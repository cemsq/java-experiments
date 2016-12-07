package com.javaTest;

import org.testng.annotations.Test;

import java.util.Map;

/**
 *
 */
public class EnvVariables {

    @Test
    public void testWithEnvVariables() {
        Map<String, String> env = System.getenv();
        String m2 = System.getProperty("M2");
        System.out.println(m2);
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
    }

    @Test
    public void testWithProperties() {
        String property = System.getProperty("catalina.base");
        System.out.println(property);
    }

    @Test
    public void both() {
        System.out.println("cat = " + System.getenv("catalina.home"));
        System.out.println("CAT = " + System.getenv("CATALINA.HOME"));
        System.out.println("cat_ = " + System.getenv("catalina_home"));
        System.out.println("CAT_ = " + System.getenv("CATALINA_HOME"));
        System.out.println("base = " + System.getenv("catalina.base"));
        System.out.println("BASE = " + System.getenv("CATALINA.BASE"));
        System.out.println("base_ = " + System.getenv("catalina_base"));
        System.out.println("BASE_ = " + System.getenv("CATALINA_BASE"));
        System.out.println("with properties");
        System.out.println("cat = " + System.getProperty("catalina.home"));
        System.out.println("CAT = " + System.getProperty("CATALINA.HOME"));
        System.out.println("cat_ = " + System.getProperty("catalina_home"));
        System.out.println("CAT_ = " + System.getProperty("CATALINA_HOME"));
        System.out.println("base = " + System.getProperty("catalina.base"));
        System.out.println("BASE = " + System.getProperty("CATALINA.BASE"));
        System.out.println("base_ = " + System.getProperty("catalina_base"));
        System.out.println("BASE_ = " + System.getProperty("CATALINA_BASE"));
    }
}
