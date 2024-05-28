package org.cem.tvolumn.model;

import lombok.Getter;

/**
 *
 */
@Getter
public enum Muscle {
    CHEST("Chest"),
    TRICEPS("Triceps"),
    SHOULDER("Shoulder"),
    DELTOID("Deltoids"),

    BACK("Back"),
    LATS("Back-lats"),

    QUADS("Quads"),
    HAMSTRING("Hamstring")
    ;

    private final String name;

    Muscle(String name) {
        this.name = name;
    }
}
