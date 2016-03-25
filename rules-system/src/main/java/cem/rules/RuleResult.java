package cem.rules;

public class RuleResult {
    private Action action;
    private String reason;

    public static RuleResult create(Action action, String reason) {
        return new RuleResult(action, reason);
    }

    private RuleResult(Action action, String reason) {
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
