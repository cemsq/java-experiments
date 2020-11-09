package com.cem.queries;

import com.cem.persistence.FilterCondition;
import com.cem.persistence.Operation;

public interface OperationQueryWriter extends QueryWriter<Operation, String> {

    default String process(FilterCondition condition) {
        return process(condition.getOperation());
    }
}
