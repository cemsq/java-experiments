package com.cg.jgen.core.generator;

import com.cg.jgen.core.Config;
import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.Language;
import com.cg.jgen.utils.GeneratorAssistant;

/**
 *
 */
public class JavaGenerator extends Generator {
    public JavaGenerator(Config config) {
        super(Language.JAVA, config);
    }

    @Override
    protected void inject(FileTemplate ft, Entity entity) {
        super.inject(ft, entity);

        String rootPackage = config.getRootPackage();

        velocity.put("Package", GeneratorAssistant.generatePackage(rootPackage, ft.getRelativePackage(), entity.getName()));
        velocity.put("Imports", GeneratorAssistant.generateImports(ft, entity, rootPackage));
        velocity.put("Members", Injector.getInjectableMembers(entity));
        velocity.put("Indexes", entity.getIndexes());
        velocity.put("pkName", entity.getPrimaryKey().getName());
        velocity.put("pkElements", entity.getPrimaryKey().columnsAsString());
        //velocity.put("DatabaseTable", entity.getDatabaseTableAnnotation());
    }
}
