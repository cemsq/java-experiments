package com.cem.queries;

public interface QueryWriter<I, O> {

    O process(I input);
}
