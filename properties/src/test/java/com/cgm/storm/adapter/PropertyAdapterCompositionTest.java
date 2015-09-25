package com.cgm.storm.adapter;

import com.cgm.storm.adapter.examples.PersonTest;
import com.cgm.storm.adapter.examples.PersonWithPetPropertyAdapterExample;
import com.cgm.storm.adapter.examples.PetTest;
import com.cgm.storm.utils.properties.PropertyCategory;
import com.cgm.storm.utils.properties.PropertyItem;
import com.cgm.storm.utils.properties.PropertySet;
import com.cgm.storm.utils.properties.adapter.exception.PropertyAdapterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * Created by César Mora on 02.12.2014.
 */
public class PropertyAdapterCompositionTest {
    private static final Logger log = LoggerFactory.getLogger(PropertyAdapterCompositionTest.class);

    @DataProvider(name = "person-provider")
    public Object[][] PersonProvider() {
        PersonTest p = new PersonTest();
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setAge(28);
        p.setMoney(BigDecimal.valueOf(15000));
        p.setPet(new PetTest("Snoopy"));
        return new Object[][]{{ p }};
    }

    @Test(expectedExceptions = PropertyAdapterException.class, dataProvider = "person-provider")
    public void shouldFail_NullPetMember(PersonTest p){
        PersonWithPetPropertyAdapterExample adapter = new PersonWithPetPropertyAdapterExample();
        try {
            // pet member in Person is now null.
            p.setPet(null);

            // PropertyAdapter requires that all fields have memory
            // It should fail
            PropertySet properties = adapter.createPropertySet(p);
        }catch (PropertyAdapterException e) {
            log.info("{}", e.getMessage());
            throw e;
        }
    }

    @Test
    public void should_StorePropertiesIntoPersonWithPet(){
        PersonWithPetPropertyAdapterExample adapter = new PersonWithPetPropertyAdapterExample();
        PersonTest p = new PersonTest();
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setAge(39);
        p.setPet(new PetTest("Scooby"));
        p.setMoney(BigDecimal.valueOf(23000));

        PropertySet properties = adapter.createPropertySet(p);

        log.debug("{}",adapter.getDefinitionAsString());

        // ---------------------
        PersonTest p2 = new PersonTest();
        p2.setPet(new PetTest("xyz"));
        // ---------------------

        adapter.storePropertiesToObject(properties, p2);

        Assert.assertEquals(p2.getFirstName(), "John");
        Assert.assertEquals(p2.getLastName(), "Doe");
        Assert.assertEquals(p2.getAge(), Integer.valueOf(39));
        Assert.assertEquals(p2.getMoney().longValue(), 23000);

        Assert.assertEquals(p2.getPet().getName(), "Scooby");
    }

    @Test (expectedExceptions = PropertyAdapterException.class, dataProvider = "person-provider")
    public void shouldFail_UpdatePropertySetWithNullPetMember(PersonTest p){
        PersonWithPetPropertyAdapterExample adapter = new PersonWithPetPropertyAdapterExample();
        try {
            PropertySet properties = adapter.createPropertySet(p);
            // pet member in Person is now null.
            p.setPet(null);

            // PropertyAdapter requires that all fields have memory
            // It should fail
            adapter.updatePropertySet(properties, p);
        }catch (PropertyAdapterException e) {
            log.info("{}", e.getMessage());
            throw e;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void should_UpdatePropertySetFromPersonWithPet(){
        PersonWithPetPropertyAdapterExample adapter = new PersonWithPetPropertyAdapterExample();

        PersonTest original = new PersonTest();
        original.setFirstName("John");
        original.setLastName("Doe");
        original.setAge(30);
        original.setMoney(new BigDecimal(20000));
        original.setPet(new PetTest("Snoopy"));

        PropertySet properties = adapter.createPropertySet(original);

        PropertyCategory category = properties.getCategory("Personal Data");
        PropertyItem item = category.getItemByFieldName("firstName");
        Assert.assertEquals(item.getValue(), "John");

        item = category.getItemByFieldName("lastName");
        Assert.assertEquals(item.getValue(), "Doe");

        item = category.getItemByFieldName("age");
        Assert.assertEquals(item.getValue(), "30");

        category = properties.getCategory("Pet Info");
        item = category.getItemByFieldName("name");
        Assert.assertEquals(item.getValue(), "Snoopy");

        //----------------------------------------
        PersonTest newPerson = new PersonTest();
        newPerson.setFirstName("Cesar");
        newPerson.setLastName("Mora");
        newPerson.setAge(28);
        newPerson.setMoney(new BigDecimal(0));
        newPerson.setPet(new PetTest("Linda"));

        adapter.updatePropertySet(properties, newPerson);
        // ----------------------------------------
        category = properties.getCategory("Personal Data");
        item = category.getItemByFieldName("firstName");
        Assert.assertEquals(item.getValue(), "Cesar");

        item = category.getItemByFieldName("lastName");
        Assert.assertEquals(item.getValue(), "Mora");

        item = category.getItemByFieldName("age");
        Assert.assertEquals(item.getValue(), "28");

        category = properties.getCategory("Pet Info");
        item = category.getItemByFieldName("name");
        Assert.assertEquals(item.getValue(), "Linda");
    }
}
