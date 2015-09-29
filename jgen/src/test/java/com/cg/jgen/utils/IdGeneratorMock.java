package com.cg.jgen.utils;

import com.cg.jgen.utils.idgenerator.IIdGenerator;

/**
 * mock id generator
 */
public class IdGeneratorMock implements IIdGenerator {
    public String createUniqueId() {
        return "STORM_PSDEMO000095";
    }
}
