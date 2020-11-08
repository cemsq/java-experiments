package cem.etraining.services;

import cem.etraining.model.Test;

import java.util.Collection;

public interface TestService {

    void save(Test test);

    Collection<Test> find(String exerciseName);
}
