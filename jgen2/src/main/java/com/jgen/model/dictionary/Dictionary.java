package com.jgen.model.dictionary;

import com.cgm.storm.utils.common.StringUtils;
import com.jgen.importer.DictionaryImporter;

import java.util.ArrayList;
import java.util.List;

/**
 * A Singleton object that serves as translator between Pdex and JGen.
 * <p/>
 * Every word in the dictionary is a tuple containing the Pdex and JGen value.
 * The PDexValue is case insensitive
 */
public class Dictionary {
    private static Dictionary instance;

    private List<Word> words = new ArrayList<>();

    private Dictionary() {

    }

    /**
     * @return the Singleton instance
     */
    public static Dictionary getInstance() {
        if (instance == null) {
            instance = new Dictionary();
            DictionaryImporter.loadDefault();
        }

        return instance;
    }

    /**
     * Delete all Dictionary words
     */
    public void reset() {
        words.clear();
    }

    /**
     * Add a new word to the dictionary. The new word must be not null.
     *
     * @param word a new word
     */
    public Word addWord(Word word) {
        if (word != null) {
            words.add(word);
            return word;
        }
        return null;
    }

    /**
     * Add a new word to the dictionary.
     *
     * @param pdexValue how pdex write a word
     * @param jgenValue how jgen write a word
     */
    public Word addWord(String pdexValue, String jgenValue) {
        Word word = Word.create(pdexValue, jgenValue);
        return addWord(word);
    }

    /**
     * Returns the jgenValue given a pdexValue.
     * <p/>
     * If the pdexValue is empty or null, it returns an empty String.
     * If the pdexValue is not found, it returns the pdexValue
     *
     * @param pdexValue a pdexValue to translate
     * @return jgenValue for a pdexValue
     */
    public String getJGenValueByPDexValue(String pdexValue) {
        if (!StringUtils.isValid(pdexValue)) {
            return "";
        }

        for (Word word : words) {
            if (word.equalsPdexValue(pdexValue)) {
                return word.getJgenValue();
            }
        }

        return pdexValue;
    }

    /**
     * @return the number words in the dictionary
     */
    public int size() {
        return words.size();
    }

}
