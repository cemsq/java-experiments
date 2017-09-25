package workflow.tokenizer;

public class Token {

    public static final Token LAST = new Token(Type.END, null);
    public static final Token OPEN = new Token(Type.OPEN_PAR, "(");
    public static final Token CLOSE = new Token(Type.CLOSE_PAR, ")");

    public enum Type {
        OPEN_PAR,
        CONDITION,
        CLOSE_PAR,
        OPERATION,
        END;
    }

    private Type type;
    private Object object;

    public Token(Type type, Object object) {
        this.type = type;
        this.object = object;
    }

    public Type getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }
}
