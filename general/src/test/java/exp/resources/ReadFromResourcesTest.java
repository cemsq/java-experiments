package exp.resources;

import datastore.FileResources;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 *
 */
public class ReadFromResourcesTest {
    @Test
    public void shouldGetFromResources() {
        String fileName = FileResources.getFileName("baseText.txt");
        File file = new File(fileName);
        Assert.assertEquals(file.exists(), true);
    }
}
