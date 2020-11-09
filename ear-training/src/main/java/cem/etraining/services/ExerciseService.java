package cem.etraining.services;

import cem.etraining.model.Exercise;

public interface ExerciseService {

    void save(Exercise exercise);

    void delete(String exercise);

    Exercise find(String name);
}
