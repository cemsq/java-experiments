package com.cem.persistence;

public class FilterCondition {
    private String field;
    private Object value;
    private Operation operation;
    private boolean caseSensitive;

    public FilterCondition setField(String field) {
        this.field = field;
        return this;
    }

    public FilterCondition setValue(Object value) {
        this.value = value;
        return this;
    }

    public FilterCondition setOperation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public FilterCondition setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        return this;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public Operation getOperation() {
        return operation;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }
}
