package com.cg.jgen.core.generator;

import com.cg.jgen.core.Entity;
import com.cg.jgen.core.Index;
import com.cg.jgen.core.Member;
import com.cg.jgen.utils.ClassAssistant;
import com.cg.jgen.utils.TsTypeDecoder;
import com.cgm.storm.utils.common.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 *
 */
public class Injector {

    public static List<Map<String, String>> getInjectableIds(Entity entity) {
        List<Map<String, String>> list = new ArrayList<>();
        Index pk = entity.getPrimaryKey();

        if (pk == null) {
            return list;
        }

        for (String memberName : pk.getColumns()) {
            Member member = entity.getMemberByName(memberName, true);
            list.add(getInjectableMember(member));
        }

        return list;
    }

    public static List<Map<String, String>> getInjectableMembersWithoutIds(Entity entity) {
        List<Map<String, String>> list = new ArrayList<>();
        Index pk = entity.getPrimaryKey();

        for (Member member : entity.getMemberList()) {
            if (!pk.hasColumn(member.getAttributeValue("name"))) {
                list.add(getInjectableMember(member));
            }
        }

        return list;
    }

    /*public static List<Map<String, String>> getInjectableMembers(Class entityClazz) {
        List<Map<String, String>> list = new ArrayList<>();

        for (Field field : ClassAssistant.getMembers(entityClazz)) {
            Member member = new Member();
            String type = Globals.getStringAfterLastChar(field.getType().getName(), '.');

            member.addAttribute("name", field.getName());
            member.addAttribute("type", type);
            if (type.equals("String")) {
                Element element = field.getAnnotation(Element.class);
                if (element != null) {
                    member.addAttribute("length", String.valueOf(element.length()));
                }
            }

            list.add(getInjectableMember(member));
        }

        return list;
    }*/

    public static List<Map<String, String>> getInjectableMembers(Entity entity) {
        List<Map<String, String>> list = new ArrayList<>();

        for (Member member : entity.getMemberList()) {
            list.add(getInjectableMember(member));
        }

        return list;
    }

    public static Map<String, String> getInjectableMember(Member member) {
        member.interpretAnnotation();

        boolean defaultValueAssigned = false;

        Map<String, String> map = new HashMap<>();
        for (Member.Attribute att : member.getAttributeList()) {
            if (att.nameIs("defaultValue") && defaultValueAssigned) {
                continue;
            }

            map.put(att.getName(), att.getValue());
            if (att.nameIs("type")) {
                String type = TsTypeDecoder.toTypeScript(att.getValue());

                String def = member.getAttributeValue("defaultValue");
                String defaultValue = StringUtils.getValidString(def, "");
                switch (type) {
                    case "string":
                        defaultValue = "'" + defaultValue + "'";
                        break;
                    case "number":
                        defaultValue = defaultValue.equals("")? "0": defaultValue;
                        break;
                    default:
                        defaultValue = "null";
                }

                map.put("tsType", type);
                map.put("defaultValue", defaultValue);
                defaultValueAssigned = true;
            }
        }

        return map;
    }

    private static NameInMapComparator nameInMapComparator = new NameInMapComparator();

    public static List<Map<String, String>> getInjectableMethods(Class clazz) {
        List<Map<String, String>> list = new ArrayList<>();

        if (clazz == null) {
            return list;
        }

        List<Method> methods = ClassAssistant.getMethods(clazz);

        for (Method method : methods) {
            Map<String, String> map = new HashMap<>();

            String returnType = ClassAssistant.getReturnType(method);

            map.put("name", method.getName());
            map.put("return", TsTypeDecoder.toTypeScript(returnType));
            map.put("parameters", ClassAssistant.getParameters(method));

            list.add(map);
        }

        Collections.sort(list, nameInMapComparator);

        return list;
    }


    private static class NameInMapComparator
            implements Comparator<Map<String, String>>, Serializable {
        @Override
        public int compare(Map<String, String> o1, Map<String, String> o2) {
            return o1.get("name").compareTo(o2.get("name"));
        }
    }
}
