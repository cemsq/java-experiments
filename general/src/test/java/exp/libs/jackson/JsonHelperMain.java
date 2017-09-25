package exp.libs.jackson;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JsonHelperMain {

    public static void main(String[] arg) {
        new JsonHelperMain().test();
    }

    public void test() {
        Map<String, Object> map = new HashMap<>();

        build(map, "item.core", "my_analyzer");
        build(map, "item.name", "my_analyzer");
        build(map, "item.ingredient.number", "my_analyzer");

        System.out.println(map);
    }

    @SuppressWarnings("unchecked")
    private void build(Map<String, Object> map, String fieldName, String analyzer) {
        String[] tokens = parseFieldName(fieldName);

        String name = tokens[0];
        String nested = tokens[1];

        // get or create an object with name "name"
        Map<String, Object> properties = (Map<String, Object>)map.get(name);
        if (properties == null) {
            properties = new HashMap<>();
            map.put(name, properties);
        }

        // looking for a terminal object
        if (nested == null || nested.equals("")) {
            properties.put("analyzer", analyzer);

        } else {
            // get or create a "properties" object
            Map<String, Object> fields = (Map<String, Object>)properties.get("properties");
            if (fields == null) {
                fields = new HashMap<>();
                properties.put("properties", fields);
            }

            // looking for a nested object
            build(fields, nested, analyzer);
        }
    }

    private String[] parseFieldName(String fieldName) {
        checkFieldName(fieldName);

        String field = fieldName;
        String nested = "";

        int point = fieldName.indexOf(".");
        if (point > 0) {
            field = fieldName.substring(0, point);
            nested = fieldName.substring(point + 1);
        }

        return new String[]{
                field,
                nested
        };
    }

    private void checkFieldName(String fieldName) {
        if (fieldName == null || fieldName.equals("")) {
            throw new RuntimeException("Empty fieldName");
        }
        if (fieldName.startsWith(".")) {
            throw new RuntimeException("fieldName should not start with dot");
        }
        if (fieldName.endsWith(".")) {
            throw new RuntimeException("fieldName should not end with dot");
        }
    }
}
