package com.jgen.importer;

import com.jgen.model.dictionary.Dictionary;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class DictionaryImporterTest {

    @Test
    public void shouldLoadDictionaryAutomatically() {
        Dictionary dictionary = Dictionary.getInstance();

        Assert.assertTrue(dictionary.size() > 0);
    }
}
