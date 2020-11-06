package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class CustomToStringTest {

    private CustomToString cts;
    private final MyBean bean = new MyBean(1, "first", "f-1");

    @BeforeMethod
    public void setUp() {
        cts = new CustomToString();
    }

    @DataProvider
    private Object[][] singleValuesProvider() {
        return new Object[][] {
                {null, "null"},
                {123, "123"},
                {456., "456.0"},
                {"str", "str"},
        };
    }

    @Test(dataProvider = "singleValuesProvider")
    public void shouldMapSingleValues(Object value, String expected) {
        assertEquals(value, expected);
    }

    @Test
    public void shouldMapArray() {
        assertEquals(new Integer[] {1, 2, 3, 4, 5}, "1, 2, 3, 4, 5");
    }

    @Test
    public void shouldMapArray_mappingElements() {
        cts.map(Integer.class, i -> i * 2);
        assertEquals(new Integer[] {1, 2, 3}, "2, 4, 6");
    }

    @Test
    public void shouldUseCustomMapping() {
        cts.map(Integer.class, i -> i * 2);
        assertEquals(123, "246");
    }

    @Test
    public void shouldUseCustomMapping_Bean() {
        cts.map(MyBean.class, MyBean::getId);
        assertEquals(bean, "1");

        cts.map(MyBean.class, MyBean::getName);
        assertEquals(bean, "first");

        cts.map(MyBean.class, MyBean::getDesc);
        assertEquals(bean, "f-1");
    }

    @Test
    public void shouldUseCustomMapping_Bean_andMapProperty() {
        cts.map(MyBean.class, MyBean::getName)
                .map(String.class, s -> "---" + s + "---");
        assertEquals(bean, "---first---");
    }

    @Test
    public void shouldUseCustomMapping_Bean_propertiesArray() {
        cts.map(MyBean.class, MyBean::getId, MyBean::getName, MyBean::getDesc);
        assertEquals(bean, "1, first, f-1");
    }

    @Test
    public void shouldUseCustomMapping_Bean_propertiesArray_recursive() {
        cts.map(MyBean.class, MyBean::getId, MyBean::getName, MyBean::getDesc)
                .map(Integer.class, i -> "id" + i)
                .map(String.class, s -> "--" + s + "--");
        assertEquals(bean, "id1, --first--, --f-1--");
    }

    private void assertEquals(Object source, String expected) {
        String value = cts.apply(source);
        Assert.assertEquals(value, expected);
    }


    private static class MyBean {
        private final Integer id;
        private final String name;
        private final String desc;

        private MyBean(Integer id, String name, String desc) {
            this.id = id;
            this.name = name;
            this.desc = desc;
        }

        public Integer getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String getDesc() {
            return this.desc;
        }
    }
}