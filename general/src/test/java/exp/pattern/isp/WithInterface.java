package exp.pattern.isp;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class WithInterface {

    @Test
    public void test() {
        IBase base = new Impl();
        base.run();
     }


    interface IBase {
        void run();
    }

    abstract class AbstractIBase implements IBase {
        @Override
        public void run() {

        }

        public abstract void method();

        public void helper1() {

        }

        public void assistant() {

        }
    }

    class Impl extends AbstractIBase {
        @Override
        public void method() {

        }
    }


}
