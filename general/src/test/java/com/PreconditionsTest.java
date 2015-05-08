package com;

import com.Dog;
import com.Person;
import com.google.common.base.Preconditions;


import com.google.common.base.Strings;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * Created by César Mora on 06.10.2014.
 */
public class PreconditionsTest {

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void shouldFail_checkingEmptyString(){
        String empty = "";

        Preconditions.checkArgument(!Strings.isNullOrEmpty(empty), "empty String");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void shouldFail_checkingNullString(){
        String nullString = null;

        Preconditions.checkArgument(!Strings.isNullOrEmpty(nullString), "null String");
    }


    @Test
    public void shouldPass_checkingANotEmptyString(){
        String notEmpty= "not empty";

        Preconditions.checkArgument(!Strings.isNullOrEmpty(notEmpty), true);
    }

    @Test
    public void validatingInteger() {
        Integer i = 5;
        Preconditions.checkArgument(i > 0, "Integer must be positive");
    }

    @Test (dataProvider = "null-object-provider",
        expectedExceptions = NullPointerException.class)
    public void shouldFail_checkingNullObject(Person p){
        Preconditions.checkArgument(true);
        Preconditions.checkNotNull(p, "Person: null");

        System.out.println(p);
    }

    @Test (dataProvider = "person-provider")
    public void shouldNotFail_checkingObjectWithMemory(Person p){
        Preconditions.checkNotNull(p);

        System.out.println("Person: should not fail");

        System.out.println(p);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void shouldFail_creatingPersonWithEmptyName(){
        Person p = Person.create("");

        System.out.println(p);

    }

    @DataProvider (name = "null-object-provider")
    public Object[][] nullObjectProvider(){
        return new Object[][]{{null}};
    }

    @DataProvider (name = "person-provider")
    public Object[][] personProvider(){
        return new Object[][] {{new Person("Fox", new Dog("a dog"))}};
    }
}
