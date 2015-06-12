package model;

/**
 * Created by Yael on 6/12/15.
 */
public class Score {
    public String type;
    public double score;

    public Score() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "type='" + type + '\'' +
                ", score=" + score +
                '}';
    }
}
