package com.cem.queries;

import com.cem.persistence.FilterCondition;
import com.cem.persistence.Operation;

import java.util.Map;

public class ConditionQueryWriter implements QueryWriter<FilterCondition, String> {

    private StringBuilder builder;
    private Map<String, Object> context;
    private OperationQueryWriter opWriter;

    @Override
    public String process(FilterCondition input) {

        String varContextName = ":var" + context.size();
        context.put(varContextName, input.getValue());
        builder.setLength(0);
        builder.append(input.getField())
                .append(" ")
                .append(opWriter.process(input))
                .append(" ")
                .append(varContextName);

        return builder.toString();
    }
}
