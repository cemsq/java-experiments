package security;

import java.util.List;

public interface PermissionQuery {

    PermissionQuery forOrgUnit(String orgUnit);
    PermissionQuery forPermission(String permObject, String ...attributes);
    PermissionQuery considerAtLeastOneAttribute();
    PermissionQuery considerAllAttributes();

    boolean isPermitted();
    boolean hasPermission();
    List<String> getOrgUnitIds();
}
