package workflow;

import com.google.common.base.Preconditions;
import workflow.base.BoolOperation;

public class ConditionReducer<T> {
    private Reducer<T> and;
    private Reducer<T> or;

    public ConditionReducer(Reducer<T> and, Reducer<T> or) {

        this.and = Preconditions.checkNotNull(and);
        this.or = Preconditions.checkNotNull(or);
    }

    public T reduce(T left, T right, BoolOperation operation) {
        switch (operation) {
            case AND: return and.reduce(left, right);

            case OR: return or.reduce(left, right);
        }

        throw new RuntimeException("you should be not here");
    }

}
