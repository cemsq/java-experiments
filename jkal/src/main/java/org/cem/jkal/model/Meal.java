package org.cem.jkal.model;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 *
 */
public class Meal {

    private String name;
    private final List<Entry> entries = Lists.newArrayList();

    public Meal(String name) {
        this.name = name;
    }

    public Meal add(Food f, double consumed) {
        entries.add(new Entry().setConsumed(consumed).setFood(f));
        return this;
    }

    public Consumption compute() {
        Consumption consumption = new Consumption(name);
        for (Entry e : entries) {
            Consumption c = e.food.consume(e.consumed);
//            System.out.println(c);
            consumption.add(c);
        }
        return consumption;
    }

    @Data
    @Setter
    @Accessors(chain = true)
    private static class Entry {
        private double consumed;
        private Food food;
    }
}
