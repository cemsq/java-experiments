package com.jgen.importer;

import com.jgen.FileLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 */
public class FileLoaderTest {

    @Test
    public void shouldLoadFromResource() {
        File file = FileLoader.tryToLoadFromResource("pom.xml");

        try {
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(file);
        System.out.println(file);
    }
}
