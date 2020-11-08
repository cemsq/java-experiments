import com.google.common.collect.Sets;

import java.util.Set;

public class FirstDuplicate {

    public static int fistDuplicate(int[] a) {

        if (a != null && a.length > 0) {

            Set<Integer> seen = Sets.newHashSetWithExpectedSize(a.length);
            for (int candidate : a) {
                if (!seen.add(candidate)) {
                    return candidate;
                }
            }
        }

        return -1;
    }
}
