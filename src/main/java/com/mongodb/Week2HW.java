package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.ascending;

/**
 * Created by Yael on 6/5/15.
 */
public class Week2HW {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> coll = db.getCollection("grades");

        Document first = coll.find().first();
        System.out.println(first);

//        List<Document> gradesList = coll.find().into(new ArrayList<Document>());
//        for(Document cur : gradesList) {
//            System.out.println(cur);
//        }

        //Bson projection = fields(include("_id","student_id", "score", "type"));
        Bson filterHW = new Document("type", "homework").append("student_id", new Document("$gt", 0));
        Bson sort = new Document("student_id", 1).append("score", 1);
        Bson nozero = new Document("student_id", new Document("$gt", 0));
        Bson sort2 = ascending("student_id", "score");
        MongoCursor<Document> cursor = coll.find(filterHW).sort(sort).iterator();
        double prevStudentId =  -1;
        List<ObjectId> toRemove = new ArrayList<ObjectId>();
        try{
            while(cursor.hasNext()) {
                Document cur = cursor.next();
                System.out.println(cur);

                Double curStudentId = cur.getDouble("student_id");
                if(curStudentId != null){
                    if (curStudentId != prevStudentId && prevStudentId !=-1){
                        toRemove.add(cur.getObjectId("_id"));
                    }
                    prevStudentId = curStudentId;
                }else {
                    System.out.println("THERE IS A PROBLEM.");
                }
            }
        }finally{
            cursor.close();
        }

        for (ObjectId i : toRemove){
            coll.deleteOne(new Document("_id", i));
            System.out.println("DELETE: " + i);
        }
    }
}
