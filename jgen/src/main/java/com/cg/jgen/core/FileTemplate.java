package com.cg.jgen.core;

import com.cg.jgen.core.exception.JGenException;
import com.cg.jgen.utils.FileAssistant;
import com.cg.jgen.utils.Globals;
import com.cg.jgen.utils.Utility;
import com.cgm.storm.utils.common.StringUtils;
import com.google.common.base.Preconditions;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * FileTemplate meta information
 */
public class FileTemplate {

    /**
     * FileTemplatesCreated
     */
    private static List<FileTemplate> fileTemplatesCreated = new ArrayList<>();
    /**
     * FileTemplate id
     */
    private String id;
    /**
     * the generated file Name. This can contain an expression of the form: {entity.name}
     */
    private String fileName;
    /**
     * module path from the root application
     */
    private String module;
    /**
     * relative package from the root package
     */
    private String relativePackage;
    /**
     * file template name (.vm extension)
     */
    private String template;
    /**
     * header template name (.vm extension)
     */
    private String headerTemplate;
    /**
     * relative path to the source core from the appPath + module
     */
    private String sourcePath;
    /**
     * alwaysGenerate in renderProcess
     */
    private boolean alwaysGenerate;
    /**
     * render regenerated block flag
     */
    private boolean regenerateBlock;
    /**
     * FileTemplate to be ignored in the render process
     */
    private boolean ignored;
    /**
     * FileTemplate dependencies
     */
    private List<FileTemplate> dependencies;

    private String resourceCatalogId;


    public FileTemplate() {
        dependencies = new ArrayList<>();
        fileTemplatesCreated.add(this);
    }

    public static void reset() {
        fileTemplatesCreated.clear();
    }

    /**
     * a Factory method that create a new instance of FileTemplate
     *
     * @param builder the builder object
     * @return a new FileTemplate instance
     * @throws NullPointerException                     if builder is null
     * @throws com.cg.jgen.core.exception.JGenException if the FileTemplate already exist
     */
    public static FileTemplate factory(FileTemplateBuilder builder) {
        Preconditions.checkNotNull(builder, "FileTemplateBuilder must be not null");
        String id = builder.getId();
        if (FileTemplate.getFileTemplate(id, false) != null) {
            throw new JGenException("FileTemplate already exist: " + id);
        }

        FileTemplate fileTemplate = new FileTemplate();
        fileTemplate.setId(id);
        fileTemplate.setFileName(builder.getFileName());
        fileTemplate.setModule(builder.getModule());
        fileTemplate.setSourcePath(builder.getSourcePath());
        fileTemplate.setTemplateName(builder.getTemplate());
        fileTemplate.setHeaderTemplate(builder.getHeaderTemplate());
        fileTemplate.setAlwaysGenerate(builder.isAlwaysGenerate());
        fileTemplate.setRegenerateBlock(builder.isRegenerateBlock());
        fileTemplate.setIgnored(builder.isIgnored());
        fileTemplate.setRelativePackage(builder.getRelativePackage());
        fileTemplate.setDependencies(builder.getDependencies());
        fileTemplate.setResourceCatalogId(builder.getResourceCatalogId());

        return fileTemplate;
    }

    /**
     * a Factory method that create a new instance of FileTemplate
     *
     * @param element a xml Element that contains the FileTemplate meta-data.
     * @return a new FileTemplate instance
     * @throws NullPointerException                     if builder is null
     * @throws com.cg.jgen.core.exception.JGenException if the FileTemplate already exist
     */
    public static FileTemplate build(Element element) {
        Preconditions.checkNotNull(element, "Element muss be not null");
        Preconditions.checkArgument(element.getName().equals("FileTemplate"), "the Xml-Element muss be FileTemplate");

        String id = element.getAttributeValue("id");
        if (FileTemplate.getFileTemplate(id, false) != null) {
            throw new JGenException("FileTemplate already exist: " + id);
        }

        String fileName = element.getAttributeValue("fileName");
        String module = element.getAttributeValue("module");
        String relativePackage = element.getAttributeValue("relativePackage");
        String template = element.getAttributeValue("template");
        String headerTemplate = element.getAttributeValue("headerTemplate");
        String sourcePath = element.getAttributeValue("sourcePath");
        String resourceCatalogId = element.getAttributeValue("resourceCatalogId");

        StringUtils.validateString(id, "id must be not null");
        StringUtils.validateString(fileName, "fileName must be not null");
        StringUtils.validateString(template, "template must be not null");

        sourcePath = StringUtils.getValidString(sourcePath, "");

        module = StringUtils.getValidString(module, "");
        relativePackage = StringUtils.getValidString(relativePackage, "");

        FileTemplate ft = new FileTemplate();
        ft.setId(id);
        ft.setFileName(fileName);
        ft.setModule(module);
        ft.setRelativePackage(relativePackage);
        ft.setTemplateName(template);
        ft.setHeaderTemplate(headerTemplate);
        ft.setSourcePath(sourcePath);
        ft.setResourceCatalogId(resourceCatalogId);

        ft.setAlwaysGenerate(false);
        ft.setRegenerateBlock(false);
        ft.setIgnored(false);

        return ft;
    }

    /**
     * @return the FileTemplates created
     */
    public static List<FileTemplate> getFileTemplatesCreated() {
        return fileTemplatesCreated;
    }

    /**
     * Returns a FileTemplate for a given Id
     *
     * @param id             FileTemplate id to find
     * @param throwException indicates if an exception should be throw when the FileTemplate is not found
     * @return the FileTemplate with the same id as the parameter. If the FileTemplate is not found and throwException is true it throws an exception, null otherwise
     * @see #getFileTemplate(java.util.List, String, boolean)
     */
    public static FileTemplate getFileTemplate(String id, boolean throwException) {
        return getFileTemplate(fileTemplatesCreated, id, throwException);
    }

    /**
     * Returns a FileTemplate from the list for a given Id
     *
     * @param list           FileTemplate search list
     * @param id             FileTemplate id to find
     * @param throwException indicates if an exception should be throw when the FileTemplate is not found
     * @return the FileTemplate with the same id as the parameter. If the FileTemplate is not found and throwException is true it throws an exception, null otherwise
     * @throws com.cg.jgen.core.exception.JGenException if the FileTemplate is not found and throwException is set to true
     */
    public static FileTemplate getFileTemplate(List<FileTemplate> list, String id, boolean throwException) {
        for (FileTemplate ft : list) {
            if (ft.getId().equals(id)) {
                return ft;
            }
        }

        if (throwException) {
            throw new JGenException("FileTemplate: '" + id + "' not found.");
        }

        return null;
    }

    /**
     * Generates the directory name where the FileTemplate will be located
     *
     * @param config     global config
     * @param entityName entity name
     * @param language   programing language
     * @return the object name of a given entityName
     */
    public String generateDirectoryName(Config config, String entityName, Language language) {
        Preconditions.checkNotNull(config, "Config should be not null");
        Preconditions.checkNotNull(language, "Language should be not null");
        StringUtils.validateString(entityName, "entityName should be not null or not empty");

        String appDir = config.getAppPath();
        String source = this.getSourcePath();
        String relativePackage = "";

        if (language == Language.JAVA) {
            relativePackage = Globals.concatObjectNames(config.getRootPackage(), this.getRelativePackage());
        }

        relativePackage = relativePackage.replace('.', '/');

        String dir = Globals.concatDirectoryNames(appDir, this.getModule(), source, relativePackage);
        String exp = Globals.replaceEntityExpression(dir, Utility.camelCase(entityName));

        return Globals.fixBackslashWithSlash(exp);
    }

    /**
     * Generates the file name of the FileTemplate
     *
     * @param entityName entity name
     * @return the file name of a given entityName
     */
    public String generateFileName(String entityName, Language language) {
        String str = this.generateObjectName(entityName);
        str += "." + language.getExtension();

        return Globals.fixBackslashWithSlash(str);
    }

    /**
     * Generates an object name with the argument 'entityName'
     * <p/>
     * The member {@link #fileName} can contain an expression in the form: {entity.name}.
     * This method replace these expression with the argument 'entityName'. If no expression is found, it returns the fileName
     *
     * @param entityName replacement expression
     * @return the object name of a given entityName
     */
    public String generateObjectName(String entityName) {
        return Globals.replaceEntityExpression(fileName, entityName);
    }

    /**
     * Generates an object name with the argument 'entityName' (package name is include).
     *
     * @param rootPackage root package of the application. (com.cgm.storm)
     * @param entityName  replacement expression
     * @return an object name with the package
     * @see #generateObjectName(String)
     */
    public String generateLongObjectName(String rootPackage, String entityName) {
        String objectName = generateObjectName(entityName);

        return Globals.concatObjectNames(rootPackage, this.relativePackage, objectName);
    }

    /**
     * @return the dependency List as String
     */
    public String dependenciesAsString() {
        int i = 0;
        StringBuilder sb = new StringBuilder("");
        for (FileTemplate dep : dependencies) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(dep.getId());

            i++;
        }

        return "[" + sb.toString() + "]";
    }

    // <editor-fold desc = "--- Getter and Setter ---">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getRelativePackage() {
        return relativePackage;
    }

    public void setRelativePackage(String relativePackage) {
        this.relativePackage = relativePackage;
    }

    public String getTemplateName() {
        return template;
    }

    public void setTemplateName(String template) {
        StringUtils.validateString(template, "template must be not null or not empty");

        FileAssistant.validateTemplateExistence(template, "Template not found: '" + template + "'");
        this.template = template;
    }

    public String getHeaderTemplate() {
        return headerTemplate;
    }

    public void setHeaderTemplate(String headerTemplate) {
        StringUtils.validateString(headerTemplate, "headerTemplate must be not null or not empty");

        FileAssistant.validateTemplateExistence(headerTemplate, "HeaderTemplate not found: '" + headerTemplate + "'");
        this.headerTemplate = headerTemplate;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public boolean isAlwaysGenerated() {
        return alwaysGenerate;
    }

    public void setAlwaysGenerate(boolean alwaysGenerate) {
        this.alwaysGenerate = alwaysGenerate;
    }

    public boolean isRegenerateBlock() {
        return regenerateBlock;
    }

    public void setRegenerateBlock(boolean regenerateBlock) {
        this.regenerateBlock = regenerateBlock;
    }

    public boolean isIgnored() {
        return ignored;
    }

    public void setIgnored(boolean ignored) {
        this.ignored = ignored;
    }

    public String getResourceCatalogId() {
        return resourceCatalogId;
    }

    public void setResourceCatalogId(String resourceCatalogId) {
        this.resourceCatalogId = resourceCatalogId;
    }

    public List<FileTemplate> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<FileTemplate> dependencies) {
        this.dependencies = dependencies;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "\nFileTemplate{ \n" +
                "      id               ='" + id + "' \n" +
                "      fileName         ='" + fileName + "' \n" +
                "      module           ='" + module + "' \n" +
                "      relativePackage  ='" + relativePackage + "' \n" +
                "      template         ='" + template + "' \n" +
                "      alwaysGenerate   =" + alwaysGenerate + " \n" +
                "      dependencies     =" + dependenciesAsString() + " \n" +
                '}';
    }

    // ----------------------------------------------------------------------------------------

}
