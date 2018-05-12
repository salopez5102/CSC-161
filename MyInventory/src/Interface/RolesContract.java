package Interface;

import Items.Roles;

import javax.management.relation.Role;

public interface RolesContract {
    Role ViewRole(int uid);
    boolean AddRole(Roles addrole);
    boolean DeleteRole(int uid);
    boolean EditRole(int replaceme, Roles replacewith);
    Role ReloadRole(int uid);
}
