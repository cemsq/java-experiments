package properties.example;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Person {

    private String name;
    private int age;

    private House house;

    private Pet pet;

    private List<String> numbers;

    public Person() {
        house = new House();
        pet = new Pet();
    }

    public Person(String name, House house, Pet pet) {
        this.name = name;
        this.house = house;
        this.pet = pet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setNumbers(String... numbers) {
        this.numbers = new ArrayList<>();
        for (String num : numbers) {
            this.numbers.add(num);
        }
    }
}
