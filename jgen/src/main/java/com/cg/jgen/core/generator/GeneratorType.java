package com.cg.jgen.core.generator;

import com.cg.jgen.core.Config;
import com.cg.jgen.core.exception.JGenException;

/**
 *
 */
public enum GeneratorType {
    JAVA("Java"),
    JAVA_ENUM("JavaEnum"),
    TYPE_SCRIPT("TypeScript"),
    XML("XML");

    private String name;

    GeneratorType(String name) {
        this.name = name;
    }

    public static GeneratorType getType(String name) {
        for (GeneratorType type : GeneratorType.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }

        throw new JGenException("Generator name not found: " + name);
    }

    public Generator createInstance(Config config) {
        switch (this) {
            case JAVA:
                return new JavaGenerator(config);

            case JAVA_ENUM:
                return new JavaEnumGenerator(config);

            case XML:
                return new XmlGenerator(config);

            case TYPE_SCRIPT:
                return new TsGenerator(config);
        }

        throw new JGenException("Unreachable core");
    }
}
