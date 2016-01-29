import org.testng.Assert;
import org.testng.annotations.Test;
import walker.*;
import walker.DirectoryWalker;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 *
 */
public class DirectoryWalkerTest {

    public static class JavaFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith("java");
        }
    }

    public static class AnnotatedFilter extends JavaFilter{
        private String path;

        public void setPath(String path) {
            this.path = new File(path).getPath();
        }

        @Override
        public boolean accept(File pathname) {
            if (super.accept(pathname)) {
                String className = className(pathname.getPath());
                Class clazz = loadClass(className);
                if (clazz != null && clazz.getAnnotation(TestAnnotation.class) != null) {
                    return true;
                }
            }

            return false;
        }

        private Class<?> loadClass(String name) {
            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                return null;
            }
        }

        private String className(String actual) {
            return actual.substring(path.length() + 1, actual.length() - 5).replace("\\", ".");
        }
    }

    public static class ClassAnnotatedWalker {
        public List<File> analyze(String path) {
            AnnotatedFilter filter = new AnnotatedFilter();
            filter.setPath(path);

            DirectoryWalker walker = new DirectoryWalker();
            walker.setFilter(filter);

            return walker.analyze(path);
        }
    }
    @Test
    public void test() {
        //new DirectoryScanner().analyze("dir-walker/src/main/resources/templates", new DirectoryWalker());

        ClassAnnotatedWalker walker = new ClassAnnotatedWalker();
        List<File> list = walker.analyze("dir-walker/src/main/java");

        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Assert.assertEquals(list.size(), 1);
        System.out.println(list);
    }
}

