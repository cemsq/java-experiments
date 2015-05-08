package com;

/**
 * Created by César Mora on 03.10.2014.
 */
public class DogViewer {
    private Dog dog;

    public DogViewer(Dog dog){
        this.dog = dog;
    }

    public Dog getDog(){
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "DogViewer{" +
                "dog=" + dog +
                '}';
    }
}
