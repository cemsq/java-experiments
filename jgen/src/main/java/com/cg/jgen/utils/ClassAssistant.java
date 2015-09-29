package com.cg.jgen.utils;

import com.cg.helix.mib.annotation.Input;
import com.cg.helix.persistence.metadata.annotation.BusinessObject;
import com.cg.helix.schemadictionary.annotation.ComplexType;
import com.cg.helix.util.annotation.Flag;
import com.cg.jgen.core.Config;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.exception.JGenException;
import com.cgm.storm.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * Assistant class for objects of type java.lang.Class
 */
public class ClassAssistant {
    protected static final Logger log = LoggerFactory.getLogger(ClassAssistant.class);

    private ClassAssistant() {

    }

    public static boolean optimisticLocking(Class clazz) {
        BusinessObject bo = (BusinessObject) clazz.getAnnotation(BusinessObject.class);
        if (bo != null) {
            if (bo.optimisticLocking() == Flag.TRUE) {
                return true;
            }
        }

        ComplexType ct = (ComplexType) clazz.getAnnotation(ComplexType.class);

        return ct != null && ct.optimisticLocking();
    }

    public static String getClassName(Class clazz) {
        String name = clazz.getName();

        return StringUtils.getStringAfterLastChar(name, '.');
    }

    public static List<Method> getMethods(Class clazz) {
        Method m1[] = clazz.getMethods();
        Method m2[] = new Method[]{};

        clazz = clazz.getSuperclass();
        if (clazz != null) {
            m2 = clazz.getDeclaredMethods();
        }

        return Globals.getArraysAsArrayList(m1, m2);
    }

    /*public static List<Field> getMembers(Class clazz) {
        Field m1[] = clazz.getDeclaredFields();

        clazz = clazz.getSuperclass();
        if (clazz == null) {
            return Globals.getArraysAsArrayList(m1);
        }
        Field m2[] = clazz.getDeclaredFields();

        return Globals.getArraysAsArrayList(m1, m2);
    }*/

    public static String getParameters(Method method) {
        StringBuilder sb = new StringBuilder("");
        Class types[] = method.getParameterTypes();
        Annotation annotations[][] = method.getParameterAnnotations();

        String message = "@Input annotation not found in method: " + method.getName() + ". Parameter # ";

        int i = 0;
        for (Class cc : types) {
            if (i > 0) {
                sb.append(", ");
            }

            // parameter type
            String tsType = TsTypeDecoder.toTypeScript(getClassName(cc));

            // search for a parameter name
            String parameter = "var_" + i;
            if (i < annotations.length) {
                if (annotations[i].length > 0) {
                    if (annotations[i][0] instanceof Input) {
                        Input input = (Input) annotations[i][0];
                        parameter = input.name();
                    } else {
                        log.warn(message + i);
                    }
                } else {
                    log.warn(message + i);
                }
            }

            parameter += ": " + tsType;

            sb.append(parameter);
            i++;
        }

        return sb.toString();
    }

    public static String getReturnType(Method method) {
        String ini = "";
        String end = "";
        Class cc = method.getReturnType();

        Type t = method.getGenericReturnType();
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;
            Type[] actualGenericParameters = pt.getActualTypeArguments();

            cc = (Class) actualGenericParameters[0];
            ini = "List<";
            end = ">";
        }

        return ini + ClassAssistant.getClassName(cc) + end;
    }

    /*public static String getAccess(int mod) {
        if (Modifier.isPrivate(mod)) {
            return "private";
        }

        if (Modifier.isPublic(mod)) {
            return "public";
        }

        if (Modifier.isProtected(mod)) {
            return "protected";
        }

        return "";
    }*/

    public static Class loadClassFromJar(Config config, FileTemplate ft, String ettName, boolean throwException) {
        String jar = config.getFullJarPath();
        String objectName = ft.generateLongObjectName(config.getRootPackage(), ettName);

        return loadClassFromJar(jar, objectName, throwException);
    }

    public static Class loadClassFromJar(String jarName, String className, boolean throwException) {
        Class clazz;
        try {
            File jarFile = new File(jarName);
            if (!jarFile.exists()) {
                throw new JGenException("Jar file not found: '" + jarName);
            }

            URL[] urls = new URL[]{jarFile.toURI().toURL()};

            URLClassLoader ucl = URLClassLoader.newInstance(urls);
            clazz = ucl.loadClass(className);
        } catch (MalformedURLException | ClassNotFoundException e) {
            if (throwException) {
                throw new JGenException("Unable to load class: " + className + ". jar-file: " + jarName + "\n", e);
            }
            clazz = null;
        }

        return clazz;
    }
}
