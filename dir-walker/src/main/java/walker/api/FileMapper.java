package walker.api;

import java.io.File;

public interface FileMapper<T> {
    FileMapper<File> DEFAULT = file -> file;

    T map(File file);
}
