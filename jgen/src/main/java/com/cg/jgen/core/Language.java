package com.cg.jgen.core;

/**
 *
 */
public enum Language {
    JAVA("Java", "java"),
    TYPE_SCRIPT("TypeScript", "ts"),
    JAVA_SCRIPT("JavaScript", "js"),
    XML("XML", "xml"),
    GENERIC("Generic", "txt"),
    UNSUPPORTED("Unsupported", "txt");

    private String name;
    private String extension;

    Language(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }
}
