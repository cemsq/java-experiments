package com.reflection;

import exp.amodel.Person;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MethodReturnType {

    @Test
    public void test() {
        try{
            Method m = MethodReturnType.class.getDeclaredMethod("method0", new Class[]{});
            instanceOf(m, List.class, Person.class);

            m = MethodReturnType.class.getDeclaredMethod("method1", new Class[]{});
            instanceOf(m, List.class, String.class);

            m = MethodReturnType.class.getDeclaredMethod("method2", new Class[]{});
            instanceOf(m, List.class, String.class);

            m = MethodReturnType.class.getDeclaredMethod("method3", new Class[]{});
            instanceOf(m, List.class, String.class);

            m = MethodReturnType.class.getDeclaredMethod("method4", new Class[]{});
            instanceOf(m, StringList.class);
        }catch(Exception e){
            System.err.println(e.toString());
        }
    }

    public static boolean instanceOf (
            Method m,
            Class<?> returnedBaseClass,
            Class<?> ... genericParameters) {

        System.out.println("Testing method: " + m.getDeclaringClass().getName()+"."+ m.getName());
        boolean instanceOf = false;
        instanceOf = returnedBaseClass.isAssignableFrom(m.getReturnType());
        System.out.println("\tReturn type test succesfull: " + instanceOf + " (expected '"+returnedBaseClass.getName()+"' found '"+m.getReturnType().getName()+"')");
        System.out.print("\tNumber of generic parameters matches: ");
        Type t = m.getGenericReturnType();
        if(t instanceof ParameterizedType){
            ParameterizedType pt = (ParameterizedType)t;
            Type[] actualGenericParameters = pt.getActualTypeArguments();
            instanceOf = instanceOf
                    && actualGenericParameters.length == genericParameters.length;
            System.out.println("" + instanceOf + " (expected "+ genericParameters.length +", found " + actualGenericParameters.length+")");
            for (int i = 0; instanceOf && i < genericParameters.length; i++) {
                if (actualGenericParameters[i] instanceof Class) {
                    instanceOf = instanceOf
                            && genericParameters[i].isAssignableFrom(
                            (Class) actualGenericParameters[i]);
                    System.out.println("\tGeneric parameter no. " + (i+1) + " matches: " + instanceOf + " (expected '"+genericParameters[i].getName()+"' found '"+((Class) actualGenericParameters[i]).getName()+"')");
                } else {
                    instanceOf = false;
                    System.out.println("\tFailure generic parameter is not a class");
                }
            }
        } else {
            System.out.println("" + true + " 0 parameters");
        }
        return instanceOf;
    }
    public List<Person> method0() {
        return null;
    }

    public List<String> method1() {
        return null;
    }
    public ArrayList<String> method2() {
        return new ArrayList<String>();
    }
    public StringList method3() {
        return null;
    }
    public <T extends StringList> T method4() {
        return null;
    }
}
