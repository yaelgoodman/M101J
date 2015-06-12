package com.mongodb;

import org.bson.Document;

/**
 * Created by Yael on 6/5/15.
 */
public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document()
                .append("str", "MongoDB, Hello")
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0));

        String str = document.getString("str");

    }
}
