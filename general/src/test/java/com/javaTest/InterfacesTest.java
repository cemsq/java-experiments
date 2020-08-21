package com.javaTest;

import java.util.function.Supplier;

/**
 *
 */
public class InterfacesTest {


    private interface ProviderIdAware {
        String getProviderId();

        static ProviderIdAware of(String providerId) {
            return () -> providerId;
        }

        static ProviderIdAware of(Supplier<String> supplier) {
            return supplier::get;
        }
    }

    private interface AnotherInterface {
        String getId();
        String getProviderId();
    }

    private class MyClass implements ProviderIdAware, AnotherInterface {

        @Override
        public String getId() {
            return "id";
        }

        @Override
        public String getProviderId() {
            return "providerId";
        }
    }

    public void geProviderId(ProviderIdAware providerIdAware) {

    }
}
