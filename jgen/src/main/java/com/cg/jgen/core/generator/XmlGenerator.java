package com.cg.jgen.core.generator;

import com.cg.jgen.core.*;
import com.cg.jgen.utils.Globals;
import com.cg.jgen.utils.XmlUtility;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 */
public class XmlGenerator extends Generator {
    public XmlGenerator(Config config) {
        super(Language.XML, config);
    }

    @Override
    protected boolean isEntityEnabled(Entity entity) {
        return entity.isValueTable();
    }

    @Override
    protected void inject(FileTemplate ft, Entity entity) {

        super.inject(ft, entity);

        String entityName = entity.getName();
        ft.setAlwaysGenerate(true);

        velocity.put("EnumValueName", entityName);
        velocity.put("Values", entity.getEnumValues());
        velocity.put("HelixNamespace", config.getHelixNamespace());
        velocity.put("EnumValueCatalogId", ft.getResourceCatalogId());

        boolean idIsInteger = false;
        if (entity.isValueTable()) {
            Member idCol = entity.getMemberByName("id", false);
            if (idCol.getAttributeValue("type").equals("Integer")) {
                idIsInteger = true;
            }
        }
        velocity.put("IdIsInteger", idIsInteger);
    }

    @Override
    protected String postRender() {
        Namespace namespace = Namespace.getNamespace(config.getHelixNamespace());

        String entityName = currEtt.getName();
        String dir = currFt.generateDirectoryName(config, entityName, language);
        String file = currFt.generateFileName(entityName, language);

        String path = Globals.concatDirectoryNames(dir, file);
        Element root = XmlUtility.readXmlFromFile(path).getRootElement();

        switch (currFt.getId()) {
            case "EntityEnumXml":
                // XML for initializing the value tables
                XmlUtility.adjustXmlForValueTable(namespace, root, rendered);
                break;
            case "ResourceXml":
                // XML for updating the resources
                XmlUtility.adjustXmlResourceForValueTable(namespace, root, rendered);
                break;
            default:
        }

        XMLOutputter xmlOutput = new XMLOutputter();
        Document doc = new Document(root.detach());
        Format format = Format.getPrettyFormat();
        format.setIndent("    ");
        xmlOutput.setFormat(format);

        return xmlOutput.outputString(doc);
    }
}
