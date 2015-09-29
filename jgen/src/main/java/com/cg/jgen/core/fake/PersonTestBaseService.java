package com.cg.jgen.core.fake;

import com.cg.helix.datasource.Filter;
import com.cg.helix.datasource.Range;
import com.cg.helix.datasource.Sort;
import com.cg.helix.mib.annotation.Input;

import java.util.List;

/**
 *
 */
public interface PersonTestBaseService {
    public Long getAllCount();

    public Long filterCount(@Input(name = "filter") Filter filter);

    public List<PersonTest> getAll();

    public List<PersonTest> find(@Input(name = "range") Range range, @Input(name = "sort") Sort sort, @Input(name = "filter") Filter filter);

    public PersonTest findById(@Input(name = "id") String id);

    public PersonTest getById(@Input(name = "id") String id);

    public PersonTest save(@Input(name = "personTest") PersonTest personTest);

    public void delete(@Input(name = "id") String id);
}
