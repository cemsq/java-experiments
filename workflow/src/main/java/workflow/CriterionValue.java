package workflow;

import com.google.common.collect.Maps;
import workflow.base.Criterion;

import java.util.Map;
import java.util.function.Function;

public class CriterionValue {

    Map<Criterion, Function<String, Object>> mapper = Maps.newHashMap();

    public CriterionValue() {
        mapper.put(Criterion.A, (str) -> str);
        mapper.put(Criterion.P, (str) -> str);
        mapper.put(Criterion.Pr, Integer::valueOf);
    }

    public Object get(Criterion criterion, String value) {
        Function<String, Object> function = mapper.get(criterion);
        if (function == null) {
            throw new IllegalArgumentException("criterion not defined: " + criterion);
        }

        return function.apply(value);
    }
}
