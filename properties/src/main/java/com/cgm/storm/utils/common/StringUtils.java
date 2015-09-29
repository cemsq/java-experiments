package com.cgm.storm.utils.common;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.List;

/**
 *  Storm String utils
 */
public class StringUtils {

    private StringUtils() {}

    public static String capitalize(String str) {

        if (!Strings.isNullOrEmpty(str)) {
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
        }

        return str;
    }

    public static String replaceLineSeparator(String str) {
        return str.replace("\n", System.lineSeparator());
    }

    public static String stringFormat(int n, String str, Object... args) {
        int ini = 0;
        int end = 0;

        StringBuilder sb = new StringBuilder("");
        for (Object arg : args) {
            end = str.indexOf("{}", ini);
            if (end < 0) {
                break;
            }

            sb.append(str.substring(ini, end));
            sb.append(arg.toString());

            ini = end + 2;
        }

        if (end < str.length()) {
            sb.append(str.substring(ini, str.length()));
        }

        int m = n - sb.length();
        for (int i = 0; i < m; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }

    public static void validateString(String str, String errorMessage) {
        Preconditions.checkArgument(isValid(str), errorMessage);
    }

    public static String getValidString(String str, String defaultStr) {
        if (isValid(str)) {
            return str;
        }
        return defaultStr;
    }

    public static boolean isValid(String string) {
        return !Strings.isNullOrEmpty(string);
    }

    public static String getStringAfterLastChar(String str, char c) {
        int pos = str.lastIndexOf(c);

        if (pos > 0) {
            return str.substring(pos + 1, str.length());
        }

        return str;
    }

    public static String concatStringsWith(String s, String... strings) {
        return concatAsString(strings, s, "", "");
    }

    public static String concatAsString(Object[] array, String separator, String before, String after) {
        return concatAsString(Arrays.asList(array), separator, before, after);
    }

    public static <T> String concatAsString(List<T> list, String separator, String before, String after) {
        Preconditions.checkNotNull(list, "list must be not null");

        int i = 0;
        StringBuilder sb = new StringBuilder("");
        for (T element : list) {
            if (element == null || !isValid(element.toString())) {
                continue;
            }
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(before);
            sb.append(element);
            sb.append(after);

            i++;
        }

        return sb.toString();
    }
}
