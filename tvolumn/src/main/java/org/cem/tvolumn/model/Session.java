package org.cem.tvolumn.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class Session {

    private final List<Work> exercises = Lists.newArrayList();

    public Session add(Exercise e, int sets) {
        exercises.add(new Work(e, sets));
        return this;
    }

    public void computeVolume(Volume vol) {
        for (Work w : exercises) {
            w.exercise.computeVolume(w.sets, vol);
        }
    }

    private static class Work {
        private final Exercise exercise;
        private final int sets;

        private Work(Exercise exercise, int sets) {
            this.exercise = exercise;
            this.sets = sets;
        }
    }
}
