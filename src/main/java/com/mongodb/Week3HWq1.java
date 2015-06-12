package com.mongodb;

import model.Score;
import model.Student;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

/**
 * Created by Yael on 6/12/15.
 */
public class Week3HWq1 {

    public static void main(String[] args) {

        MongoClient client = new MongoClient();
        Datastore ds = new Morphia().createDatastore(client, "school");
        Query<Student> query = ds.createQuery(Student.class);
        List<Student> students = query.asList();

       // Iterable<Student> fetch = query.fetch();

       // Iterator<Student> iterator = fetch.iterator();

      //  Map<Integer, Score> scoreToDelete = new HashMap<Integer, Score>();

        for(Student student : students) {
            //Student student = iterator.next();
            System.out.println("student before update: " + student.toString());
            List<Score> scores = student.getScores();
            if(scores.size() > 0) {
                Score minScore = null;
                for(Score curScore : scores) {
                    if(curScore.getType().equals("homework") && (minScore == null || curScore.getScore() < minScore.getScore())) {
                        minScore = curScore;
                    }
                }

                if(minScore != null) {

                    scores.remove(minScore);
                    UpdateOperations<Student> ops = ds.createUpdateOperations(Student.class).set("scores", scores);
                    Query<Student> queryCurStudent = ds.createQuery(Student.class).field("_id").equal(student.getId());
                    ds.update(queryCurStudent, ops);

                }
            }

            System.out.println("student after apdate: " + student.toString());
        }

       // ((MorphiaIterator) fetch).close();

    }
}
