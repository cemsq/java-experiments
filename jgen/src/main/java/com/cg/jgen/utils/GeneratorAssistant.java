package com.cg.jgen.utils;

import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.Member;
import com.cgm.storm.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class GeneratorAssistant {
    private static final Logger log = LoggerFactory.getLogger(GeneratorAssistant.class);
    private static String imports;

    public static String generatePackage(String rootPackage, String relativePackage, String entityName) {
        StringUtils.validateString(entityName, "entityName must be not null or not empty");
        relativePackage = StringUtils.getValidString(relativePackage, "");
        rootPackage = StringUtils.getValidString(rootPackage, "");

        String expression = relativePackage.toLowerCase();
        String replacement = Utility.camelCase(entityName);

        String relPackage = Globals.replaceEntityExpression(expression, replacement);

        return Globals.concatObjectNames(rootPackage, relPackage);
    }

    public static String generateImports(FileTemplate fileTemplate, Entity entity, String rootPackage) {
        imports = "";

        String entityName = entity.getName();
        String thisPackage = GeneratorAssistant.generatePackage(rootPackage, fileTemplate.getRelativePackage(), entityName);

        for (FileTemplate dependency : fileTemplate.getDependencies()) {
            performImport(dependency, entity, rootPackage, thisPackage);
        }

        boolean composed = false;
        FileTemplate entityFT = FileTemplate.getFileTemplate("Entity", false);
        FileTemplate enumFt = FileTemplate.getFileTemplate("EntityEnum", false);

        for (Member member : entity.getMemberList()) {
            String type = extractTypeNameAndImportJavaList(member.getAttributeValue("type"));
            Entity ett = Entity.getEntity(type);

            if (ett != null && entityFT != null && enumFt != null) {
                //FileTemplate ft = ett.isValueTable()? enumFt: entityFT;
                FileTemplate ft = entityFT;

                composed = true;
                performImport(ft, ett, rootPackage, thisPackage);
            }

//            if (ett != null && entityFT != null) {
//                composed = true;
//                performImport(entityFT, ett, rootPackage, thisPackage);
//            }
        }

        if (composed && fileTemplate.getId().equals("EntityServiceImpl")) {
            log.warn("the Entity '{}' is composed. Please override the methods: 'getAll' and 'save' in the {}ServiceImpl class to support get and save with cascade", entityName, entityName);
        }

        return imports;
    }

    private static String extractTypeNameAndImportJavaList(String typeName) {
        int ini = typeName.indexOf("List<");
        int end = typeName.indexOf(">");

        if (ini >= 0 && end > 0) {
            imports = StringUtils.concatStringsWith("\n", imports, "import java.util.List;");
            return typeName.substring(ini + 5, end);
        }

        return typeName;
    }

    private static void performImport(FileTemplate dependency, Entity entity, String rootPackage, String thisPackage) {
        String entityName = entity.getName();
        String depPackage = Utility.getPackage(dependency, entityName);

        if (!thisPackage.equals(depPackage)) {
            String depObject = dependency.generateLongObjectName(rootPackage, entityName);
            String depImport = String.format("import %s;", depObject);
            imports = StringUtils.concatStringsWith("\n", imports, depImport);
        }
    }
}
