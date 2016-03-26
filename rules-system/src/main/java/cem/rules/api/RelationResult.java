package cem.rules.api;

public class RelationResult {
    private Action action;
    private String reason;

    public static RelationResult create(Action action, String reason) {
        return new RelationResult(action, reason);
    }

    public static RelationResult allow() {
        return create(Action.ALLOW, "Relation allowed");
    }

    public static RelationResult undefined() {
        return create(Action.UNDEFINED, "Relation undefined");
    }

    public static RelationResult deny(String message) {
        return create(Action.DENY, message);
    }

    private RelationResult(Action action, String reason) {
        this.action = action;
        this.reason = reason;
    }

    public boolean isAllowed() {
        return action.equals(Action.ALLOW);
    }

    public boolean isDenied() {
        return action.equals(Action.DENY);
    }

    public Action getAction() {
        return action;
    }

    public String getReason() {
        return reason;
    }
}
