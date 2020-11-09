package cem.etraining.model;

import com.google.common.collect.Lists;

import java.util.Collection;

public class Exercise {

    private final String name;
    private final String description;
    private final Collection<Test> tests = Lists.newArrayList();

    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addTest(Test test) {
        if (test != null) {
            tests.add(test);
        }
    }
}
