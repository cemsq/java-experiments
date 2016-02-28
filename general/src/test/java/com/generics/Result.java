package com.generics;

import java.util.List;

/**
 * Created by cesar on 12/17/2015.
 */
public class Result<T> {

    T get(T t) {
        return t;
    }
}

class ListResult<T> extends Result<List<T>> {
    @Override
    List<T> get(List<T> ts) {
        return super.get(ts);
    }
}


