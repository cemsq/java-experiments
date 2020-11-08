

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstNonRepeatingCharacter {

    private static final String DEFAULT_VALUE = "_";

    public static String get(String input) {

        if (input == null || input.isEmpty()) {
            return DEFAULT_VALUE;
        }

        Set<Character> discarded = new HashSet<>();
        Map<Character, AtomicInteger> cache = new LinkedHashMap<>();
        for (Character c : input.toCharArray()) {

            if (discarded.contains(c)) {
                continue;
            }

            int count = cache.computeIfAbsent(c, k -> new AtomicInteger()).incrementAndGet();

            if (count > 1) {
                cache.remove(c);
                discarded.add(c);
            }
        }

        if (!cache.isEmpty()) {
            Map.Entry<Character, AtomicInteger> e = cache.entrySet().iterator().next();
            return e.getKey().toString();
        }

        return DEFAULT_VALUE;
    }
}
