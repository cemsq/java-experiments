package datastore;

import com.google.common.base.Preconditions;

import java.net.URL;

/**
 *
 */
public class FileResources {
    private static ClassLoader classLoader = FileResources.class.getClassLoader();

    public static String getFileName(String name) {
        URL url = classLoader.getResource(name);
        Preconditions.checkArgument(url != null, "file not found: " + name);

        return url.getFile();
    }
}
