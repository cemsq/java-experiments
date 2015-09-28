package fieldhandler.accessor;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reflection.exception.FieldNotFoundException;

/**
 *
 */
public class WhenCreateFieldAccessorTest {

    @Test
    public void defaultTypeShouldBeGetterSetter() {
        Assert.assertEquals(FieldAccessor.DEFAULT_TYPE, AccessorType.GETTER_SETTER);
        FieldAccessor accessor = FieldAccessor.factory(TestClass.class, "withGetterSetter");
        Assert.assertEquals(accessor.getClass(), FieldAccessor.GetterSetter.class);
    }

    @Test(expectedExceptions = FieldNotFoundException.class)
    public void shouldThrowsFieldNotFoundException() {
        FieldAccessor.factory(TestClass.class, "xyz");
    }

    @Test(dataProvider = "properAccessorWithTypeProvider")
    public <T>void shouldCreateProperAccessorFromType(
            Class<T> clazz,
            String field,
            AccessorType type,
            Class<? extends FieldAccessor<T>> accessorExpected,
            boolean shouldFail) {

        try {
            FieldAccessor<T> accessor = FieldAccessor.factory(clazz, field, type);

            Assert.assertNotNull(accessor, "Accessor not created. Type: " + type);
            Assert.assertEquals(accessor.getClass(), accessorExpected, "Wrong Accessor Type created");

            Assert.assertFalse(shouldFail, "it should fail, but it didn't");
        } catch (Exception e) {
            Assert.assertTrue(shouldFail, "it shouldn't fail. " + e.getMessage());
        }
    }

    @Test(dataProvider = "withoutGetterSetterProvider")
    public<T> void shouldFail_GetterSetterNotDefined(Class<T> clazz, String field) {
        FieldAccessor.factory(clazz, field, AccessorType.GETTER_SETTER);
    }

    // **********************************************************************
    // Providers
    // **********************************************************************

    @DataProvider
    public Object[][] properAccessorWithTypeProvider() {
        Class DIRECT = FieldAccessor.DirectField.class;
        Class GETTER_SETTER = FieldAccessor.GetterSetter.class;

        AccessorType DIRECT_TYPE = AccessorType.DIRECT_FIELD;
        AccessorType GETTER_SETTER_TYPE = AccessorType.GETTER_SETTER;

        return new Object[][] {
                // success test
                {TestClass.class, "onlyGetter", DIRECT_TYPE, DIRECT, false},
                {TestClass.class, "onlySetter", DIRECT_TYPE, DIRECT, false},
                {TestClass.class, "noGetterNoSetter", DIRECT_TYPE, DIRECT, false},
                {TestClass.class, "withGetterSetter", DIRECT_TYPE, DIRECT, false},
                {TestClass.class, "withGetterSetter", GETTER_SETTER_TYPE, GETTER_SETTER, false},

                // failing tests: no getter or setter defined
                {TestClass.class, "onlyGetter", GETTER_SETTER_TYPE, DIRECT, true},
                {TestClass.class, "onlySetter", GETTER_SETTER_TYPE, DIRECT, true},
                {TestClass.class, "noGetterNoSetter", GETTER_SETTER_TYPE, DIRECT, true},
        };
    }

    @DataProvider
    public Object[][] withoutGetterSetterProvider() {
        return new Object[][] {
                {TestClass.class, "onlyGetter"},
                {TestClass.class, "onlySetter"},
                {TestClass.class, "noGetterNoSetter"},
        };
    }
}
