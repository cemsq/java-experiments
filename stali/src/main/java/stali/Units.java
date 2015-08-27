package stali;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Units {

    public static Map<String, String> units;
    private static int count = 1;

    static {
        units = new HashMap<>();

        units.put("AE", getId());
        units.put("AP", getId());
        units.put("BX", getId());
        units.put("CA", getId());
        units.put("CT", getId());
        units.put("DOS", getId());
        units.put("DTZ", getId());
        units.put("EIM", getId());
        units.put("FL", getId());
        units.put("GR", getId());
        units.put("INJ", getId());
        units.put("KAN", getId());
        units.put("KAR", getId());
        units.put("KG", getId());
        units.put("KIS", getId());
        units.put("KP", getId());
        units.put("KRT", getId());
        units.put("KT", getId());
        units.put("L", getId());
        units.put("METER", getId());
        units.put("MG", getId());
        units.put("ML", getId());
        units.put("OP", getId());
        units.put("PA", getId());
        units.put("PAL", getId());
        units.put("PCE", getId());
        units.put("PCK", getId());
        units.put("PF", getId());
        units.put("PK", getId());
        units.put("ROL", getId());
        units.put("SACK", getId());
        units.put("SET", getId());
        units.put("ST", getId());
        units.put("ST.", getId());
        units.put("STG", getId());
        units.put("STK", getId());
        units.put("STUECK", getId());
        units.put("TUBE", getId());
        units.put("VE", getId());
        units.put("VE1", getId());
        units.put("VE2", getId());


    }

    private static String getId() {
        String id = "unit_x" + count++;
        return id;
    }

    public static String getUnitValue(String word) {
        for (String key : units.keySet()) {
            if (word.contains("_" + key)) {
                return word.replace(key, units.get(key));
            } else if (key.equals(word)){
                return units.get(key);
            }
        }

        return word;
    }
}
