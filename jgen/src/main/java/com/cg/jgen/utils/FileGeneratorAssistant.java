package com.cg.jgen.utils;

import com.cg.jgen.core.exception.JGenException;

/**
 *
 */
public class FileGeneratorAssistant {

    private static int ini;
    private static int end;

    private static boolean blockFixed;

    public static String regenerateBlock(String dir, String file, String data, boolean regenerateBlock, boolean isAlwaysGenerate) {
        blockFixed = false;
        try {
            if (regenerateBlock && !isAlwaysGenerate) {
                String path = Globals.concatDirectoryNames(dir, file);
                data = fixRegeneratedBlockFromFile(path, data);
            }
        } catch (JGenException e) {
            //log.warn("Failed when trying to render 'regenerated block' in: {}. Generating file for first time? it's ok", currentFT.generateObjectName(currentEntityName));
        }

        return data;
    }

    private static String fixRegeneratedBlockFromFile(String fileName, String replacementData) {
        String dataFromDisk = FileAssistant.readFileAsString(fileName);
        return fixRegeneratedBlockFromString(dataFromDisk, replacementData);
    }

    private static String fixRegeneratedBlockFromString(String dataToFix, String replacementData) {
        String replacement = extractRegeneratedBlock(replacementData);
        String oldBlock = extractRegeneratedBlock(dataToFix);

        if (oldBlock.equals("")) {
            return dataToFix;
        }

        String fixed = dataToFix.substring(0, ini);
        fixed += replacement;
        fixed += dataToFix.substring(end, dataToFix.length());

        blockFixed = true;

        return fixed;
    }

    public static String extractRegeneratedBlock(String data) {
        String iniTag = "<regenerate>";
        String endTag = "</regenerate>";

        ini = data.indexOf(iniTag);
        end = data.indexOf(endTag);

        if (ini < 0 || end < 0 || end < ini) {
            return "";
        }

        ini = data.lastIndexOf("//", ini);
        end = end + endTag.length();

        //String replacement = data.substring(ini, end);
        return data.substring(ini, end);
    }


    public static boolean isBlockFixed() {
        return blockFixed;
    }
}
