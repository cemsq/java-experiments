package org.cem.tvolumn.model;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class Exercise {

    private final String name;
    private final List<MuscleFactor> muscles = Lists.newArrayList();

    public Exercise(String name) {
        this.name = name;
    }

    public Exercise add(Muscle m, double f) {
        muscles.add(new MuscleFactor(m, f));
        return this;
    }

    public void computeVolume(int sets, Volume vol) {
        for (MuscleFactor m : muscles) {
            vol.put(m.muscle, (double) sets * m.factor);
        }
    }

    private static final class MuscleFactor {
        private final Muscle muscle;
        private final double factor;

        private MuscleFactor(Muscle muscle, double factor) {
            this.muscle = muscle;
            this.factor = factor;
        }
    }
}
