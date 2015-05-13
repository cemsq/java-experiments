import java.io.File;

/**
 *
 */
public class DirectoryWalkerBase {

    private boolean skipDir;

    public boolean isSkipDir() {
        return skipDir;
    }

    public void setSkipDir(boolean skipDir) {
        this.skipDir = skipDir;
    }

    public void currentDirectory(File dir) {

    }

    public void processCurrentFile(File file) {

    }
}
