package com.cg.jgen.core.generator;

import com.cg.jgen.core.Config;
import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.Language;
import com.cg.jgen.utils.ClassAssistant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class TsGenerator extends Generator {
    public TsGenerator(Config config) {
        super(Language.TYPE_SCRIPT, config);
    }

    @Override
    protected void inject(FileTemplate ft, Entity entity) {
        super.inject(ft, entity);

        FileTemplate entityFT = FileTemplate.getFileTemplate("Entity", true);
        FileTemplate service = FileTemplate.getFileTemplate("EntityService", true);

        String entityName = entity.getName();
        String serviceName = service.generateObjectName(entityName);

        Class ettyClass = ClassAssistant.loadClassFromJar(config, entityFT, entityName, false);
        Class servClass = ClassAssistant.loadClassFromJar(config, service, entityName, true);

        List<Map<String, String>> members = Injector.getInjectableMembers(entity);
        List<Map<String, String>> noIds = Injector.getInjectableMembersWithoutIds(entity);
        if (ClassAssistant.optimisticLocking(ettyClass)) {
            Map<String, String> sysVersion = new HashMap<>();
            sysVersion.put("name", "sys_version");
            sysVersion.put("tsType", "number");
            sysVersion.put("defaultValue", "0");

            members.add(sysVersion);
            noIds.add(sysVersion);
        }

        velocity.put("Members", members);
        velocity.put("EntityIds", Injector.getInjectableIds(entity));
        velocity.put("EntityNoIds", noIds);
        velocity.put("ServiceName", serviceName);
        velocity.put("ServiceMethods", Injector.getInjectableMethods(servClass));
    }

}
