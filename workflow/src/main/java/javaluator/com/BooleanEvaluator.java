package javaluator.com;

import com.fathzer.soft.javaluator.AbstractEvaluator;
import com.fathzer.soft.javaluator.BracketPair;
import com.fathzer.soft.javaluator.Operator;
import com.fathzer.soft.javaluator.Parameters;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BooleanEvaluator extends AbstractEvaluator<Object> {

    /** The logical AND operator.*/
    private static final Operator AND = new Operator("&&", 2, Operator.Associativity.LEFT, 2);
    /** The logical OR operator.*/
    public final static Operator OR = new Operator("||", 2, Operator.Associativity.LEFT, 1);

    public final static Operator GREATER = new Operator(">", 2, Operator.Associativity.LEFT, 3);


    private static final Parameters PARAMETERS;

    static {
        // Create the evaluator's parameters
        PARAMETERS = new Parameters();
        // Add the supported operators
        PARAMETERS.add(AND);
        PARAMETERS.add(OR);
        PARAMETERS.add(GREATER);
        PARAMETERS.addExpressionBracket(BracketPair.PARENTHESES);
    }

    public BooleanEvaluator() {
        super(PARAMETERS);
    }

    @Override
    protected Object toValue(String literal, Object evaluationContext) {

        Object obj = ((Map<String, Object>) evaluationContext).get(literal);
        if (obj != null) {
            return obj;
        }

        try {
            return Double.parseDouble(literal);
        } catch (Exception e) {
            //
        }

        return literal;
    }

    @Override
    protected Object evaluate(Operator operator, Iterator<Object> operands, Object evaluationContext) {

        if (operator == OR) {
            Object o1 = operands.next();
            Object o2 = operands.next();
            return (boolean) o1 || (boolean)o2;
        } else if (operator == AND) {
            Object o1 = operands.next();
            Object o2 = operands.next();
            return (boolean) o1 && (boolean)o2;
        } else if (operator == GREATER) {
            Object o1 = operands.next();
            double o2 = (double)operands.next();

            if (o1 instanceof List) {
                List<Double> list = (List<Double>) o1;
                for (Double aDouble : list) {
                    if (aDouble > o2) {
                        return true;
                    }
                }

                return false;
            }

            return (double)o1 > o2;
        }
        else {
            return super.evaluate(operator, operands, evaluationContext);
        }
    }


    public static void main(String[] args) {
        BooleanEvaluator evaluator = new BooleanEvaluator();
        String expression = "(12 > 1) && x > 20";

        Map<String, Object> map = Maps.newHashMap();
        map.put("x", Lists.newArrayList(30D, 3D));

        System.out.println (expression+" = "+evaluator.evaluate(expression, map));
    }
}
