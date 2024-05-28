package org.cem.tvolumn.model;

/**
 *
 */
public class Exercises {

    public static final Exercise chestPress = new Exercise("ChestPress")
            .add(Muscle.CHEST, 1)
            .add(Muscle.TRICEPS, 0.5)
            .add(Muscle.SHOULDER, 0.5);

    public static final Exercise chestIncl = new Exercise("chestIncl")
            .add(Muscle.CHEST, 1)
            .add(Muscle.TRICEPS, 0.4)
            .add(Muscle.SHOULDER, 0.7);

    public static final Exercise shoulderPress = new Exercise("ShoulderPress")
            .add(Muscle.CHEST, 0.1)
            .add(Muscle.TRICEPS, 0.5)
            .add(Muscle.SHOULDER, 1);

    public static final Exercise lateralRaises = new Exercise("LateralRaises")
            .add(Muscle.DELTOID, 1);

    public static final Exercise dips = new Exercise("Dips")
            .add(Muscle.CHEST, 0.2)
            .add(Muscle.TRICEPS, 1)
            .add(Muscle.SHOULDER, 0.2);

    public static final Exercise tricepsMac = new Exercise("TricepsMachine")
            .add(Muscle.CHEST, 0.2)
            .add(Muscle.TRICEPS, 1)
            .add(Muscle.SHOULDER, 0.2);

    public static final Exercise tricepsExt = new Exercise("TricepsExtension")
            .add(Muscle.TRICEPS, 1);
}
