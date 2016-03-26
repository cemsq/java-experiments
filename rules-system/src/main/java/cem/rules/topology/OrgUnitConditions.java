package cem.rules.topology;

import cem.rules.api.RelationCondition;
import cem.rules.test.Characteristic;
import cem.rules.test.OrgUnit;
import cem.rules.test.Type;

public class OrgUnitConditions {

    public final static Integer SYSTEM_ID = 1;

    private OrgUnitConditions() {

    }

    public static RelationCondition<OrgUnit> isSystem() {
        return oue -> SYSTEM_ID.equals(oue.getId());
    }

    public static RelationCondition<OrgUnit> isNull() {
        return oue -> oue == null;
    }

    public static RelationCondition<OrgUnit> any() {
        return oue -> true;
    }

    public static RelationCondition<OrgUnit> hasNoType() {
        return oue -> oue.getType() == null;
    }

    public static RelationCondition<OrgUnit> hasNoCharacteristics() {
        return oue -> oue.getCharacteristics().isEmpty();
    }

    public static RelationCondition<OrgUnit> is(Type type) {
        return oue -> oue.getType().equals(type);
    }

    public static RelationCondition<OrgUnit> isNot(Type type) {
        return oue -> !oue.getType().equals(type);
    }

    public static RelationCondition<OrgUnit> contains(Characteristic chr) {
        return oue -> oue.getCharacteristics().contains(chr);
    }

    public static RelationCondition<OrgUnit> notContains(Characteristic chr) {
        return oue -> !oue.getCharacteristics().contains(chr);
    }
}
