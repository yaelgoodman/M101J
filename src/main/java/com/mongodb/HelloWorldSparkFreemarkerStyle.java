package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yael on 5/30/15.
 */
public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) {
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                Configuration configuration = new Configuration();
                StringWriter writer = new StringWriter();
                try {
                    configuration.setDirectoryForTemplateLoading(new File("/Users/Yael/M101J/src/test/resources/"));
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "Yael");
                    helloTemplate.process(helloMap, writer);
                    System.out.println(writer);
                } catch(Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return  writer;
            }
        });
    }
}
