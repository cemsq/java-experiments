package com.jtlog.serialization;

import java.util.Map;

/**
 *
 */
public class StringWriterImpl implements Writer {

    private final Map<Separator, String> separators;
    private final StringBuilder sb;

    public StringWriterImpl(Map<Separator, String> separators, StringBuilder sb) {
        this.separators = separators;
        this.sb = sb;
    }

    @Override
    public void addValue(String value) {
        put(value);
    }

    @Override
    public void add(Separator separator) {
        String val = separators.get(separator);
        put(val);
    }

    private void put(String value) {
        if (value != null) {
            sb.append(value);
        }
    }
}
