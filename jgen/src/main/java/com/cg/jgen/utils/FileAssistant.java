package com.cg.jgen.utils;

import com.cg.jgen.core.exception.JGenException;
import com.cg.jgen.core.generator.VelocityAdapter;
import com.cgm.storm.utils.common.StringUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Assistant class to write and read files.
 */
public class FileAssistant {
    private static final Logger log = LoggerFactory.getLogger(FileAssistant.class);
    private static final VelocityAdapter velocity = new VelocityAdapter();

    public static void validateExistence(String path, String message) {
        File file = new File(path);
        if (!file.exists()) {
            throw new JGenException(message);
        }
    }

    public static void validateTemplateExistence(String path, String message) {
        try {
            velocity.render(path);
        } catch (ResourceNotFoundException e) {
            throw new JGenException(message, e);
        }

    }

    public static boolean writeFile(String fileName, String data, boolean override) {
        if (override) {
            return writeNewFile(fileName, data);
        }
        return writeIfNotExist(fileName, data);
    }

    public static boolean writeIfNotExist(String fileName, String data) {
        File file = new File(fileName);

        boolean created = false;
        if (!file.exists()) {
            created = writeNewFile(fileName, data);
        }

        return created;
    }

    public static boolean writeNewFile(String fileName, String data) {
        if (!StringUtils.isValid(data)) {
            log.warn("Empty data. This file will be not created: " + fileName);
            return false;
        }

        // try-with resources
        try (PrintWriter pw = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8")))) {
            pw.println(data);
        } catch (Exception e) {
            throw new JGenException("Writing file: " + fileName, e);
        }

        return true;
    }

    public static String readFileAsString(String fileName) {
        StringBuilder text = new StringBuilder("");
        // try-with resources
        try (BufferedReader br = new BufferedReader(new BufferedReader
                (new InputStreamReader(new FileInputStream(fileName), "UTF-8")))) {
            String line = br.readLine();
            int i = 0;
            while (line != null) {
                // more than 1 element
                if (i > 0) {
                    text.append(System.lineSeparator());
                }
                text.append(line);

                line = br.readLine();
                i++;
            }
        } catch (IOException e) {
            throw new JGenException("Error reading file: " + fileName, e);
        }

        return text.toString();
    }
}
