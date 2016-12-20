package com.cg.jgen.core.importer;

import com.cg.jgen.core.Dictionary;
import com.cg.jgen.core.*;
import com.cg.jgen.core.exception.JGenException;
import com.cg.jgen.utils.Globals;
import com.cg.jgen.utils.KeyWordChecker;
import com.cg.jgen.utils.Utility;
import com.cg.jgen.utils.XmlUtility;
import com.cgm.storm.utils.common.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 *
 */
public class PDexImporter {
    private static final Logger log = LoggerFactory.getLogger(PDexImporter.class);
    private static Dictionary dictionary;
    private static String currentTableName;

    private static List<String> ignoredColumns;

    private PDexImporter() {

    }

    public static List<Entity> importEntityList(String xmlDatabasePath, String pdexSchemaFile, List<String> ignoredCols) {
        log.info("===== Importing PDex =========================================");
        log.info("file: {}", xmlDatabasePath);
        log.info("schema: {}", pdexSchemaFile);

        Element root = XmlUtility.readXmlFromFileWithSchema(xmlDatabasePath, pdexSchemaFile).getRootElement();

        ignoredColumns = ignoredCols;
        dictionary = Dictionary.getInstance();
        Element tablesRoot = root.getChild("Tables");
        if (tablesRoot == null) {
            throw new JGenException("Tag 'Tables' in file '" + xmlDatabasePath + "' not found.");
        }

        List<Entity> list = buildEntityList(tablesRoot);
        List<ForeignKey> fkList = buildForeignKeys(root);
        setForeignKeys(list, fkList);

        log.info("import done.\n");

        return list;
    }

    private static List<Entity> buildEntityList(Element tablesNode) {
        List<Entity> list = new ArrayList<>();
        log.info("--- Loading EntityList ---");

        List<Element> tables = tablesNode.getChildren("Table");
        for (Element table : tables) {
            Entity entity = buildEntity(table);
            if (entity != null) {
                list.add(entity);
            }
        }

        return list;
    }

    private static Entity buildEntity(Element table) {
        currentTableName = table.getAttributeValue("name");
        KeyWordChecker.checkKeyWord(currentTableName, "Table", "");
        Element comment = table.getChild("Comment");
        String description = comment != null ? comment.getText() : "";

        String ignore = table.getAttributeValue("ignore");
        ignore = StringUtils.getValidString(ignore, "false");

        if (ignore.equals("true")) {
            log.info("- Table ignored: {}", currentTableName);
            return null;
        }

        log.info("Loading Table: {}", currentTableName);

        Entity entity = new Entity(currentTableName);
        entity.setDescription(description);
        entity.setMembers(buildMembers(table));
        entity.setIndexes(buildIndexes(table));
        entity.setEnumValues(buildEnumValues(table));
        String attIsValueTable = table.getAttributeValue("isValueTable");
        if (attIsValueTable != null && attIsValueTable.equals("true")) {
            entity.setValueTable(true);
        }


        String requiredIdStr = table.getAttributeValue("requiredId");
        boolean requiredId = Boolean.valueOf(requiredIdStr);

        if (entity.getPrimaryKey() == null && requiredId) {
            throw new JGenException("Table '" + currentTableName + "' without PrimaryKey");
        }
        log.debug("    PrimaryKey: {}", entity.getPrimaryKey());
        log.debug("        Entity created: {}\n", entity);

        return entity;
    }

    private static List<Member> buildMembers(Element table) {
        List<Member> list = new ArrayList<>();

        try {
            Element columnsRoot = table.getChild("Columns");

            List<Element> columns = columnsRoot.getChildren("Column");
            for (Element column : columns) {
                String colName = column.getAttributeValue("name");

                if (!Globals.isListed(ignoredColumns, colName)) {
                    Member member = buildMember(column);
                    list.add(member);
                }
            }

        } catch (NullPointerException e) {
            log.warn("'Columns' tag not found. Table: {}", currentTableName);
        }

        return list;
    }

    private static Member buildMember(Element column) {
        List<Attribute> attributes = column.getAttributes();
        if (attributes.size() == 0) {
            throw new JGenException("Column without attributes.");
        }
        String colName = column.getAttributeValue("name");
        StringUtils.validateString(colName, "Column without name");

        log.debug("    Loading member: {}", Utility.camelCase(colName));
        Member member = new Member();

        for (Attribute att : attributes) {
            String name = att.getName();
            String value = att.getValue();
            member.addAttribute(new Member.Attribute(name, value), currentTableName);
        }
        member.interpretAnnotation();

        return member;
    }

    public static List<EnumValue> buildEnumValues(Element table) {
        List<EnumValue> enumValues = new ArrayList<>();

        Element values = table.getChild("Values");
        if (values != null) {
            List<Element> enumValueElements = values.getChildren("Value");
            for (Element enumValueElement : enumValueElements) {
                EnumValue enumValue = buildEnumValue(enumValueElement);
                log.debug("    EnumValue loaded: {}", enumValue);
                enumValues.add(enumValue);
            }
        }

        return enumValues;
    }

    private static EnumValue buildEnumValue(Element enumValueElement) {
        String id = enumValueElement.getAttributeValue("id");
        Integer sortSequence = Integer.valueOf(enumValueElement.getAttributeValue("sortSequence"));
        String code = enumValueElement.getAttributeValue("core");
        String displayText = enumValueElement.getAttributeValue("displayText");

        return new EnumValue(id, code, displayText, sortSequence);
    }

    private static List<Index> buildIndexes(Element table) {
        List<Index> indexes = new ArrayList<>();

        List<Element> indexElements = table.getChildren("Index");
        for (Element indexElement : indexElements) {
            Index index = buildIndex(indexElement);
            log.debug("    Index loaded: {}", index);
            indexes.add(index);
        }

        return indexes;
    }

    private static Index buildIndex(Element indexElement) {
        String name = indexElement.getAttributeValue("name");
        String primary = indexElement.getAttributeValue("primary");
        String unique = indexElement.getAttributeValue("unique");

        primary = dictionary.getJGenValueByPDexValue(primary);
        unique = dictionary.getJGenValueByPDexValue(unique);

        Index index = new Index(name, primary, unique);
        try {
            List<String> columns = XmlUtility.buildSimpleList(indexElement, "Columns", "Column", "name");
            index.setColumns(columns);
        } catch (JGenException e) {
            throw new JGenException("Error loading Index. Table: '" + currentTableName + "'. Index: '" + name + "'", e);
        }

        return index;
    }

    private static List<ForeignKey> buildForeignKeys(Element root) {
        String rootElement = "ForeignKeys";
        String childElement = "ForeignKey";
        Element foreignKeysRoot = root.getChild(rootElement);

        if (foreignKeysRoot == null) {
            log.warn("{} element not found.", rootElement);
            return Collections.emptyList();
        }

        List<ForeignKey> list = new ArrayList<>();
        List<Element> foreignKeyElements = foreignKeysRoot.getChildren(childElement);
        if (foreignKeyElements.size() == 0) {
            log.warn("{} elements not found", childElement);
            return Collections.emptyList();
        }

        log.info("--- Loading ForeignKeys ---");
        for (Element foreignKey : foreignKeyElements) {
            list.add(buildForeignKey(foreignKey));
        }

        return list;
    }

    private static ForeignKey buildForeignKey(Element foreignKeyElement) {
        ForeignKey foreignKey = new ForeignKey();
        String name = foreignKeyElement.getAttributeValue("name");
        String childTable = foreignKeyElement.getAttributeValue("child");
        String parentTable = foreignKeyElement.getAttributeValue("parent");
        String cardinality = foreignKeyElement.getAttributeValue("cardinality");

        try {
            foreignKey.setName(name);
            foreignKey.setChildTable(childTable);
            foreignKey.setParentTable(parentTable);
            foreignKey.setCardinality(cardinality);
            foreignKey.setColumns(buildForeignKeyColumns(foreignKeyElement));
            log.info("    loaded: {}", name);
            log.debug("       {}", foreignKey);
        } catch (JGenException e) {
            throw new JGenException("Unable to build a ForeignKey in config file", e);
        }

        return foreignKey;
    }

    private static List<Map<String, String>> buildForeignKeyColumns(Element foreignKey) {
        List<Map<String, String>> columns = new ArrayList<>();
        List<Element> columnElements = foreignKey.getChildren("Column");

        for (Element column : columnElements) {
            Map<String, String> col = new HashMap<>();
            String srcColumn = column.getAttributeValue("src");
            String targetColumn = column.getAttributeValue("target");

            StringUtils.validateString(srcColumn, "attribute 'src' not found");
            StringUtils.validateString(targetColumn, "attribute 'target' not found");

            col.put("src", Utility.camelCase(srcColumn));
            col.put("target", Utility.camelCase(targetColumn));

            columns.add(col);
        }

        return columns;
    }

    private static void setForeignKeys(List<Entity> entities, List<ForeignKey> fkList) {
        for (Entity entity : entities) {
            List<ForeignKey> fks = ForeignKey.getForeignKeyByFrom(entity.getName(), fkList);

            for (ForeignKey fk : fks) {
                entity.addForeignKey(fk);
            }
        }
    }
}
