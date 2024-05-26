package org.cem.jkal.model;

import com.beust.jcommander.internal.Lists;

import java.util.List;

/**
 *
 */
public class Plan {

    private String name;
    private final List<Meal> meals = Lists.newArrayList();

    public Plan(String name) {
        this.name = name;
    }

    public Plan add(Meal meal) {
        meals.add(meal);
        return this;
    }

    public Consumption compute() {
        Consumption result = new Consumption(name);
        for (Meal meal : meals) {
            result.add(meal.compute());
        }
        return result;
    }
}
