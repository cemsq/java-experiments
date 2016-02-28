package com.generics;

import org.testng.annotations.Test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by cesar on 12/17/2015.
 */
public class ResultTest {

    MockInterface service = new MockInterface();

    @Test
    public void test1() {
        Complex x = service.unique(Complex.class);
        List<Complex> list = service.list(Complex.class);

//        method(MockInterface::unique, Complex.class);

//        method(service, MockInterface::unique, Complex.class);
//        method(service, MockInterface::list, Complex.class);

        x = Results.unique(service, Complex.class);
        list = Results.list(service, Complex.class);

    }

    static class Results {
        public static<T> T unique(MockInterface service, Class<T> complex) {
            return method(service, MockInterface::unique, complex);
        }

        public static<T> List<T> list(MockInterface service, Class<T> complex) {
            return method(service, MockInterface::list, complex);
        }

        public static <T, R> R method(MockInterface service, BiFunction<MockInterface, Class<T>, R> action, Class<T> complex) {

            return action.apply(service, complex);
        }
    }



    static class Complex {
        public Complex() {
        }
    }

    public<T, R> R method(MockInterface service, BiFunction<MockInterface, Class<T>, R> action, Class<T> complex) {

        return action.apply(service, complex);
    }

    public<T, R> R method(Function<Class<T>, R> action, Class<T> complex) {

        return action.apply(complex);
    }

}

