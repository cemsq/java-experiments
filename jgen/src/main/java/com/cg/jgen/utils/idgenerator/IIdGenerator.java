package com.cg.jgen.utils.idgenerator;

/**
 * interface for ID generator so that we can replace it by a mock implementation
 */
public interface IIdGenerator {

    public String createUniqueId();

}
