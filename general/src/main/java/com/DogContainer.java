package com;

import java.util.Arrays;

/**
 *
 */
public class DogContainer {
    private Dog dogs[];

    public DogContainer(String ...args) {
        int n = args.length;
        dogs = new Dog[n];

        int i = 0;
        for (String name : args) {
            dogs[i++] = new Dog(name);
        }
    }

    public Dog[] getDogs() {
        return dogs;
    }

    public Dog[] getContent() {
        Dog []copy = new Dog[dogs.length];

        System.arraycopy(dogs, 0, copy, 0, dogs.length);

        return copy;
    }

    @Override
    public String toString() {
        return "dogs= " + Arrays.toString(dogs);
    }
}
