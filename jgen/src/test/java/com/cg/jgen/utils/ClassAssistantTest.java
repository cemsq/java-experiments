package com.cg.jgen.utils;

import com.cg.helix.persistence.metadata.annotation.BusinessObject;
import com.cg.helix.schemadictionary.annotation.ComplexType;
import com.cg.helix.util.annotation.Flag;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 */
public class ClassAssistantTest {

    @ComplexType(optimisticLocking = true)
    @BusinessObject
    private class OptimisticLockingByComplexType {

    }

    @ComplexType
    @BusinessObject(optimisticLocking = Flag.TRUE)
    private class OptimisticLockingByBusinessObject {

    }


    @Test
    public void shouldPass_OptimisticLockingByComplexType() {
        boolean actual = ClassAssistant.optimisticLocking(OptimisticLockingByComplexType.class);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldPass_OptimisticLockingByBusinessObject() {
        boolean actual = ClassAssistant.optimisticLocking(OptimisticLockingByBusinessObject.class);
        boolean expected = true;

        Assert.assertEquals(actual, expected);
    }
}
