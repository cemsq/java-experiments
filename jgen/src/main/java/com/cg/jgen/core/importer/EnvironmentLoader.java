package com.cg.jgen.core.importer;

import com.cg.jgen.core.*;
import com.cg.jgen.core.exception.JGenException;
import com.cg.jgen.core.generator.Generator;
import com.cg.jgen.core.generator.GeneratorType;
import com.cg.jgen.utils.Globals;
import com.cg.jgen.utils.XmlUtility;
import com.cgm.storm.utils.common.StringUtils;
import com.google.common.base.Preconditions;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Read the xml config file
 */
public class EnvironmentLoader {
    private static final Logger log = LoggerFactory.getLogger(EnvironmentLoader.class);

    private static boolean recordModules;

    private static List<String> alwaysGenerateList;
    private static List<String> regenerateBlockList;
    private static List<String> ignoredFileTemplateList;
    private static List<String> modules;
    private static Map<String, List<String>> fileTemplatesDependencies;

    public static JGenEnvironment load(String xmlConfig, String xmlPdex) {
        log.info("===== Importing Config =========================================");
        log.info("file: {}", xmlConfig);

        Element root = XmlUtility.readXmlFromFile(xmlConfig).getRootElement();

        Config config = new Config(root);

        modules = new ArrayList<>();
        fileTemplatesDependencies = new HashMap<>();
        List<String> ignoredColumns = buildSimpleList(root, "IgnoreColumns", "Column", "name");
        alwaysGenerateList = buildSimpleList(root, "AlwaysGenerate", "FileTemplate", "id");
        regenerateBlockList = buildSimpleList(root, "RegenerateBlock", "FileTemplate", "id", false);
        ignoredFileTemplateList = buildSimpleList(root, "IgnoreFileTemplates", "FileTemplate", "id", false);

        List<Entity> entities = PDexImporter.importEntityList(xmlPdex, config.getXmlSchema(), ignoredColumns);

        JGenEnvironment env = new JGenEnvironment();
        env.setConfig(config);
        env.setEntities(entities);

        buildGenerators(root, env, config);

        env.setModules(modules);

        log.info("import done.\n");

        return env;
    }

    /**
     * Build a simple String List from a 2 level xml Element
     *
     * @param root       root element containing the 2 levels Elements
     * @param parentName first level Element name
     * @param childName  second level Element name
     * @param attribute  attribute to read
     * @return a String list with the argument 'attribute' for each 'childName' Element
     */
    private static List<String> buildSimpleList(Element root, String parentName, String childName, String attribute) {
        return buildSimpleList(root, parentName, childName, attribute, true);
    }

    /**
     * Build a simple String List from a 2 level xml Element
     *
     * @param root       root element containing the 2 levels Elements
     * @param parentName first level Element name
     * @param childName  second level Element name
     * @param attribute  attribute to read
     * @param throwException if there is an Exception, and throwException is 'false', it delivers an empty List
     * @return a String list with the argument 'attribute' for each 'childName' Element
     */
    private static List<String> buildSimpleList(Element root, String parentName, String childName, String attribute, boolean throwException) {
        try {
            log.info("{} list.", parentName);
            List<String> list = XmlUtility.buildSimpleList(root, parentName, childName, attribute);
            log.info("    {}", list);

            return list;

        } catch (Exception e) {
            if (throwException) {
                throw e;
            } else {
                log.warn("{}", e.getMessage());
                return new ArrayList<>();
            }
        }
    }

    private static void buildGenerators(Element root, JGenEnvironment env, Config config) {
        Element languagesRoot = root.getChild("Languages");
        Preconditions.checkNotNull(languagesRoot, "'Languages' element in config file not found");

        recordModules = false;
        for (Element genDefinition : languagesRoot.getChildren()) {
            String name = genDefinition.getName();
            GeneratorType type = GeneratorType.getType(name);

            Generator gen = type.createInstance(config);
            if (gen != null) {
                recordModules = gen.getLanguage() == Language.JAVA;

                env.addGenerator(type, gen);
                env.addFileTemplateList(type, buildFileTemplates(genDefinition));
            }
        }

    }

    /**
     * Build a language's FileTemplate list.
     *
     * @param languageElement FileTemplate's language to build
     * @return a FileTemplate list for the attribute language
     */
    private static List<FileTemplate> buildFileTemplates(Element languageElement) {
        List<Element> files = languageElement.getChildren("FileTemplate");
        Preconditions.checkArgument(files.size() > 0, "Language without FileTemplate elements: " + languageElement.getName());

        log.info("   Language: " + languageElement.getName());

        List<FileTemplate> list = new ArrayList<>();
        for (Element file : files) {
            FileTemplate ft = buildFileTemplate(file);
            if (ft != null) {
                List<String> dependencyList = buildDependencyList(file);
                fileTemplatesDependencies.put(ft.getId(), dependencyList);

                list.add(ft);
            }
        }

        matchDependencies(FileTemplate.getFileTemplatesCreated());

        return list;
    }

    /**
     * Build a FileTemplate
     * <p/>
     * This method reads the FileTemplate attributes from the xml Element: file
     *
     * @param fileTemplateElement xml Element corresponding to a FileTemplate
     * @return a new FileTemplate instance
     * @throws com.cg.jgen.core.exception.JGenException if the build() method find an invalid attribute (null or empty)
     * @see com.cg.jgen.core.FileTemplateBuilder
     */
    private static FileTemplate buildFileTemplate(Element fileTemplateElement) {
        String id = fileTemplateElement.getAttributeValue("id");
        log.info("        - Loading FileTemplate: {}", id);

        FileTemplate ft = FileTemplate.build(fileTemplateElement);

        boolean ignored = Globals.isListed(ignoredFileTemplateList, id);
        boolean alwaysGenerate = Globals.isListed(alwaysGenerateList, id);
        boolean regenerateBlock = Globals.isListed(regenerateBlockList, id);
        ft.setIgnored(ignored);
        ft.setAlwaysGenerate(alwaysGenerate);
        ft.setRegenerateBlock(regenerateBlock);

        addModule(ft.getModule());

        return ft;
    }

    /**
     * Build the dependencyList from a xml FileTemplate element
     *
     * @param fileTemplateElement the xml Element that represent a FileTemplate
     * @return the dependencyList of a FileTemplate
     * @throws com.cg.jgen.core.exception.JGenException if a dependency has no Id in the xml file
     */
    private static List<String> buildDependencyList(Element fileTemplateElement) {
        List<String> list = new ArrayList<>();

        List<Element> depList = fileTemplateElement.getChildren("Dependency");
        for (Element dep : depList) {
            String id = dep.getAttributeValue("id");
            if (StringUtils.isValid(id)) {
                list.add(id);
            } else {
                throw new JGenException("Dependency without 'id'");
            }
        }

        return list;
    }

    /**
     * Match the existing dependencies for each FileTemplate object.
     *
     * @param fileTemplates FileTemplates created
     * @throws com.cg.jgen.core.exception.JGenException if a dependency Id is not found in the FileTemplates List
     */
    private static void matchDependencies(List<FileTemplate> fileTemplates) {
        for (FileTemplate ft : fileTemplates) {
            List<String> dependencyList = fileTemplatesDependencies.get(ft.getId());
            List<FileTemplate> dependencies = new ArrayList<>();
            for (String id : dependencyList) {
                FileTemplate dependency = FileTemplate.getFileTemplate(fileTemplates, id, false);
                if (dependency == null) {
                    throw new JGenException("Dependency '" + id + "' in FileTemplate: '" + ft.getId() + "' not found: ");
                }

                dependencies.add(dependency);
            }
            ft.setDependencies(dependencies);
        }
    }

    /**
     * Store a module name
     * <p/>
     * If module is an invalid String (empty or null) or recordModules flag is set to false, this method do nothing
     *
     * @param module module name
     */
    private static void addModule(String module) {
        if (!StringUtils.isValid(module) || !recordModules) {
            return;
        }

        if (!Globals.isListed(modules, module)) {
            modules.add(module);
        }
    }

}
