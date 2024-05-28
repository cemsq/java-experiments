package org.cem.tvolumn.model;

import org.testng.annotations.Test;

/**
 *
 */
public class ExerciseTest {

    @Test
    public void testPush() {

        compute(new Session()
                .add(Exercises.chestPress, 2)
                .add(Exercises.chestIncl, 1)
                .add(Exercises.dips, 3)
                .add(Exercises.shoulderPress, 1)
                .add(Exercises.lateralRaises, 2)
                .add(Exercises.tricepsExt, 2)

                , new Session()
                        .add(Exercises.shoulderPress, 2)
                        .add(Exercises.lateralRaises, 2)
                        .add(Exercises.chestPress, 1)
                        .add(Exercises.chestIncl, 1)
                        .add(Exercises.tricepsMac, 1)
                        .add(Exercises.tricepsExt, 2)

        );
    }

    private void compute(Session ...sessions) {
        Volume vol = new Volume();
        for (Session session : sessions) {
            session.computeVolume(vol);
        }

        System.out.println(vol.toString());
    }
}
