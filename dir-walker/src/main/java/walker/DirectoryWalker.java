package walker;

import com.google.common.collect.Lists;
import walker.api.FileMapper;
import walker.filter.Filters;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 *
 */
public class DirectoryWalker {

    public DirectoryWalker() {

    }

    public List<File> analyze(String path) {
        return analyze(path, FileMapper.DEFAULT);
    }

    public<T> List<File> analyze(String path, FileFilter filter) {
        return analyze(path, filter, FileMapper.DEFAULT);
    }

    public<T> List<T> analyze(String path, FileMapper<T> mapper) {
        return analyze(path, Filters.DEFAULT, mapper);
    }

    public<T> List<T> analyze(String path, FileFilter filter, FileMapper<T> mapper) {
        List<T> collected = Lists.newArrayList();

        internalAnalyze(filter, new File(path), collected, mapper);

        return collected;
    }

    private<T> void internalAnalyze(FileFilter evaluator, File file, List<T> collected, FileMapper<T> mapper) {
        if (file.isDirectory()) {
            for (File f : listFiles(file)) {
                internalAnalyze(evaluator, f, collected, mapper);
            }

        } else if (evaluator.accept(file)){
            collected.add(mapper.map(file));
        }
    }

    private File[] listFiles(File file) {
        File[] files = null;

        if (file != null) {
            files = file.listFiles();
        }

        if (files == null) {
            files = new File[]{};
        }

        return files;
    }
}

