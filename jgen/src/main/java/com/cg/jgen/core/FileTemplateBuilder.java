package com.cg.jgen.core;

import com.cgm.storm.utils.common.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Assistant to build a FileTemplate.
 */
public class FileTemplateBuilder {
    private String id;
    private String fileName;
    private String module;
    private String relativePackage;
    private String template;
    private String sourcePath;
    private String headerTemplate;

    private List<FileTemplate> dependencies;

    private boolean alwaysGenerate;
    private boolean regenerateBlock;
    private boolean ignored;
    private String resourceCatalogId;

    public FileTemplateBuilder() {
        this.alwaysGenerate = false;
        this.regenerateBlock = false;
        this.ignored = false;
    }

    public FileTemplateBuilder id(String id) {
        this.id = id;
        return this;
    }

    public FileTemplateBuilder fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileTemplateBuilder module(String module) {
        this.module = module;
        return this;
    }

    public FileTemplateBuilder relativePackage(String relativePath) {
        this.relativePackage = relativePath;
        return this;
    }

    public FileTemplateBuilder template(String template) {
        this.template = template;
        return this;
    }

    public FileTemplateBuilder sourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
        return this;
    }

    public FileTemplateBuilder headerTemplate(String headerTemplateName) {
        this.headerTemplate = headerTemplateName;
        return this;
    }

    public FileTemplateBuilder alwaysGenerate(boolean alwaysGenerate) {
        this.alwaysGenerate = alwaysGenerate;
        return this;
    }

    public FileTemplateBuilder regenerateBlock(boolean regenerateBlock) {
        this.regenerateBlock = regenerateBlock;
        return this;
    }

    public FileTemplateBuilder ignored(boolean ignored) {
        this.ignored = ignored;
        return this;
    }

    public FileTemplateBuilder resourceCatalogId(String resourceCatalogId) {
        this.resourceCatalogId = resourceCatalogId;
        return this;
    }

    public FileTemplateBuilder dependencies(List<FileTemplate> list) {
        this.dependencies = list;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getModule() {
        return module;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getHeaderTemplate() {
        return headerTemplate;
    }

    public String getRelativePackage() {
        return relativePackage;
    }

    public String getTemplate() {
        return template;
    }

    public List<FileTemplate> getDependencies() {
        return dependencies;
    }

    public boolean isAlwaysGenerate() {
        return alwaysGenerate;
    }

    public boolean isRegenerateBlock() {
        return regenerateBlock;
    }

    public boolean isIgnored() {
        return ignored;
    }

    public String getResourceCatalogId() {
        return resourceCatalogId;
    }

    public void setResourceCatalogId(String resourceCatalogId) {
        this.resourceCatalogId = resourceCatalogId;
    }

    public FileTemplate build() {
        StringUtils.validateString(id, "id must be not null");
        StringUtils.validateString(fileName, "fileName must be not null");
        StringUtils.validateString(template, "template must be not null");
        StringUtils.validateString(headerTemplate, "headerTemplate must be not null");

        module = StringUtils.getValidString(module, "");
        relativePackage = StringUtils.getValidString(relativePackage, "");

        if (dependencies == null) {
            dependencies = new ArrayList<>();
        }

        return FileTemplate.factory(this);
    }


}
