package com.cgm.storm.adapter;

import com.cgm.storm.adapter.examples.PersonPropertyAdapterExample;
import com.cgm.storm.adapter.examples.PersonTest;
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
 *
 */

public class PropertyAdapterTest {

    private static final Logger log = LoggerFactory.getLogger(PropertyAdapterTest.class);

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

    @Test (expectedExceptions = NullPointerException.class)
    public void shouldFail_NullPersonObject(){
        PersonPropertyAdapterExample adapter = new PersonPropertyAdapterExample();
        PersonTest p = null;

        try {
            PropertySet ps = adapter.createPropertySet(p);
        }catch (NullPointerException e){
            log.info("{}", e.getMessage());
            throw e;
        }
    }

    @Test (expectedExceptions = PropertyAdapterException.class, dataProvider = "person-provider")
    public void shouldFail_CategoryNotFound(PersonTest p){
        PersonPropertyAdapterExample adapter = new PersonPropertyAdapterExample();
        PropertySet fake = new PropertySet();
        PropertyCategory cat = new PropertyCategory("category 1");
        cat.addItem(new PropertyItem());
        cat.addItem(new PropertyItem());
        cat.addItem(new PropertyItem());
        fake.addCategory(cat);

        try {
            adapter.updatePropertySet(fake, p);
        }catch(PropertyAdapterException e){
            log.info("{}", e.getMessage());
            throw e;
        }
    }

    @Test (expectedExceptions = PropertyAdapterException.class, dataProvider = "person-provider")
    public void shouldFail_PropertySetNotMatch(PersonTest p){
        PersonPropertyAdapterExample adapter = new PersonPropertyAdapterExample();
        PropertySet fake = new PropertySet();
        fake.setInfo("a fake PropertySet");

        try {
            adapter.storePropertiesToObject(fake, p);
        }catch (PropertyAdapterException e) {
            log.info("{}", e.getMessage());
            throw e;
        }
    }

    @Test (dataProvider = "person-provider")
    public void should_CreatePropertySet(PersonTest p){
        PersonPropertyAdapterExample adapter = new PersonPropertyAdapterExample();
        PropertySet properties = adapter.createPropertySet(p);

        log.debug("{}", properties);
        Assert.assertEquals(properties.getCategoryList().size(), 1);
    }

    @Test
    public void should_StorePropertiesIntoPerson(){
        PersonPropertyAdapterExample adapter = new PersonPropertyAdapterExample();
        PersonTest p = new PersonTest();
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setAge(39);
        p.setMoney(BigDecimal.valueOf(23000));

        PropertySet properties = adapter.createPropertySet(p);

        System.out.println(adapter.getDefinitionAsString());
        log.debug("{}", adapter.getDefinitionAsString());
        // ---------------------
        PersonTest p2 = new PersonTest();
        p2.setPet(new PetTest("xyz"));
        // ---------------------

        adapter.storePropertiesToObject(properties, p2);

        Assert.assertEquals(p2.getFirstName(), "John");
        Assert.assertEquals(p2.getLastName(), "Doe");
        Assert.assertEquals(p2.getAge(), Integer.valueOf(39));
        Assert.assertEquals(p2.getMoney().longValue(), 23000);
    }

    @Test
    public void should_UpdatePropertySetFromPerson(){
        PersonPropertyAdapterExample adapter = new PersonPropertyAdapterExample();

        PersonTest original = new PersonTest();
        original.setFirstName("John");
        original.setLastName("Doe");
        original.setAge(30);
        original.setMoney(new BigDecimal(20000));

        PropertySet properties = adapter.createPropertySet(original);

        PropertyCategory category = properties.getCategory("Personal Data");
        PropertyItem item = category.getItemByFieldName("firstName");
        Assert.assertEquals(item.getValue(), "John");

        item = category.getItemByFieldName("lastName");
        Assert.assertEquals(item.getValue(), "Doe");

        item = category.getItemByFieldName("age");
        Assert.assertEquals(item.getValue(), "30");

        //----------------------------------------
        PersonTest newPerson = new PersonTest();
        newPerson.setFirstName("Cesar");
        newPerson.setLastName("Mora");
        newPerson.setAge(28);
        newPerson.setMoney(new BigDecimal(0));

        adapter.updatePropertySet(properties, newPerson);
        // ----------------------------------------
        category = properties.getCategory("Personal Data");
        item = category.getItemByFieldName("firstName");
        Assert.assertEquals(item.getValue(), "Cesar");

        item = category.getItemByFieldName("lastName");
        Assert.assertEquals(item.getValue(), "Mora");

        item = category.getItemByFieldName("age");
        Assert.assertEquals(item.getValue(), "28");
    }
}
