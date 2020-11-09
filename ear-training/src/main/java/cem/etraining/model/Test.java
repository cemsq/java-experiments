package cem.etraining.model;

import cem.etraining.Utils;
import org.joda.time.LocalDateTime;

public class Test {

    private final Exercise exercise;
    private final String score;
    private final float _score;
    private final LocalDateTime time;

    public Test(Exercise exercise, String score) {
        this(exercise, score, LocalDateTime.now());
    }

    public Test(Exercise exercise, String score, LocalDateTime time) {
        this.exercise = exercise;
        this.score = score;
        this._score = Utils.toFloat(score);
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public float getNumericScore() {
        return _score;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
