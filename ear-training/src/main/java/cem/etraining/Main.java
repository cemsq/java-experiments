package cem.etraining;

import cem.etraining.model.Exercise;
import cem.etraining.services.ExerciseService;
import cem.etraining.services.Services;

public class Main {

    public static void main(String ...args) {

        ExerciseService exs = Services.exerciseService();

        exs.save(new Exercise("Ex1", "an exc456"));
        exs.save(new Exercise("Ex01", "an exc456"));

        exs.delete("Ex1");
    }
}
