package com.jgen.importer;

import com.jgen.model.Column;
import com.jgen.model.IgnoredColumns;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class IgnoredColumnsTest {

    @Test
    public void shouldSaveOnly1() {
        IgnoredColumns columns = new IgnoredColumns();

        Column col = new Column();
        col.setName("name");
        columns.add(col);

        col.setName("name");
        columns.add(col);

        col.setName("name");
        columns.add(col);

        col.setName("name");
        columns.add(col);

        Assert.assertEquals(columns.size(), 1);
    }

    @Test
    public void shouldSave4() {
        IgnoredColumns columns = new IgnoredColumns();

        Column col = new Column();
        col.setName("one");
        columns.add(col);

        col.setName("two");
        columns.add(col);

        col.setName("three");
        columns.add(col);

        col.setName("four");
        columns.add(null);

        Assert.assertEquals(columns.size(), 3);
    }

    @Test
    public void shouldFindColumns() {
        IgnoredColumns columns = new IgnoredColumns();

        Column col = new Column();
        col.setName("one");
        columns.add(col);

        col.setName("two");
        columns.add(col);

        col.setName("three");
        columns.add(col);

        col.setName("four");
        columns.add(col);

        Assert.assertTrue(columns.contain("one"));
        Assert.assertTrue(columns.contain("two"));
        Assert.assertTrue(columns.contain("three"));
        Assert.assertTrue(columns.contain("four"));
    }
}
