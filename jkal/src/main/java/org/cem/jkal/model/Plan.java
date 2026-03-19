package org.cem.jkal.model;

import com.beust.jcommander.internal.Lists;
import lombok.Getter;

import java.util.List;

/**
 *
 */
public class Plan {

    private String name;
    @Getter private boolean enabled = true;
    private final List<Meal> meals = Lists.newArrayList();

    public Plan(String name) {
        this.name = name;
    }

    public Plan enabled(boolean v) {
        enabled = v;
        return this;
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
