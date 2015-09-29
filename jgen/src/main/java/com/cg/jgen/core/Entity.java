package com.cg.jgen.core;

import com.cg.jgen.core.exception.JGenException;
import com.cg.jgen.utils.Utility;
import com.cgm.storm.utils.common.StringUtils;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Entity {
    private static final Map<String, Entity> entities = new HashMap<>();

    private String name;
    private String description;
    private List<Member> memberList;
    private Index primary;
    private List<Index> indexes;
    private boolean isValueTable;

    private List<EnumValue> enumValues;

    public Entity(String name) {
        StringUtils.validateString(name, "Entity name must be not null or not empty");
        Preconditions.checkState(!entities.containsKey(name), "Entity name duplicated: " + name);

        this.name = name;
        memberList = new ArrayList<>();
        indexes = new ArrayList<>();
        enumValues = new ArrayList<>();

        entities.put(name, this);
        isValueTable = false;
    }

    public static void reset() {
        entities.clear();
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public static Entity getEntity(String name) {
        return entities.get(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Utility.pascalCase(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValueTable() {
        return isValueTable;
    }

    public void setValueTable(boolean isValueTable) {
        this.isValueTable = isValueTable;
    }

    public Entity addMember(Member member) {
        Preconditions.checkNotNull(member, "member must be not null");

        Member m = getMemberByName(member.getAttributeValue("name"), false);
        if (m == null) {
            memberList.add(member);
        } else {
            throw new JGenException("Duplicated member: " + m.getAttributeValue("name") + " in " + name);
        }

        return this;
    }

    public void setMembers(List<Member> memberList) {
        Preconditions.checkNotNull(memberList);
        for (Member member : memberList) {
            addMember(member);
        }
    }

    public Member getMemberByName(String name, boolean throwException) {
        for (Member member : memberList) {
            if (member.getAttributeValue("name").equals(name)) {
                return member;
            }
        }

        if (throwException) {
            throw new JGenException("Table '" + this.getName() + "' has no member such: '" + name + "'");
        }

        return null;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public Index getPrimaryKey() {
        return primary;
    }

//    public String getPrimaryKeyAsString() {
//        if (primary == null) {
//            return "";
//        }
//
//        String primary = "primaryKey = @PrimaryKey(";
//        primary += "name = \"" + this.primary.getName() + "\"";
//        primary += ", elementNames = {" + this.primary.columnsAsString() + "})";
//
//        return primary;
//    }

    public void addForeignKey(ForeignKey fk) {
        Preconditions.checkNotNull(fk, "ForeignKey must be not null");

        Entity toTable = Entity.getEntity(fk.getParentTable());

        List<Map<String, String>> fkCols = fk.getColumns();
        for (Map<String, String> col : fkCols) {
            String src = col.get("src");
            String target = col.get("target");

            this.getMemberByName(src, true);

            if (toTable != null) {
                toTable.getMemberByName(target, true);
            }
        }

        Member member = new Member();
        String type = fk.generateType();
        String name = fk.generateName();

        member.addAttribute(new Member.Attribute("name", name));
        member.addAttribute(new Member.Attribute("type", type));
        member.addAttribute(new Member.Attribute("helixAnnotation", fk.buildAnnotation()));

        this.addMember(member);
    }

    public Entity addIndex(Index index) {
        Preconditions.checkNotNull(index, "Index must be not null");

        // search for an invalid member name
        List<String> columns = index.getColumns();
        for (String colName : columns) {
            colName = Utility.camelCase(colName);
            if (this.getMemberByName(colName, false) == null) {
                throw new JGenException("Column '" + colName + "' in Index '" + index.getName() + "' not match with the table definition. Table: " + name + ".");
            }
        }

        if (index.isPrimary()) {
            if (primary != null) {
                throw new JGenException("PrimaryKey for '" + name + "' already defined.");
            }
            primary = index;
        } else {
            indexes.add(index);
        }

        return this;
    }

    public void setIndexes(List<Index> indexes) {
        if (indexes == null) {
            return;
        }

        this.indexes.clear();
        for (Index index : indexes) {
            addIndex(index);
        }
    }

//    public String getIndexesAsString() {
//        String space = System.lineSeparator() + "        ";
//
//        int i = 0;
//        StringBuilder sbIndexes = new StringBuilder("");
//        for (Index index : this.indexes) {
//            String auxSpace = "";
//            // more than 1 element
//            if (i > 0) {
//                auxSpace = ", ";
//            }
//            auxSpace = auxSpace + space;
//
//            sbIndexes.append(String.format("%s" + "@TableIndex(%s)", auxSpace, index));
//            i++;
//        }
//
//        return "indexes = {" + sbIndexes.toString() + System.lineSeparator() + "    }";
//    }

    public Entity addEnumValue(EnumValue enumValue) {
        Preconditions.checkNotNull(enumValue, "EnumValue must be not null");

        enumValues.add(enumValue);

        return this;
    }

    public List<EnumValue> getEnumValues() {
        return enumValues;
    }

    public void setEnumValues(List<EnumValue> enumValues) {
        if (enumValues == null) {
            return;
        }

        this.enumValues.clear();
        for (EnumValue enumValue : enumValues) {
            addEnumValue(enumValue);
        }
    }

//    public String getDatabaseTableAnnotation() {
//        String databaseAnnotation = "@DatabaseTable(" + System.lineSeparator();
//        databaseAnnotation += "    tableName = \"" + name + "\"";
//        if (primary != null) {
//            databaseAnnotation += "," + System.lineSeparator() + "    " + getPrimaryKeyAsString();
//        }
//
//        if (indexes.size() > 0) {
//            databaseAnnotation += "," + System.lineSeparator() + "    " + getIndexesAsString();
//        }
//
//        databaseAnnotation += System.lineSeparator() + ")";
//
//        return databaseAnnotation;
//    }

    @Override
    public String toString() {
        String str = StringUtils.concatAsString(memberList, ", ", "", "");

        return name + ": {" + str + "}";
    }
}
