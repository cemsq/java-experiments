package com.cg.jgen.core.generator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

/**
 *
 */
public class VelocityAdapter {
    private VelocityContext context;
    private VelocityEngine ve = new VelocityEngine();

    public VelocityAdapter() {
        reset();
    }

    public void reset() {
        context = new VelocityContext();

        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    }

    public void put(String key, Object value) {
        context.put(key, value);
    }

    public String render(String templateName) {
        String str;

        StringWriter writer = new StringWriter();
        Template template = ve.getTemplate(templateName);
        template.merge(context, writer);

        str = writer.toString();

        return str;
    }
}
