package fieldhandler.accessor;

import com.cgm.storm.utils.fieldhandler.AccessorType;
import com.cgm.storm.utils.fieldhandler.accessor.FieldAccessor;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class UsingFieldAccessorTest {

    @Test(dataProvider = "getterProvider")
    public<T> void shouldGetValue(Class<T> clazz, String field, AccessorType type, T obj, Object expectedValue) {
        FieldAccessor<T> accessor = FieldAccessor.factory(clazz, field, type);

        Assert.assertEquals(accessor.get(obj), expectedValue);
    }

    @Test(dataProvider = "setterProvider")
    public<T> void shouldSetValue(Class<T> clazz, String field, AccessorType type, T obj, Object value) {
        FieldAccessor<T> accessor = FieldAccessor.factory(clazz, field, type);

        accessor.set(obj, value);

        Assert.assertEquals(accessor.get(obj), value);
    }

    @DataProvider
    public Object[][] getterProvider() {

        TestClass test = new TestClass("only", "abc", 1.5, 4);

        return testProvider(test);
    }

    @DataProvider
    public Object[][] setterProvider() {

        TestClass test = new TestClass("-", "-", 0, 0);

        return testProvider(test);
    }

    public Object[][] testProvider(TestClass test) {

        return new Object[][] {
                {TestClass.class, "onlyGetter", AccessorType.DIRECT_FIELD, test, "only"},
                {TestClass.class, "onlySetter", AccessorType.DIRECT_FIELD, test, "abc"},
                {TestClass.class, "noGetterNoSetter", AccessorType.DIRECT_FIELD, test, 1.5},
                {TestClass.class, "withGetterSetter", AccessorType.DIRECT_FIELD, test, 4},

                {TestClass.class, "withGetterSetter", AccessorType.GETTER_SETTER, test, 4},
        };
    }
}
