package com.cg.jgen.utils.idgenerator;

import com.cg.helix.util.IdGenerator;

/**
 * call of Helix id generator
 */
public class IdGeneratorImpl implements IIdGenerator {
    public String createUniqueId() {
        return IdGenerator.createUniqueId();
    }
}
