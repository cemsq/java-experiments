package com.cg.jgen.utils;

import com.cg.jgen.core.Config;
import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;
import com.cgm.storm.utils.common.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Object to be Injected by Velocity, and their methods can be called from the vm-templates.
 */
public class Utility {
    private static final Calendar calendar = Calendar.getInstance();
    private static Config currentConfig;

    public static void setConfig(Config config) {
        currentConfig = config;
    }

    public static String toLowerCase(String str) {
        if (!StringUtils.isValid(str)) {
            return "";
        }

        return str.toLowerCase();
    }

    public static String toUpperCase(String str) {
        if (!StringUtils.isValid(str)) {
            return "";
        }

        return str.toUpperCase();
    }

    public static String pascalCase(String str) {
        String str2 = "";
        if (StringUtils.isValid(str)) {
            str2 = str.substring(0, 1).toUpperCase();
            str2 += str.substring(1, str.length());
            return str2;
        }

        return str2;
    }

    public static String camelCase(String str) {
        String str2 = "";
        if (StringUtils.isValid(str)) {
            str2 = str.substring(0, 1).toLowerCase();
            str2 += str.substring(1, str.length());
            return str2;
        }

        return str2;
    }

    public static String getPackage(String fileTemplateName, String entityName) {
        FileTemplate ft = FileTemplate.getFileTemplate(fileTemplateName, false);
        return getPackage(ft, entityName);
    }

    public static String getPackage(FileTemplate ft, String entityName) {

        if (currentConfig == null || ft == null || Entity.getEntity(entityName) == null) {
            return "";
        }

        String rootPackage = currentConfig.getRootPackage();
        String relativePackage = ft.getRelativePackage();

        String expression = relativePackage.toLowerCase();
        String replacement = Utility.camelCase(entityName);

        String relPackage = Globals.replaceEntityExpression(expression, replacement);

        return Globals.concatObjectNames(rootPackage, relPackage);
    }

    public static String objectName(String fileTemplateName, String entityName) {
        FileTemplate ft = FileTemplate.getFileTemplate(fileTemplateName, false);

        if (currentConfig == null || ft == null || Entity.getEntity(entityName) == null) {
            return "";
        }

        return ft.generateLongObjectName(currentConfig.getRootPackage(), entityName);
    }

    public static String date() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        format.format(calendar.getTime());
        return format.format(calendar.getTime());
    }

}
