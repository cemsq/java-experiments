package com.reference;

import com.ArrayContainer;
import com.Dog;
import com.DogContainer;
import org.testng.annotations.Test;

/**
 *
 */
public class ReturningReference {
    @Test
    public void getStringObjectMember() {
        ArrayContainer arrayObject = new ArrayContainer();

        String [] array = arrayObject.getArray();
        System.out.println(arrayObject);

        array[0] = "a";
        System.out.println(arrayObject);

        String [] content = arrayObject.getContent();
        content[0].concat("X");
        System.out.println(arrayObject);
    }

    @Test
    public void getDogsArrays() {
        DogContainer dogContainer = new DogContainer("A", "B", "C");
        System.out.println(dogContainer);

        Dog []dogs = dogContainer.getDogs();
        dogs[0] = new Dog("Apl");
        System.out.println(dogContainer);


        dogs = dogContainer.getContent();
        dogs[1] = new Dog("HDP");
        System.out.println(dogContainer);
    }
}
