package com.cg.jgen.cmd.evaluators;

import com.cgm.storm.utils.common.StringUtils;

/**
 *
 */
public class FileExtensionEvaluator implements ArgumentEvaluator {
    private String extension;

    public FileExtensionEvaluator(String extension) {
        StringUtils.validateString(extension, "'extension' must be not null or not empty");
        this.extension = extension;
    }

    @Override
    public boolean evaluate(String arg) {
        if (!StringUtils.isValid(arg)) {
            return false;
        }
        String ext = StringUtils.getStringAfterLastChar(arg, '.');

        return extension.equals(ext);
    }
}
