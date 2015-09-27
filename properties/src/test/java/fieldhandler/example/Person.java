package fieldhandler.example;

import fieldhandler.example.item.Item;

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

    private List<Pet> pets;

    private List<String> numbers;

    private Item item;

    public Person() {

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

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
