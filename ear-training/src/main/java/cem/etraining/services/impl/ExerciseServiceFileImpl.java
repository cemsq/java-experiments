package cem.etraining.services.impl;

import cem.etraining.model.Exercise;
import cem.etraining.persistence.FileStorage;
import cem.etraining.services.ExerciseService;
import com.google.common.collect.Sets;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExerciseServiceFileImpl implements ExerciseService {

    private final FileStorage storage = new FileStorage("Exercise.txt");
    private final Set<Exercise> exercises = Sets.newHashSet();

    @Override
    public void save(Exercise exercise) {
        if (exercise != null) {
            exercises.add(exercise);
            store();
        }
    }

    @Override
    public void delete(String exercise) {
        exercises.removeIf(e -> e.getName().equals(exercise));
        store();
    }

    @Override
    public Exercise find(String name) {
        return null;
    }

    private void store() {

        List<Exercise> sorted = exercises.stream()
                .sorted(Comparator.comparing(Exercise::getName))
                .collect(Collectors.toList());

        storage.delete();
        storage.writeAll(sorted, Exercise::getName, Exercise::getDescription);
        storage.save();
    }
}
