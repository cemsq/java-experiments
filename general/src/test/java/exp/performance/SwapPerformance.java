package exp.performance;

import exp.performance.base.PerformanceBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class SwapPerformance extends PerformanceBase{
    private final long MAX_ITERATIONS = 1_000_000L;

    private long a = 5;
    private long A = 5535234526984756278L;

    private long b = 3;
    private long B = 3968475209384752098L;

    public SwapPerformance() {
        super(SwapPerformance.class);
    }

    @Test(dataProvider = "provider")
    public void swapping(long a, long b, Swap s, String method) {
        setMethodName(method);
        long i = 0;
        for (; i<MAX_ITERATIONS; i++) {
            s.swap(a, b);
        }
        log.info("iterations: {}", i);
    }

    @DataProvider
    public Object[][] provider() {
        return new Object[][] {
                {a, b, new CommonSwap(), "common swap: small numbers"},
                {a, b, new XORSwap(),    "xor    swap: small numbers"},
                {A, B, new CommonSwap(), "common swap: big   numbers"},
                {A, B, new XORSwap(),    "xor    swap: big   numbers"}
                };
    }

    public static interface Swap {
        public void swap(long a, long b);
    }

    public static class CommonSwap implements Swap {
        @Override
        public void swap(long a, long b) {
            long tmp;
            tmp = a;
            a = b;
            b = tmp;
        }
    }

    public static class XORSwap implements Swap {
        @Override
        public void swap(long a, long b) {
            a = a ^ b; //XOR
            b = a ^ b; //XOR
            a = a ^ b; //XOR
        }
    }
}
