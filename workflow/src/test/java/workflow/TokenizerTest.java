package workflow;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;
import workflow.base.*;
import workflow.tokenizer.Token;
import workflow.tokenizer.Tokenizer;

import java.util.ArrayList;

public class TokenizerTest {

    @Test
    public void test() {
        ArrayList<Condition> conditions = Lists.newArrayList(
                new Condition("((", Criterion.A, Operator.equal, "b", "", null),
                new Condition("", Criterion.P, Operator.different, "c", ")", BoolOperation.OR),
                new Condition("", Criterion.P, Operator.different, "c", ")", null)
        );

        Rule rule = new Rule(conditions);
        Tokenizer tokenizer = new Tokenizer(rule);

        Token token = tokenizer.nextToken();

        StringBuilder sb = new StringBuilder();
        while (!token.equals(Token.LAST)) {
            Token.Type type = token.getType();
            sb.append(type);
            sb.append(" ");

//            System.out.("d");
//            throw new RuntimeException("asd");

            token = tokenizer.nextToken();

//            try {
//
//                token = tokenizer.nextToken();
//            } catch (Exception e) {
//                System.out.printf("what!!!");
//                token = Token.LAST;
//                throw e;
//            }
        }

        System.out.println(sb);
    }
}

