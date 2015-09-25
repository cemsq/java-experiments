package properties.example;

/**
 *
 */
public class Person {

    private String name;
    private int age;

    private House house;

    private Pet pet;

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
}
