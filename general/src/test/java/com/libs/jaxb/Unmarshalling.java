package com.libs.jaxb;

import com.generated.Database;
import com.generated.ObjectFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 *
 */
public class Unmarshalling {

    @Test
    public void unmarshalling() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller u = jc.createUnmarshaller();
        Database database = (Database) u.unmarshal(new File("general/storm.xml"));

        Assert.assertNotNull(database);
    }
}
