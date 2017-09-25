package workflow.tokenizer;

import workflow.base.Condition;
import workflow.base.Rule;

import java.util.Iterator;

public class Tokenizer {

    Iterator<Condition> iterator;
    Condition current;
    int openPar;
    int closePar;
    boolean conditionAvailable;
    boolean operatorAvailable;

    public Tokenizer(Rule rule) {
        iterator = rule.getConditions().iterator();
    }

    public Token nextToken() {
        Token token;

        if (current == null && iterator.hasNext()) {
            current = iterator.next();
            openPar = current.getOpen();
            closePar = current.getClose();
            conditionAvailable = true;
            operatorAvailable = current.getBoolOperation() != null;
        }

        if (current != null) {
            if (openPar > 0) {
                openPar--;
                token = Token.OPEN;

            } else if (conditionAvailable) {
                conditionAvailable = false;
                token = new Token(Token.Type.CONDITION, current);

            } else if (closePar > 0) {
                closePar--;
                token = Token.CLOSE;

            } else if (operatorAvailable) {
                token = new Token(Token.Type.OPERATION, current.getBoolOperation());
                operatorAvailable = false;
                current = null;

            } else {
                current = null;
                token = nextToken();

            }

        } else {
            token =  Token.LAST;
        }

        return token;
    }
}
