package com.reference;

import com.Dog;
import com.DogViewer;
import com.Person;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ReferenceTest {
    Runtime run;

    @BeforeClass
    public void setUp(){
        System.out.println("init test");
        run = Runtime.getRuntime();
    }

    @Test
    public void references(){
        Person p1 = new Person("John");
        Person p2 = new Person("John");

        // it calls .compareTo() method
        Assert.assertEquals(p1, p2);

        Person john = p1;

        p1 = (Person)p2.clone();
        p2.setName("Allan");

        // compare references
        if (p1 == p2) {
            Assert.fail();
        }

        Assert.assertEquals(p1.getName(), "John");
        Assert.assertEquals(p2.getName(), "Allan");
    }

    @Test
    public void cloningAPerson() {
        Person p = new Person("Carl", new Dog("Scooby"));
        Person copy = (Person)p.clone();

        Assert.assertEquals(copy, p);
        Assert.assertNotSame(copy, p);
    }

    @Test
    public void referenceUnreachable(){
        Person p = new Person("John", new Dog("Scooby"));
        DogViewer dv = new DogViewer(p.getDog());

        Dog d = dv.getDog();
        System.out.println(d);
        Assert.assertEquals(d, p.getDog());

        p = null;
        System.out.println("Dog is still reachable: "+ d);
        Assert.assertNotNull(d);

        d = null;
        System.out.println("Dog is still reachable: " + dv);
        Assert.assertNotNull(dv.getDog());

        dv = null; // dog is unreachable... the Garbage collector will free this memory
        System.out.println(dv);
    }

    @Test
    public void callingGarbageCollector(){

        System.gc(); // garbage collector
        System.out.println("Memory Free before test:       " + run.freeMemory());
        // this is a new Java 7 Feature:
        //      1_000_000  -> 1000000
        //      1_00_00_00 -> 1000000
        Person p = null;
        for (int i=0; i<10_000_000; i++){
             p = new Person("someName " + i, new Dog("a Dog " + i));
        }
        System.out.println("Memory Free before GC call:    " + run.freeMemory());
        System.gc(); // garbage collector
        System.out.println("Memory Free after GC:          " + run.freeMemory());
    }

    @Test
    public void shouldBeEquals() {
        Person p = new Person("name");
        Assert.assertEquals(p.getClass(), Person.class);
    }

    @AfterClass
    public void end(){
        System.out.println("--- ReferenceTest done...");
    }
}
