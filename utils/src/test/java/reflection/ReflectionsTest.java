package reflection;

import com.cgm.storm.utils.reflection.Reflections;
import com.cgm.storm.utils.reflection.exception.ConstructorNotFoundException;
import org.testng.Assert;
import org.testng.annotations.Test;
import reflection.example.EmptyConstructor;
import reflection.example.GenericArrayField;
import reflection.example.GetterAndSetter;
import reflection.example.NoEmptyConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 */
public class ReflectionsTest {

    @Test
    public void shouldCreateInstance() {
        EmptyConstructor instance = Reflections.createInstance(EmptyConstructor.class);

        Assert.assertNotNull(instance);
    }

    @Test(expectedExceptions = ConstructorNotFoundException.class)
    public void shouldThrowConstructorNotFound() {
        Reflections.createInstance(NoEmptyConstructor.class);
    }

    @Test
    public void shouldFindGetter() {
        Method getter = Reflections.getter(GetterAndSetter.class, "string");

        Assert.assertNotNull(getter);
    }

    @Test
    public void shouldFindSetter() {
        Method setter = Reflections.setter(GetterAndSetter.class, "integer");

        Assert.assertNotNull(setter);
    }

    @Test
    public void shouldCallGetter() {
        Method stringGetter = Reflections.getter(GetterAndSetter.class, "string");
        Method integerGetter = Reflections.getter(GetterAndSetter.class, "integer");
        Method primitiveGetter = Reflections.getter(GetterAndSetter.class, "primitiveInt");

        GetterAndSetter obj = new GetterAndSetter();
        obj.setString("Nala");
        obj.setInteger(25);
        obj.setPrimitiveInt(5);

        String string = (String)Reflections.invoke(stringGetter, obj);
        Integer integer = (Integer)Reflections.invoke(integerGetter, obj);
        int primitiveInt = (int) Reflections.invoke(primitiveGetter, obj);

        Assert.assertEquals(string, "Nala");
        Assert.assertEquals(integer, Integer.valueOf(25));
        Assert.assertEquals(primitiveInt, 5);
    }

    @Test
    public void shouldCallSetter() {
        Method stringGetter = Reflections.setter(GetterAndSetter.class, "string");
        Method integerGetter = Reflections.setter(GetterAndSetter.class, "integer");
        Method primitiveGetter = Reflections.setter(GetterAndSetter.class, "primitiveInt");

        GetterAndSetter obj = new GetterAndSetter();

        Reflections.invoke(stringGetter, obj, "Nala");
        Reflections.invoke(integerGetter, obj, 25);
        Reflections.invoke(primitiveGetter, obj, 5);

        Assert.assertEquals(obj.getString(), "Nala");
        Assert.assertEquals(obj.getInteger(), Integer.valueOf(25));
        Assert.assertEquals(obj.getPrimitiveInt(), 5);
    }

    @Test
    public void shouldExtractGenericType() {
        Field f = Reflections.getField(GenericArrayField.class, "list");

        Assert.assertTrue(f.getType().equals(List.class));
        Assert.assertEquals(Reflections.getType(f), EmptyConstructor.class);
    }
}
