package com.cgm.storm.utils.properties;

import com.google.common.base.Strings;

/**
 * Created by César Mora on 14.10.2014.
 */
public class Globals {
    private Globals(){}

    public static String getName(Class clazz){
        String name = clazz.getName();
        return getStringAfterLastPoint(name);
    }

    public static String getStringAfterLastPoint(String str){
        int pos = str.lastIndexOf('.') + 1;

        return str.substring(pos, str.length());
    }

    public static boolean isValidString(String str) {
        return !Strings.isNullOrEmpty(str);
    }

}
