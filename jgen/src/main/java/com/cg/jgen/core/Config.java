package com.cg.jgen.core;

import com.cg.jgen.core.importer.DictionaryImporter;
import com.cg.jgen.utils.FileAssistant;
import com.cg.jgen.utils.Globals;
import com.cgm.storm.utils.common.StringUtils;
import com.google.common.base.Preconditions;
import org.jdom2.Element;

/**
 *
 */
public class Config {

    private String appPath = "";
    private String rootPackage = "";
    private String jarPath = "";
    private String dictionary = "";
    private String xmlSchema = "";
    private String helixNamespace = "";

    private String databaseProject = "";
    private String databaseUpgradeTest = "";
    private String migrationFile = "";

    public Config() {
    }

    public Config(Element config) {
        Preconditions.checkNotNull(config, "Element must be not null");
        Preconditions.checkArgument(config.getName().equals("Config"), "Wrong xml Element. 'Config' expected");

        setAppPath(config.getAttributeValue("appPath"));
        setRootPackage(config.getAttributeValue("rootPackage"));
        setJarPath(config.getAttributeValue("jarPath"));
        setDictionary(config.getAttributeValue("dictionary"));
        setXmlSchema(config.getAttributeValue("schema"));
        setHelixNamespace(config.getAttributeValue("helixNamespace"));

        setDatabaseProject(config.getAttributeValue("databaseProject"));
        setDatabaseUpgradeTest(config.getAttributeValue("databaseUpgradeTest"));
        setMigrationFile(config.getAttributeValue("migrationFile"));

        DictionaryImporter.importDictionary(dictionary);
    }

    public boolean isValidToUpdateMigrationFile() {
        return StringUtils.isValid(databaseProject) &&
               StringUtils.isValid(databaseUpgradeTest) &&
               StringUtils.isValid(migrationFile);
    }

    // <editor-fold desc="--Getter and Setter">
    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        FileAssistant.validateExistence(appPath, "appPath not found: " + appPath);
        this.appPath = appPath;
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = StringUtils.getValidString(rootPackage, "");
    }

    public String getJarPath() {
        return jarPath;
    }

    public String getFullJarPath() {
        return Globals.concatDirectoryNames(getAppPath(), getJarPath());
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    public String getDictionary() {
        return dictionary;
    }

    public void setDictionary(String dictionary) {
        this.dictionary = dictionary;
    }

    public String getXmlSchema() {
        return xmlSchema;
    }

    public void setXmlSchema(String xmlSchema) {
        this.xmlSchema = StringUtils.getValidString(xmlSchema, "");
    }

    public String getHelixNamespace() {
        return helixNamespace;
    }

    public void setHelixNamespace(String helixNamespace) {
        this.helixNamespace = helixNamespace;
    }

    public String getDatabaseProject() {
        return databaseProject;
    }

    public void setDatabaseProject(String databaseProject) {
        this.databaseProject = databaseProject;
    }

    public String getDatabaseUpgradeTest() {
        return databaseUpgradeTest;
    }

    public void setDatabaseUpgradeTest(String databaseUpgradeTest) {
        this.databaseUpgradeTest = databaseUpgradeTest;
    }

    public String getMigrationFile() {
        return migrationFile;
    }

    public void setMigrationFile(String migrationFile) {
        this.migrationFile = migrationFile;
    }

    // </editor-fold>
}
