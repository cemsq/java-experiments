package exp.performance.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 */
public abstract class PerformanceBase {

    protected final Logger log;
    private double init;
    private String methodName;

    public PerformanceBase(Class<?extends PerformanceBase> clazz) {
        log =  LoggerFactory.getLogger(clazz);
        methodName = "";
        log.info("---------------------------------------------------------");
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @BeforeMethod
    public void before() {
        init = System.currentTimeMillis();
    }

    @AfterMethod
    public void after() {
        double end = System.currentTimeMillis();
        log.info("{}: {}", methodName, end - init);
    }
}
