package com.jgen.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class IgnoredColumns {
    private static final Logger log = LoggerFactory.getLogger(IgnoredColumns.class);
    private Map<String, Object> columns = new HashMap<>();

    public IgnoredColumns add(Column column) {

        if (column != null && !contain(column)) {
            columns.put(column.getName(), null);
        }

        return this;
    }

    public boolean contain(Column column) {
        return column != null && contain(column.getName());
    }

    public boolean contain(String columnName) {
        return columns.containsKey(columnName);
    }

    public int size() {
        return columns.size();
    }
}
