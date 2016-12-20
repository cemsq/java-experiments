package test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class DirectoryScanner {

    private DirectoryWalkerBase walker;
    private static OnlyFiles onlyFiles = new OnlyFiles();
    private static OnlyDirectories onlyDirectories = new OnlyDirectories();

    public final void analyze(String path, DirectoryWalkerBase walker) {
        this.walker = walker;
        analyze(new File(path));
    }

    private void analyze(File actual) {
        //System.out.println(deep + dir.getParent() + "::" + dir.getName());

        if (actual.isDirectory()) {
            walker.setSkipDir(false);
            walker.currentDirectory(actual);
            if (walker.isSkipDir()) {
                return;
            }

            for (File file : getFiles(actual)) {
                walker.processCurrentFile(file);
            }

            for (File dir : getDirectories(actual)) {
                analyze(dir);
            }
        }
    }

    public static List<File> getDirectories(File dir) {
        return listFiles(dir, onlyDirectories);
    }

    public static List<File> getFiles(File file) {
        return listFiles(file, onlyFiles);
    }

    public static List<File> listFiles(File file, FileFilter filter) {
        return Arrays.asList(file.listFiles(filter));
    }

    public static class OnlyFiles implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            return !pathname.isDirectory();
        }
    }
    public static class OnlyDirectories implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            return pathname.isDirectory();
        }
    }
}
