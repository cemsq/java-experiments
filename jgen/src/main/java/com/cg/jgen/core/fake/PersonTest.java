package com.cg.jgen.core.fake;

import com.cg.helix.persistence.metadata.annotation.BusinessObject;
import com.cg.helix.schemadictionary.annotation.ComplexType;
import com.cg.helix.util.annotation.Flag;

/**
 *
 */
@ComplexType
@BusinessObject(optimisticLocking = Flag.TRUE)
public class PersonTest {
}
