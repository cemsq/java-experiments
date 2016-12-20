package walker.filter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import walker.core.BoolOperation;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.function.BiPredicate;


public class Filters {

    public static final FileFilter JAVA = fileExtensionFilter("java");
    public static final FileFilter DEFAULT = (file) -> true;

    public static FileFilter fileExtensionFilter(String ext, String ...exts) {
        return fileExtensionFilter(BoolOperation.OR, ext, exts);
    }

    public static FileFilter fileExtensionFilter(BoolOperation op, String ext, String ...exts) {
        return new ElementsFilter<>((file, e) -> file.getName().endsWith(e), toList(ext, exts));
    }

    public static FileFilter filterContainer(FileFilter el, FileFilter ...elements) {
        return filterContainer(BoolOperation.OR, el, elements);
    }

    public static FileFilter filterContainer(BoolOperation op, FileFilter el, FileFilter ...elements) {
        return new ElementsFilter<>((file, filter) -> filter.accept(file), op, toList(el, elements));
    }

    @SafeVarargs
    public static FileFilter javaAnnotatedFilter(Class<? extends Annotation> e, Class<? extends Annotation>... elements) {
        return new ElementsFilter<>(Filters::check, toList(e, elements));
    }

    private static boolean check(File file, Class<? extends Annotation> annotation) {
        if (JAVA.accept(file)) {
            String name = extractClassName(file);
            Class<?> aClass = loadClass(name);
            if (aClass != null) {
                return aClass.getAnnotation((Class)annotation) != null;
            }
        }

        return false;
    }

    private static Class<?> loadClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load class: {}" + name);
            return null;
        }
    }

    private static String extractClassName(File file) {
        String main = "src\\main\\java\\";
        String test = "src\\test\\java\\";
        String source;

        String fileName = file.getAbsolutePath();

        int ini = fileName.indexOf(main);
        if (ini >= 0) {
            source = main;
        } else {
            ini = fileName.indexOf(test);
            if (ini >= 0) {
                source = test;
            } else {
                return fileName;
            }
        }

        return fileName.substring(ini + source.length(), fileName.length() - 5).replace("\\", ".");
    }

//    @Slf4j
//    public static class JavaAnnotatedFilter extends ElementsFilter<Class<? extends Annotation>> implements FileFilter {
//
//        @SafeVarargs
//        public JavaAnnotatedFilter(Class<? extends Annotation> e, Class<? extends Annotation>... elements) {
//            super(e, elements);
//        }
//
//        @SafeVarargs
//        public JavaAnnotatedFilter(BoolOperation op, Class<? extends Annotation> e, Class<? extends Annotation>... elements) {
//            super(op, e, elements);
//        }
//
//        @Override
//        public boolean internalAccept(File file, Class<? extends Annotation> annotation) {
//            if (JAVA.accept(file)) {
////                loadClass()
//                annotation.getAnnotations();
//            }
//
//            return false;
//        }
//
//
//        private Class<?> loadClass(String name) {
//            try {
//                return Class.forName(name);
//            } catch (ClassNotFoundException e) {
//                log.error("Could not load class: {}" + name);
//                return null;
//            }
//        }
//    }

//    public static class FilterContainer extends ElementsFilter<FileFilter> implements FileFilter {
//        private static final BiPredicate<File, FileFilter> predicate = (file, filter) -> filter.accept(file);
//
//        public FilterContainer() {
//            this(Lists.newArrayList());
//        }
//
//        public FilterContainer(List<FileFilter> elements) {
//            super(predicate, elements);
//        }
//
//        public FilterContainer(BoolOperation op, List<FileFilter> elements) {
//            super(predicate, op, elements);
//        }
//
//        public boolean addFilter(FileFilter filter) {
//            return add(filter);
//        }
//
//        @Override
//        public boolean accept(File pathname) {
//            return super.accept(pathname);
//        }
//    }

    public static class ElementsFilter<T> implements FileFilter {
        private BoolOperation op;
        private List<T> elements;
        private BiPredicate<File, T> predicate;

        public ElementsFilter(BiPredicate<File, T> predicate, List<T> elements) {
            this(predicate, BoolOperation.OR, elements);
        }

        public ElementsFilter(BiPredicate<File, T> predicate, BoolOperation op, List<T> elements) {
            this.op = op;
            this.predicate = predicate;
            this.elements = elements;
        }

        @Override
        public boolean accept(File pathname) {

            return pathname != null && op.check(elements, (element) -> predicate.test(pathname, element));
        }
    }

    @SafeVarargs
    private static<T> List<T> toList(T el, T ... elements) {
        Preconditions.checkNotNull(el, "first element cannot be null");

        List<T> list = Lists.newArrayList(elements);
        list.add(el);

        return list;
    }
}
