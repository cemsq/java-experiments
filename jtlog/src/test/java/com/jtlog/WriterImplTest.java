package com.jtlog;

import com.google.common.collect.ImmutableMap;
import com.jtlog.model.Exercise;
import com.jtlog.serialization.Separator;
import com.jtlog.serialization.Writable;
import com.jtlog.serialization.StringWriterImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class WriterImplTest {

    @DataProvider
    public Object[][] writeToStringProvider() {
        return new Object[][] {
                {new Exercise()
                        .setId("LegPress")
                        .setNote("Master exercise: without labels"),
                        "# LegPress|Master exercise: without labels|"
                },
                {new Exercise()
                        .setId("LegPress")
                        .setNote("Master exercise: with labels")
                        .addLabel("leg")
                        .addLabel("push"),
                        "# LegPress|Master exercise: without labels|leg,push"
                }
        };
    }

    @Test(dataProvider = "writeToStringProvider")
    public void shouldWriteToString(Writable object, String expected) {


        ImmutableMap<Separator, String> separators = ImmutableMap.<Separator, String>builder()
                .put(Separator.START_RECORD, "")
                .put(Separator.END_RECORD, "\n")
                .put(Separator.VALUE, "|")
                .put(Separator.SUB_VALUE, ",")
                .build();

        StringBuilder sb = new StringBuilder();
        StringWriterImpl writer = new StringWriterImpl(separators, sb);
        object.write(writer);

        Assert.assertEquals(sb.toString(), expected);
    }

    @Test
    public void t1() {
        System.out.println("yeah");
    }
}
