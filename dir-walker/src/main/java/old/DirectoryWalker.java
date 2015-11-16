package old;

import com.google.common.base.Strings;

import java.io.File;

/**
 *
 */
public class DirectoryWalker extends DirectoryWalkerBase {

    String root;
    String module;
    String source;
    String pkg;
    boolean packageFound;
    boolean apiModule;

    @Override
    public void currentDirectory(File dir) {
        pkg = "";
        packageFound = false;
        for (File file : DirectoryScanner.getFiles(dir)) {
            String name = file.getName();
            String parent = file.getParent();
            boolean sentinel = false;

            switch (name) {
                case "root":
                    root = parent;
                    System.out.println("Root: " + root);
                    sentinel = true;
                    break;

                case "api-module":
                case "module":
                    apiModule = name.equals("api-module");
                    module = getDifference(parent, root);
                    System.out.println("   Module: " + module);
                    sentinel = true;
                    break;

                case "source":
                    source = getDifference(parent, root, module);
                    System.out.println("      Source: " + source);
                    sentinel = true;
                    break;
            }

            if (sentinel) {
                break;
            }
        }
    }

    @Override
    public void processCurrentFile(File file) {
        String name = file.getName();
        String parent = file.getParent();

        int pos = name.lastIndexOf('.') + 1;
        String extension = "";

        if (pos > 0) {
            extension = name.substring(pos);
        }

        switch (extension) {
            case "vm":
                if (!packageFound && !apiModule) {
                    packageFound = true;
                    pkg = getDifference(parent, root, module, source);
                    System.out.println("          Package: " + pkg);
                }
                System.out.println("             Template: " + name);
                break;
        }
    }

    public String getDifference(String large, String ...words) {
        int length = 0;
        int count = 0;
        for (String s : words) {
            if (!Strings.isNullOrEmpty(s)) {
                length += s.length();
                count++;
            }
        }
        length += count;
        return length < large.length() ? large.substring(length) : "";
    }
}
