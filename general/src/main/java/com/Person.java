package com;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * Created by César Mora on 03.10.2014.
 */
public class Person implements Comparable<Person>{
    private String name;
    private Dog dog;

    public Person(String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
        this.name = name;
    }

    public Person(String name, Dog dog) {
        this.name = name;
        this.dog = dog;
    }

    public static Person create(String name, Dog dog){
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name));

        return new Person(name, dog);
    }

    public static Person create(String name){
        return create(name, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public int compareTo(Person o) {
        if (o == null) return -1;

        return this.name.compareTo(o.getName());
    }

    @Override
    public Object clone() {
        Person p = Person.create(this.name);
        p.dog = this.dog;
        return p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (dog != null ? !dog.equals(person.dog) : person.dog != null) return false;
        return !(name != null ? !name.equals(person.name) : person.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dog != null ? dog.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", dog=" + dog +
                '}';
    }
}
