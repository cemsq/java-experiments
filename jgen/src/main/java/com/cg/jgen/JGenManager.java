package com.cg.jgen;


import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.JGenEnvironment;
import com.cg.jgen.core.generator.Generator;
import com.cg.jgen.core.generator.GeneratorType;
import com.cg.jgen.utils.FileAssistant;
import com.cg.jgen.utils.FileGeneratorAssistant;
import com.cg.jgen.utils.Globals;
import com.cg.jgen.utils.Utility;
import com.cgm.storm.utils.common.StringUtils;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that is responsible for managing the rendering process.
 */
public class JGenManager {
    private static final Logger log = LoggerFactory.getLogger(JGenManager.class);
    private List<String> filesCreated;

    private Entity entity;

    private String storeMessage;

    public JGenManager() {
        filesCreated = new ArrayList<>();
    }

    public void render(JGenEnvironment env, List<GeneratorType> generatorsName) {
        filesCreated.clear();

        Utility.setConfig(env.getConfig());
        for (GeneratorType type : generatorsName) {
            render(env, type);
        }

        log.info("render done.");
    }

    public void render(JGenEnvironment env, GeneratorType type) {
        Utility.setConfig(env.getConfig());
        List<Entity> entities = env.getEntities();

        Generator gen = env.getGenerator(type);
        log.info("===============================================");
        log.info("Using Generator: " + type);
        log.info("===============================================");

        entity = null;

        List<FileTemplate> fileTemplates = env.getFileTemplates(type);

        gen.renderAll(entities, fileTemplates);
        while (gen.getNextRender() != null) {
            String data = gen.getRendered();
            storeData(gen, data);

            log(gen);
        }

        log.info("render done.");
        //this.logFilesCreated();
    }

    private void log(Generator gen) {
        Entity currEtt = gen.getCurrentEntity();
        FileTemplate currFt = gen.getCurrentFileTemplate();

        if (entity != currEtt) {
            entity = currEtt;
            logEntity(entity);
        }

        logFileTemplate(currFt);
    }

    private void logEntity(Entity entity) {
        log.info("- {}: ", entity.getName());
    }

    private void logFileTemplate(FileTemplate ft) {
        String name = StringUtils.stringFormat(25, "{}:", ft.getId());
        log.info("       - {} {}", name, storeMessage);
    }

    @SuppressFBWarnings(value = {"RV_RETURN_VALUE_IGNORED_BAD_PRACTICE"},
            justification = "We don't really have to do something with the result of 'directory.mkdirs()'")
    private void storeData(Generator gen, String data) {
        FileTemplate currentFT = gen.getCurrentFileTemplate();
        Entity ett = gen.getCurrentEntity();

        String dir = currentFT.generateDirectoryName(gen.getConfig(), ett.getName(), gen.getLanguage());
        String fileName = currentFT.generateFileName(ett.getName(), gen.getLanguage());

        boolean blockGenerated = FileGeneratorAssistant.isBlockFixed();

        //String objectName = currentFT.generateObjectName(ett.getName());
        String path = Globals.concatDirectoryNames(dir, fileName);
        File directory = new File(dir);
        directory.mkdirs();

        boolean override = currentFT.isAlwaysGenerated() || (currentFT.isRegenerateBlock() && blockGenerated);
        boolean created = FileAssistant.writeFile(path, data, override);

        if (created) {
            if (blockGenerated) {
                storeMessage = "+ regenerated block";
            } else {
                storeMessage = "+ created";
            }
            filesCreated.add(path);
        } else {
            storeMessage = "- already exist";
        }

        //log.info("{}{}", message, objectName);
    }

    public void logFilesCreated() {
        if (log.isInfoEnabled()) {

            Collections.sort(filesCreated);
            String str = StringUtils.concatAsString(filesCreated, "\n", "    ", "");

            log.info("=================================================");
            log.info("Files Created:");
            log.info("=================================================");
            log.info("\n{}", str);
        }
    }
}
