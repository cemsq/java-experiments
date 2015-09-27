package util;

/**
 *
 */
public class StringsUtil {

    private StringsUtil() {
    }

    public static String capitalize(String str) {

        if (!com.google.common.base.Strings.isNullOrEmpty(str)) {
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
        }

        return str;
    }
}
