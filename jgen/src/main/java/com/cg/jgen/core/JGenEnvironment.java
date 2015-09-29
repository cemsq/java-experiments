package com.cg.jgen.core;

import com.cg.jgen.core.exception.JGenException;
import com.cg.jgen.core.generator.Generator;
import com.cg.jgen.core.generator.GeneratorType;
import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class JGenEnvironment {
    private Config config;
    private List<String> modules;
    private List<Entity> entities;
    private Map<GeneratorType, Generator> generators;
    private Map<GeneratorType, List<FileTemplate>> fileTemplates;

    public JGenEnvironment() {
        generators = new HashMap<>();
        fileTemplates = new HashMap<>();
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void addGenerator(GeneratorType type, Generator gen) {
        Preconditions.checkState(!generators.containsKey(type), "Generator name duplicated");
        Preconditions.checkNotNull(gen, "Generator must be not null");

        generators.put(type, gen);
    }

    public Generator getGenerator(GeneratorType name) {
        if (!generators.containsKey(name)) {
            throw new JGenException("Generator not found: " + name);
        }
        return generators.get(name);
    }

    public void addFileTemplateList(GeneratorType type, List<FileTemplate> fts) {
        Preconditions.checkState(!fileTemplates.containsKey(type), "FileTemplates name duplicated");
        Preconditions.checkNotNull(fts, "FileTemplateList must be not null");

        fileTemplates.put(type, fts);
    }

    public List<FileTemplate> getFileTemplates(GeneratorType name) {
        if (!fileTemplates.containsKey(name)) {
            throw new JGenException("FileTemplates not found: " + name);
        }
        return fileTemplates.get(name);
    }
}
