package exp.libs.jackson;

import com.google.common.base.Strings;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JsonHelperTest {

    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();

        build(map, "item.code", "my_analyzer");
        build(map, "item.name", "my_analyzer");
        build(map, "item.ingredient.number", "my_analyzer");

        System.out.println(map);
    }

    @SuppressWarnings("unchecked")
    private void build(Map<String, Object> map, String fieldName, String analyzer) {
        String[] tokens = parseFieldName(fieldName);

        String name = tokens[0];
        String nested = tokens[1];

        Map<String, Object> properties = (Map<String, Object>)map.get(name);

        if (properties == null) {
            properties = new HashMap<>();
            map.put(name, properties);
        }

        if (Strings.isNullOrEmpty(nested)) {
            properties.put("analyzer", analyzer);

        } else {
            Map<String, Object> fields = (Map<String, Object>)properties.get("properties");
            if (fields == null) {
                fields = new HashMap<>();
                properties.put("properties", fields);
            }

            build(fields, nested, analyzer);
        }
    }

    private static String[] parseFieldName(String fieldName) {
        checkFieldName(fieldName);

        String field = fieldName;
        int point = fieldName.indexOf(".");

        if (point > 0) {
            field = fieldName.substring(0, point);
            fieldName = fieldName.substring(point + 1);
        } else {
            fieldName = "";
        }

        return new String[]{
                field,
                fieldName
        };
    }

    private static void checkFieldName(String fieldName) {
        if (Strings.isNullOrEmpty(fieldName)) {
            throw new RuntimeException("Empty fieldName");
        }
        if (fieldName.endsWith(".")) {
            throw new RuntimeException("fieldName should not end with dot");
        }
    }
}
