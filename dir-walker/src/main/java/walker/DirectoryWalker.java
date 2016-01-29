package walker;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DirectoryWalker {

    private String path;
    private List<File> files;
    private FileFilter filter = new DefaultFilter();

    public List<File> analyze(String path) {
        this.path = path;
        files = new ArrayList<>();

        analyse(new File(path));

        return files;
    }

    private void analyse(File file) {
        if (file.isDirectory()) {
            File list[] = file.listFiles();
            for (File f : list) {
                analyse(f);
            }
        } else if (filter.accept(file)){
            files.add(file);
        }
    }

    public void setFilter(FileFilter filter) {
        this.filter = filter;
    }

    private class DefaultFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            return true;
        }
    }
}

