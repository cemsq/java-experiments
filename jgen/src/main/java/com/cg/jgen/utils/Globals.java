package com.cg.jgen.utils;

import com.cg.jgen.core.exception.JGenException;
import com.cgm.storm.utils.common.BatchExecutor;
import com.cgm.storm.utils.common.StringUtils;
import com.cgm.storm.utils.common.exception.BatchExecutorException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Globals {

    private Globals() {
    }

    public static <T> void addIfNotListed(List<T> list, T element) {
        if (!isListed(list, element)) {
            list.add(element);
        }
    }

    public static <T> boolean isListed(List<T> list, T element) {
        if (element == null || list == null) {
            return false;
        }

        for (T s : list) {
            if (s.equals(element)) {
                return true;
            }
        }

        return false;
    }

    public static <T> List<T> getArraysAsArrayList(T[] array1, T[] array2) {
        List<T> destiny = new ArrayList<>();

        destiny.addAll(Arrays.asList(array1));
        destiny.addAll(Arrays.asList(array2));

        return destiny;
    }

    public static String fixBackslashWithSlash(String pathWithBackSlash) {
        String path = pathWithBackSlash.replace('\\', '/');
        StringBuilder sb = new StringBuilder("");

        int i = 0;
        int n = path.length();
        boolean sentinel = false;
        while (i < n) {
            char c = path.charAt(i);

            if (c == '/') {
                sentinel = true;
            } else {
                if (sentinel) {
                    sb.append("/");
                    sentinel = false;
                }
                sb.append(c);
            }
            i++;
        }

        return sb.toString();
    }

    /**
     * Example:
     * strWithExp = "an{entity.name}ServiceApi"
     * replace    = "Article"
     * <p/>
     * name will be:
     * "anArticleServiceApi"
     */
    public static String replaceEntityExpression(String strWithExp, String replace) {
        String expPascalEntity = "\\{Entity\\}";
        String expCamelEntity = "\\{entity\\}";

        String ret = strWithExp.replaceAll(expPascalEntity, Utility.pascalCase(replace));
        return ret.replaceAll(expCamelEntity, Utility.camelCase(replace));
    }

    public static String concatDirectoryNames(String... dirs) {
        return StringUtils.concatStringsWith("/", dirs);
    }

    public static String concatObjectNames(String... packages) {
        return StringUtils.concatStringsWith(".", packages);
    }

    @SuppressFBWarnings(value = {"RV_RETURN_VALUE_IGNORED_BAD_PRACTICE"}, justification = "We don't really have to do something with the result of 'file.delete()'")
    public static void compile(String appPath, List<String> modules) {

        List<String> sentences = makeSentenceToCompile(appPath, modules);
        for (String sentence : sentences) {
            try {
                BatchExecutor.exec(sentence, true);
            } catch (BatchExecutorException e) {
                throw new JGenException("Unable to compile: " + sentence, e);
            }
        }
    }

    public static List<String> makeSentenceToCompile(String appPath, List<String> modules) {
        List<String> sentence = new ArrayList<>();

        for (String module : modules) {
            String ss = Globals.concatDirectoryNames(appPath, module, "pom.xml");
            sentence.add("mvn.bat -f " + ss + " clean install -DskipTests");
        }

        return sentence;
    }
}

