package com.jgen;

import com.jgen.exception.JgenException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 */
public class FileLoader {

    public static File tryToLoadFromResource(String path) {
        URL url = ClassLoader.getSystemResource(path);
        if (url == null) {
            return new File(path);
        }

        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new JgenException("File not found: " + path);
        }
    }
}
