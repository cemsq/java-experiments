package workflow.base;

public class Condition {

    private String open;
    private String close;
    private Criterion criterion;
    private Operator operator;
    private String value;
    private BoolOperation boolOperation;

    public Condition(Criterion criterion, Operator operator, String value, BoolOperation boolOperation) {
        this.criterion = criterion;
        this.operator = operator;
        this.value = value;
        this.boolOperation = boolOperation;
    }

    public Condition(String open, Criterion criterion, Operator operator, String value, String close, BoolOperation boolOperation) {
        this.criterion = criterion;
        this.operator = operator;
        this.value = value;
        this.boolOperation = boolOperation;

        this.open = open;
        this.close = close;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public Operator getOperator() {
        return operator;
    }

    public String getValue() {
        return value;
    }

    public BoolOperation getBoolOperation() {
        return boolOperation;
    }

    public int getOpen() {
        return open != null? open.length() : 0;
    }

    public int getClose() {
        return close != null? close.length() : 0;
    }
}
