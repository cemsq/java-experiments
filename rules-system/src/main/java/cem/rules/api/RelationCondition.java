package cem.rules.api;

public interface RelationCondition<OBJECT> {

    boolean test(OBJECT object);
}
