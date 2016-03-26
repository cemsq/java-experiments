package cem.rules.api;

import com.google.common.base.Preconditions;

public class Relation<OBJECT> implements RelationChecker<OBJECT> {
    private RelationCondition<OBJECT> leftCondition;
    private RelationCondition<OBJECT> rightCondition;
    private RelationResult result;

    public Relation(RelationCondition<OBJECT> leftCondition, RelationCondition<OBJECT> rightCondition, RelationResult result) {
        Preconditions.checkNotNull(leftCondition, "null leftCondition");
        Preconditions.checkNotNull(rightCondition, "null rightCondition");
        Preconditions.checkNotNull(result, "null result");

        this.leftCondition = leftCondition;
        this.rightCondition = rightCondition;
        this.result = result;
    }

    @Override
    public RelationResult check(OBJECT left, OBJECT right) {
        if (leftCondition.test(left) && rightCondition.test(right)) {
            return result;
        }

        return RelationResult.undefined();
    }
}
