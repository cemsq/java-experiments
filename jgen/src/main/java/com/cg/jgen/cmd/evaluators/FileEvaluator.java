package com.cg.jgen.cmd.evaluators;

/**
 *
 */
public class FileEvaluator implements ArgumentEvaluator {
    FileExistenceEvaluator existence;
    FileExtensionEvaluator extension;

    public FileEvaluator(String extension) {
        existence = new FileExistenceEvaluator();
        this.extension = new FileExtensionEvaluator(extension);
    }

    @Override
    public boolean evaluate(String arg) {
        return existence.evaluate(arg) && extension.evaluate(arg);
    }
}
