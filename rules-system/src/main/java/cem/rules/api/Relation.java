package cem.rules.api;

import com.google.common.base.Preconditions;

public class Relation<OBJECT> implements RelationChecker<OBJECT> {
    private RelationCondition<OBJECT> leftCondition;
    private RelationCondition<OBJECT> rightCondition;
    private ResultProvider resultProvider;

    public Relation(RelationCondition<OBJECT> leftCondition, RelationCondition<OBJECT> rightCondition, ResultProvider result) {
        Preconditions.checkNotNull(leftCondition, "null leftCondition");
        Preconditions.checkNotNull(rightCondition, "null rightCondition");
        Preconditions.checkNotNull(result, "null resultProvider");

        this.leftCondition = leftCondition;
        this.rightCondition = rightCondition;
        this.resultProvider = result;
    }

    @Override
    public RelationResult check(OBJECT left, OBJECT right) {
        if (leftCondition.test(left) && rightCondition.test(right)) {
            return resultProvider.get();
        }

        return RelationResult.undefined();
    }
}
