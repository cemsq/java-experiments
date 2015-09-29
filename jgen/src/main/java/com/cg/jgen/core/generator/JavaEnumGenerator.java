package com.cg.jgen.core.generator;

import com.cg.jgen.core.Config;
import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;

/**
 *
 */
public class JavaEnumGenerator extends JavaGenerator {
    public JavaEnumGenerator(Config config) {
        super(config);
    }

    @Override
    protected boolean isEntityEnabled(Entity entity) {
        return entity.isValueTable();
    }

    @Override
    protected void inject(FileTemplate ft, Entity entity) {
        super.inject(ft, entity);

        velocity.put("Values", entity.getEnumValues());
    }
}
