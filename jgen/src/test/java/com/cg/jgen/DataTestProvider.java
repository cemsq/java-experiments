package com.cg.jgen;

import com.cg.jgen.core.*;

import java.util.Arrays;

/**
 *
 */
public class DataTestProvider {
    private static Entity ettPersonTest;

    static {
        createFileTemplates();

        ettPersonTest = new Entity("PersonTest");
        ettPersonTest.setDescription("A Person object Test");

        Member member = new Member();
        member.addAttribute("name", "id");
        member.addAttribute("type", "String");
        member.addAttribute("length", "18");
        ettPersonTest.addMember(member);

        member = new Member();
        member.addAttribute("name", "firstName");
        member.addAttribute("type", "String");
        member.addAttribute("length", "25");
        ettPersonTest.addMember(member);

        member = new Member();
        member.addAttribute("name", "lastName");
        member.addAttribute("type", "String");
        member.addAttribute("length", "25");
        ettPersonTest.addMember(member);

        Index pk = new Index("PK_PERSON_TEST", "true", "true");
        Index index = new Index("IX_PERSON_TEST", "false", "true");

        pk.addColumn("id");
        index.addColumn("firstName");

        ettPersonTest.addIndex(pk);
        ettPersonTest.addIndex(index);
    }

    public static FileTemplate getFileTemplate(String id) {
        return FileTemplate.getFileTemplate(id, true);
    }

    public static Entity getEntityPersonTest() {
        return ettPersonTest;
    }

    public static void createFileTemplates() {
        // for Generic Generator
        FileTemplate base = new FileTemplateBuilder()
                .id("GeneratorTestTemplate")
                .fileName("{Entity}Test")
                .template("generator/GeneratorBase.vm")
                .headerTemplate("SimpleJavaHeader.vm")
                .relativePackage("b")
                .build();

        // for Java
        FileTemplate java = new FileTemplateBuilder()
                .id("JavaGeneratorTemplate")
                .fileName("{Entity}Test")
                .template("generator/JavaGenerator.vm")
                .headerTemplate("SimpleJavaHeader.vm")
                .relativePackage("b")
                .build();

        FileTemplate dep = new FileTemplateBuilder()
                .id("ADependencyClassTemplate")
                .fileName("ADependencyClass")
                .template("generator/JavaGenerator.vm")
                .headerTemplate("SimpleJavaHeader.vm")
                .relativePackage("c")
                .build();
        java.setDependencies(Arrays.asList(dep));

        // for Java Enumerations
        FileTemplate enums = new FileTemplateBuilder()
                .id("JavaEnumGeneratorTemplate")
                .fileName("{Entity}Test")
                .template("generator/JavaEnumGenerator.vm")
                .headerTemplate("SimpleJavaHeader.vm")
                .relativePackage("b")
                .build();
        enums.setDependencies(Arrays.asList(dep));

        // for XML
        FileTemplate xml = new FileTemplateBuilder()
                .id("XmlGeneratorTemplate")
                .fileName("{Entity}Test")
                .template("generator/XmlGenerator.vm")
                .headerTemplate("SimpleJavaHeader.vm")
                .relativePackage("b")
                .resourceCatalogId("aResourceCatalogId")
                .build();

        // for TypeScript
        FileTemplate ts = new FileTemplateBuilder()
                .id("TsGeneratorTemplate")
                .fileName("{Entity}")
                .module("")
                .relativePackage("core.fake")
                .template("generator/TsGenerator.vm")
                .headerTemplate("SimpleJsHeader.vm")
                .relativePackage("b")
                .build();

        FileTemplate service = new FileTemplateBuilder()
                .id("EntityService")
                .fileName("{Entity}Service")
                .module("")
                .relativePackage("core.fake")
                .template("generator/JavaGenerator.vm")
                .headerTemplate("SimpleJavaHeader.vm")
                .build();

        FileTemplate entity = new FileTemplateBuilder()
                .id("Entity")
                .fileName("{Entity}")
                .module("")
                .relativePackage("core.fake")
                .template("templates/java/Entity.vm")
                .headerTemplate("templates/emptyHeader.vm")
                .build();
    }
}
