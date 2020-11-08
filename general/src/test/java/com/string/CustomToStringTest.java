package com.string;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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
    public void shouldMapCustom() {
        cts.map(Integer.class, i -> i * 2);
        assertEquals(123, "246");
    }

    @Test
    public void shouldMapNull() {
        assertEquals(null, "null");
    }

    @Test
    public void shouldMapNull_custom() {
        cts.mapNullTo("");
        assertEquals(null, "");
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
    public void shouldMapArray_customDelimiter() {
        cts.delimiter("\n");
        assertEquals(new Integer[] {1, 2, 3}, "1\n2\n3");
    }

    @Test
    public void shouldMapArray_customDelimiters() {
        cts.delimiter(" # ", "[", "]");
        assertEquals(new Integer[] {1, 2, 3}, "[1 # 2 # 3]");
    }

    @Test
    public void shouldMapList() {
        assertEquals(Lists.newArrayList(1, 2, 3, 4, 5), "1, 2, 3, 4, 5");
    }

    @Test
    public void shouldMapList_sorting() {
        cts.sorted();
        assertEquals(Lists.newArrayList(5, 1, 4, 2, 3), "1, 2, 3, 4, 5");
    }

    @Test
    public void shouldMapList_mappingElements() {
        cts.map(Integer.class, i -> i * 2);
        assertEquals(Lists.newArrayList(1, 2, 3), "2, 4, 6");
    }

    @Test
    public void shouldMapList_customDelimiter() {
        cts.delimiter("\n");
        assertEquals(Lists.newArrayList(1, 2, 3), "1\n2\n3");
    }

    @Test
    public void shouldMapList_customDelimiters() {
        cts.delimiter(" # ", "[", "]");
        assertEquals(Lists.newArrayList(1, 2, 3), "[1 # 2 # 3]");
    }

    @Test
    public void shouldMapSet() {
        assertEquals(Sets.newHashSet("a", "b", "c", "d", "e"), "a, b, c, d, e");
    }

    @Test
    public void shouldMapSet_mappingElements() {
        cts.map(String.class, s -> "." + s);
        assertEquals(Sets.newHashSet("a", "b", "c", "d", "e"), ".a, .b, .c, .d, .e");
    }

    @Test
    public void shouldMapSet_customDelimiter() {
        cts.delimiter("\n");
        assertEquals(Sets.newHashSet("a", "b", "c", "d", "e"), "a\nb\nc\nd\ne");
    }

    @Test
    public void shouldMapSet_customDelimiters() {
        cts.delimiter(" # ", "[", "]");
        assertEquals(Sets.newHashSet("a", "b", "c", "d", "e"), "[a # b # c # d # e]");
    }

    @Test
    public void shouldMapBean() {
        cts.map(MyBean.class, MyBean::getId);
        assertEquals(bean, "1");

        cts.map(MyBean.class, MyBean::getName);
        assertEquals(bean, "first");

        cts.map(MyBean.class, MyBean::getDesc);
        assertEquals(bean, "f-1");
    }

    @Test
    public void shouldMapBean_nullProperty() {
        cts.map(MyBean.class, MyBean::getName, MyBean::getId)
                .delimiter(" ")
                .mapNullTo("was null");

        MyBean myBean = new MyBean(null, "my id", null);
        assertEquals(myBean, "my id was null");
    }

    @Test
    public void shouldMapBean_andProperty() {
        cts.map(MyBean.class, MyBean::getName)
                .map(String.class, s -> "---" + s + "---");
        assertEquals(bean, "---first---");
    }

    @Test
    public void shouldMapBean_propertiesArray() {
        cts.map(MyBean.class, MyBean::getId, MyBean::getName, MyBean::getDesc);
        assertEquals(bean, "1, first, f-1");
    }

    @Test
    public void shouldMapBean_propertiesArray_recursive() {
        cts.map(MyBean.class, MyBean::getId, MyBean::getName, MyBean::getDesc)
                .map(Integer.class, i -> "id" + i)
                .map(String.class, s -> "--" + s + "--");
        assertEquals(bean, "id1, --first--, --f-1--");
    }

    @Test
    public void shouldMapBean_differentlyAsArray() {
        cts.map(MyBean.class, MyBean::getId, MyBean::getName, MyBean::getDesc)
                .delimiter(" ", "[", "]")
                .beanDelimiter(" # ", "(", ")")
                .mapNullTo("was null");

        assertEquals(bean, "(1 # first # f-1)");
        assertEquals(bean, "(1 # first # f-1)");
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