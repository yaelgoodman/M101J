package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yael on 5/30/15.
 */
public class HelloWorldFreemarkerStyle {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        try {
            configuration.setDirectoryForTemplateLoading(new File("/Users/Yael/M101J/src/test/resources/"));
            Template helloTemplate = configuration.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "Yael");
            helloTemplate.process(helloMap, writer);
            System.out.println(writer);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
