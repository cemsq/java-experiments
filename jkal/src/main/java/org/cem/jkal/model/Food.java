package org.cem.jkal.model;

import lombok.experimental.Accessors;

/**
 *
 */
@Accessors(chain = true)
public class Food extends AbstractProduct {

    public Food(String name, double carbs, double protein, double fat) {
        super(name, carbs, protein, fat);
    }

    public Food(String name, double carbs, double protein, double fat, double factor) {
        super(name, carbs, protein, fat, factor);
    }

}
