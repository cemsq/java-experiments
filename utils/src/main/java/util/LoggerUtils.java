package util;

public class LoggerUtils {

    public static String params(String p1, Object v1) {
        return params(p1, v1);
    }

    public static String params(String p1, Object v1, String p2, Object v2) {
        return params(p1, v1, p2, v2);
    }

    public static String params(String p1, Object v1, String p2, Object v2, String p3, Object v3) {
        return params(p1, v1, p2, v2, p3, v3);
    }

    private static String params(Object... keyValuePairs) {
        if (keyValuePairs.length % 2 != 0) {
            throw new IllegalArgumentException("---");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < keyValuePairs.length; i += 2) {
            if (i > 0) {
                sb.append(", ");
            }
            String name = String.valueOf(keyValuePairs[i]);
            Object value = keyValuePairs[i + 1];
            sb.append(String.format("%s = %s", name, value));
        }

        return sb.toString();
    }

}
