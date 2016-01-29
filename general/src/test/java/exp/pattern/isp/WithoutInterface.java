package exp.pattern.isp;

import org.testng.annotations.Test;

/**
 *
 */
public class WithoutInterface {

    @Test
    public void test() {
        Base b = new Impl();

        b.run();
    }

    abstract class Base {

        public void run() {

        }

        public abstract void method();

        public void helper1() {

        }

        public void assistant() {

        }
    }

    class Impl extends Base {
        @Override
        public void method() {

        }
    }
}
