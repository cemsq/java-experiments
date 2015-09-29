package com.cg.jgen.core.importer;

import com.cg.jgen.core.Dictionary;
import com.cg.jgen.core.Word;
import com.cg.jgen.core.exception.JGenException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Reads the dictionary file in xml format
 *
 * @see com.cg.jgen.core.Dictionary
 */
public class DictionaryImporter {
    private static final Logger log = LoggerFactory.getLogger(DictionaryImporter.class);

    /**
     * Import the Dictionary from a xml File
     *
     * @param xmlDictionary xml file path
     * @return Dictionary
     * @throws com.cg.jgen.core.exception.JGenException when the file is not found, malformed, or if a pdex-jgenValue is empty or not defined
     */
    public static Dictionary importDictionary(String xmlDictionary) {
        log.info("===== Importing Dictionary =========================================");
        log.info("file: {} ", xmlDictionary);

        Dictionary dic;
        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new File(xmlDictionary));

            Element root = doc.getRootElement();
            List<Element> words = root.getChildren("Word");
            dic = buildDictionary(words);

        } catch (JDOMException | IOException e) {
            throw new JGenException("Error importing '" + xmlDictionary + "' file", e);
        }
        log.info("import done.\n");
        return dic;
    }

    /**
     * Build the Dictionary from a Xml Word List.
     *
     * @param words a list of Word elements in the xml file
     * @return the constructed Dictionary
     * @throws com.cg.jgen.core.exception.JGenException if a Word element has no pdex nor jgenValue defined.
     *                                                  It also throws if pdex or jgen value is empty
     */
    private static Dictionary buildDictionary(List<Element> words) {
        Dictionary dic = Dictionary.getInstance();
        dic.reset();

        if (words.size() == 0) {
            log.warn("No words are found");
            return dic;
        }

        for (Element word : words) {
            String pdexValue = word.getAttributeValue("PDexValue");
            String jgenValue = word.getAttributeValue("JGenValue");

            Word wordCreated = dic.addWord(pdexValue, jgenValue);
            log.info("    {}", wordCreated);
        }
        int size = dic.size();
        log.info("{} element{} created", size, (size > 1 || size == 0) ? "s" : "");

        return dic;
    }
}
