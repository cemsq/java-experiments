package exp.dstore;

import datastore.Person;
import datastore.PseudoDataStore;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by cesar on 21/03/2015.
 */
public class DataStoreTest {

    Person p1 = new Person("Alan", 28);
    Person p2 = new Person("Carl", 43);
    Person p3 = new Person("John", 34);

    @Test
    public void shouldNotAddRepeatedReferences() {
        PseudoDataStore<Person> ds = new PseudoDataStore<>();

        ds.add(p1);
        ds.add(p2);
        ds.add(p3);

        ds.add(p1);

        checkSize(ds, 3);
    }

    @Test
    public void shouldGetValueByIndex() {
        PseudoDataStore<Person> ds = new PseudoDataStore<>();

        ds.add(p1);
        ds.add(p2);
        ds.add(p3);

        Person p = ds.getByIndex(1);
        Assert.assertSame(p, p2);
    }

    @Test
    public void shouldSetRow() {
        PseudoDataStore<Person> ds = new PseudoDataStore<>();

        ds.add(p1);
        ds.add(p2);
        ds.add(p3);

        Person p = new Person("Chuck", 45);
        ds.set(1, p);

        checkSize(ds, 3);
        Assert.assertEquals(ds.getByIndex(1), p);
        Assert.assertEquals(ds.indexOf(p), 1);
        Assert.assertEquals(ds.indexOf(p2), -1);

        System.out.println(ds);
    }

    @Test
    public void shouldRemove() {
        PseudoDataStore<Person> ds = new PseudoDataStore<>();

        ds.add(p1);
        ds.add(p2);
        ds.add(p3);

        Assert.assertEquals(ds.indexOf(p1), 0);
        Assert.assertEquals(ds.indexOf(p2), 1);
        Assert.assertEquals(ds.indexOf(p3), 2);

        ds.remove(p1);
        checkSize(ds, 2);
        Assert.assertEquals(ds.indexOf(p1), -1);
        Assert.assertEquals(ds.indexOf(p2), 0);
        Assert.assertEquals(ds.indexOf(p3), 1);

        ds.removeByIndex(0);
        checkSize(ds, 1);
        Assert.assertEquals(ds.indexOf(p2), -1);
        Assert.assertEquals(ds.indexOf(p3), 0);
    }


    public void checkSize(PseudoDataStore ds, int size) {
        Assert.assertEquals(ds.size(), size);
        Assert.assertEquals(ds.getList().size(), size);
        Assert.assertEquals(ds.getByReference().size(), size);
    }
    // TODO:
    //  - unique fields
    //  - filters
    //  - sort

}

