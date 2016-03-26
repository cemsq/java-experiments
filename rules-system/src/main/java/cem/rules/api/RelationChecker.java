package cem.rules.api;

public interface RelationChecker<OBJECT> {

    RelationResult check(OBJECT left, OBJECT right);
}
