package cem.etraining.services;

import cem.etraining.services.impl.ExerciseServiceFileImpl;

public class Services {

    private static final ExerciseService exerciseService = new ExerciseServiceFileImpl();

    public static ExerciseService exerciseService() { return exerciseService; }
}
