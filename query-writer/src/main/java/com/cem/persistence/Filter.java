package com.cem.persistence;

import java.util.List;

public class Filter {

    private List<FilterCondition> conditions;
    private List<Filter> subFilters;
    private BooleanOperation booleanOperation;
}
