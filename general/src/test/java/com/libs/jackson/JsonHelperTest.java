package com.libs.jackson;

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

        System.out.println(JsonHelper.readableJson(map));
    }


    private void build(Map<String, Object> map, String fieldName, String analyzer) {
        String[] tokens = parseFieldName(fieldName);

        String name = tokens[0];
        String nested = tokens[1];

        // get or create an object with name "name"
        Map<String, Object> properties = get(map, name);

        // looking for a terminal object
        if (Strings.isNullOrEmpty(nested)) {
            properties.put("type", "string");
            properties.put("analyzer", analyzer);

        } else {
            // get or create a "properties" object
            Map<String, Object> fields = get(properties, "properties");

            // looking for a nested object
            build(fields, nested, analyzer);
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> get(Map<String, Object> map, String name) {
        Map<String, Object> obj = (Map<String, Object>)map.get(name);
        if (obj == null) {
            obj = new HashMap<>();
            map.put(name, obj);
        }

        return obj;
    }

    /**
     * fieldName = "some.field.name"
     *  returns:
     *      [0] = "some"
     *      [1] = "field.name"
     */
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
        if (Strings.isNullOrEmpty(fieldName)) {
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
