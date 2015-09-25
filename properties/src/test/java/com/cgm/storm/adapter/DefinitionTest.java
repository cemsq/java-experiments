package com.cgm.storm.adapter;

import com.cgm.storm.adapter.examples.PersonTest;
import com.cgm.storm.adapter.examples.PetTest;
import com.cgm.storm.utils.properties.PropertyCategory;
import com.cgm.storm.utils.properties.PropertyItem;
import com.cgm.storm.utils.properties.PropertySet;
import com.cgm.storm.utils.properties.adapter.PropertyAdapter;
import com.cgm.storm.utils.properties.adapter.exception.PropertyAdapterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class DefinitionTest {

    private static final Logger log = LoggerFactory.getLogger(DefinitionTest.class);

    @DataProvider(name = "person-provider")
    public Object[][] PersonProvider() {
        PersonTest p = new PersonTest();
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setAge(28);
        p.setPet(new PetTest("Snoopy"));
        return new Object[][]{{ p }};
    }

    @Test (expectedExceptions = IllegalStateException.class, dataProvider = "person-provider")
    public void shouldFail_PropertyAdapterWithoutDefinitions(PersonTest p){
        PropertyAdapter<PersonTest> adapter = new PropertyAdapter<>(PersonTest.class, "a Person Adapter");

        try{
            PropertySet properties = adapter.createPropertySet(p);
        }catch (IllegalStateException e){
            log.info("{}", e.getMessage());
            throw e;
        }
    }

   @Test (dataProvider = "person-provider", expectedExceptions = PropertyAdapterException.class)
    public void shouldFail_wrongFieldNameInRootClass(PersonTest p) {
        try {
            PropertyAdapter<PersonTest> pa = new PropertyAdapter<>(PersonTest.class, "a Person test");
            String category = "Personal Data";

            // there is no 'name' member in Person class
            pa.defineProperty(category, "name", "Vorname", "first name description");
            pa.defineProperty(category, "lastName", "Nachname", "last name description");
            pa.defineProperty(category, "age", "Age", "age description");

            pa.defineProperty("Pet Info", "pet", "name", "pet name", "pet name description");

            PropertySet ps = pa.createPropertySet(p);
            log.debug("{}", ps);
        }catch (PropertyAdapterException e) {
            log.info("{}", e.getMessage());
            throw e;
        }
    }

    @Test (dataProvider = "person-provider", expectedExceptions = PropertyAdapterException.class)
    public void shouldFail_wrongFieldNameInMemberClass(PersonTest p) {
        try {
            PropertyAdapter<PersonTest> pa = new PropertyAdapter<>(PersonTest.class, "a Person test");
            String category = "Personal Data";

            pa.defineProperty(category, "firstName", "Vorname", "first name description");
            pa.defineProperty(category, "lastName", "Nachname", "last name description");
            pa.defineProperty(category, "age", "Age", "age description");

            // there is no 'age' member in Pet class
            pa.defineProperty("Pet Info", "pet", "age", "pet name", "pet name description");

            PropertySet ps = pa.createPropertySet(p);
            log.debug("{}", ps);
        }catch (PropertyAdapterException e) {
            log.info("{}", e.getMessage());
            throw e;
        }
    }

    @Test (dataProvider = "person-provider")
    public void should_createPropertySet(PersonTest p){
        PropertyAdapter<PersonTest> pa = new PropertyAdapter<>(PersonTest.class, "a Person Adapter");

        String cat = "Personal Data";

        pa.defineProperty(cat, "firstName", "Vorname", "first name description");
        pa.defineProperty(cat, "lastName", "Nachname", "last name description");
        pa.defineProperty(cat, "age", "Age", "age description");

        cat = "Pet Info";
        pa.defineProperty(cat, "pet", "name", "pet name", "pet name description");

        PropertySet ps = pa.createPropertySet(p);
        Assert.assertNotNull(ps);
        // --------------------------------------------------

        Assert.assertEquals(ps.getInfo(), "a Person Adapter");

        PropertyCategory category = ps.getCategory("Personal Data");
        Assert.assertNotNull(category);
        Assert.assertEquals(3, category.getItemList().size());

        PropertyItem item = category.getItemByFieldName("age");
        Assert.assertNotNull(item);
        Assert.assertEquals(item.getType(), "Integer");

        category = ps.getCategory("Pet Info");
        Assert.assertNotNull(category);

        item = category.getItemByFieldName("name");
        Assert.assertNotNull(item);
    }
}
