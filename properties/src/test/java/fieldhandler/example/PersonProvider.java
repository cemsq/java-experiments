package fieldhandler.example;

import java.util.List;

/**
 *
 */
public class PersonProvider {

    public static Person create(String name, int age, List<String> numbers, Pet pet, House house) {
        Person p = new Person();

        p.setName(name);
        p.setAge(age);
        p.setNumbers(numbers);
        p.setPet(pet);
        p.setHouse(house);

        return p;
    }
}
