package com.cg.jgen.cmd.evaluators;

import java.io.File;

/**
 *
 */
public class FileExistenceEvaluator implements ArgumentEvaluator {
    public FileExistenceEvaluator() {

    }

    @Override
    public boolean evaluate(String arg) {
        File file = new File(arg);

        return file.exists();
    }
}
