package com.cg.jgen.core.generator;

import com.cg.jgen.core.Config;
import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.Language;
import com.cg.jgen.utils.FileGeneratorAssistant;
import com.cg.jgen.utils.Utility;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Base Generator
 */
public class Generator {
    protected static final Logger log = LoggerFactory.getLogger(Generator.class);
    protected static final VelocityAdapter velocity = new VelocityAdapter();
    protected static final Utility utility = new Utility();

    protected List<FileTemplate> fileTemplates;
    protected List<Entity> entities;
    protected Language language;
    protected Config config;

    protected String template;
    protected String rendered;

    protected FileTemplate currFt;
    protected Entity currEtt;

    private int countFt;
    private int countEtt;
    private boolean nextEntity;

    public Generator(Config config) {
        Preconditions.checkNotNull(config, "Config must be not null");
        this.config = config;
        this.language = Language.GENERIC;
    }

    public Generator(Language language, Config config) {
        Preconditions.checkNotNull(language, "Language must be not null");
        Preconditions.checkNotNull(config, "Config must be not null");

        this.config = config;
        this.language = language;
    }

    /**
     * Render a single FileTemplate and Entity into a String
     *
     * @param ft     FileTemplate to inject
     * @param entity Entity to inject
     * @return the String rendered
     */
    public String render(FileTemplate ft, Entity entity) {
        currFt = ft;
        currEtt = entity;

        inject(ft, entity);

        rendered = velocity.render(template);
        return rendered;
    }

    /**
     * Inject important values from FileTemplate and Entity into VelocityAdapter
     * @param ft FileTemplate to inject
     * @param entity Entity to inject
     */
    protected void inject(FileTemplate ft, Entity entity) {
        String entityName = entity.getName();
        template = ft.getTemplateName();

        // common injections
        velocity.put("Entity", Utility.pascalCase(entityName));
        velocity.put("entity", Utility.camelCase(entityName));
        velocity.put("Members", Injector.getInjectableMembers(entity));
        velocity.put("Description", entity.getDescription());
        velocity.put("HeaderTemplate", ft.getHeaderTemplate());
        velocity.put("Date", Utility.date());
        velocity.put("util", utility);
    }

    /**
     * Render a list from Entities and FileTemplates
     * @param entities Entities to render
     * @param fts FileTemplates to render
     */
    public void renderAll(List<Entity> entities, List<FileTemplate> fts) {
        Preconditions.checkNotNull(entities, "Entities must be not null");
        Preconditions.checkNotNull(fts, "FileTemplates must be not null");

        this.entities = entities;
        this.fileTemplates = fts;

        countEtt = -1;
        countFt = 0;
        nextEntity = false;
    }

    /**
     * Return the next String rendered. <p>
     * The method {@link Generator#renderAll(java.util.List, java.util.List)} had to be called before.
     * Return null if there are no more Entities to render.
     * @return next String rendered.
     */
    public String getNextRender() {
        Preconditions.checkNotNull(fileTemplates, "FileTemplates must be not null");
        Preconditions.checkNotNull(entities, "Entities must be not null");

        FileTemplate ft = getNextFileTemplate();
        Entity ett = getNextEntity();

        if (ett == null) {
            return null;
        }

        render(ft, ett);
        rendered = postRender();

        return rendered;
    }

    private Entity getNextEntity() {
        if (nextEntity) {
            countEtt++;
        }

        if (countEtt >= entities.size()) {
            return null;
        }

        Entity ett = entities.get(countEtt);
        if (isEntityEnabled(ett)) {
            nextEntity = false;
            return ett;
        } else {
            return getNextEntity();
        }
    }

    private FileTemplate getNextFileTemplate() {
        int size = fileTemplates.size();

        FileTemplate ft;
        do {
            int pos = countFt++ % size;
            if (pos == 0) {
                nextEntity = true;
            }
            ft = fileTemplates.get(pos);
        } while (ft.isIgnored());

        return ft;
    }

    protected boolean isEntityEnabled(Entity entity) {
        return !entity.isValueTable();
    }

    protected String postRender() {
        if (rendered == null) {
            return null;
        }

        String entityName = currEtt.getName();
        String dir = currFt.generateDirectoryName(config, entityName, language);
        String file = currFt.generateFileName(entityName, language);

        return FileGeneratorAssistant.regenerateBlock(dir, file, rendered, currFt.isRegenerateBlock(), currFt.isAlwaysGenerated());
    }

    // <editor-fold desc="--- Getter und Setter---">


    public Config getConfig() {
        return config;
    }

    public String getRendered() {
        return rendered;
    }

    public Language getLanguage() {
        return language;
    }

    public Entity getCurrentEntity() {
        return currEtt;
    }

    public FileTemplate getCurrentFileTemplate() {
        return currFt;
    }
    // </editor-fold>
}
