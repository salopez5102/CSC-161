package DataBase;

import Interface.RolesContract;
import Items.Roles;
import javax.management.relation.Role;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RolesDatabase implements RolesContract {

    DatabaseConnection conn = new DatabaseConnection();
    private Roles userRole = null;

    @Override
    public Role ViewRole(int uid) {
        Roles returnme = null;
        try {
            ResultSet results = conn.getConnection().createStatement().executeQuery(
                    "SELECT * FROM Roles WHERE uid=" + uid + ";"
            );
            while (results.next()) {
                returnme = Roles.cloneRoles(
                        results.getInt(1),      /*results.getInt("uid"),*/
                        results.getString("rolename"),
                        results.getString("targetname"),
                        results.getBoolean("permissions-view"),
                        results.getBoolean("permissions-add"),
                        results.getBoolean("permissions-delete"),
                        results.getBoolean("permissions-edit"),
                        results.getBoolean("permissions-reload")
                );
            }
        } catch (Exception any) {
            any.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean AddRole(Roles addrole) {
        try {
            conn.getConnection().createStatement().executeUpdate(
                    "INSERT INTO Roles (uid, rolename, targetname, `permissions-view`, `permissions-add`, `permissions-delete`, `permissions-edit`, `permissions-reload`)" +
                            " VALUES (" + addrole.getUid() +
                            ", '" + addrole.getRolename() +
                            "', '" + addrole.getTargetname() +
                            "', " + (addrole.canView()?"1":"0") +
                            ", " + (addrole.canAdd()?"1":"0") +
                            ", " + (addrole.canDelete()?"1":"0") +
                            ", " + (addrole.canEdit()?"1":"0") +
                            ", " + (addrole.canReload()?"1":"0") + ");"
            );
        } catch (SQLException any) {
            any.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean DeleteRole(int uid) {
        try {
            conn.getConnection().createStatement().executeUpdate(
                    "DELETE FROM Roles WHERE uid=" + uid + ";"
            );
        } catch (SQLException any) {
            any.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean EditRole(int replaceme, Roles replacewith) {
        return DeleteRole(replaceme) && AddRole(replacewith);
    }

    @Override
    public Role ReloadRole(int uid) {
        return ViewRole(uid);
    }

    public boolean check(String username) {
        userRole = null;
        try {
            ResultSet results = conn.getConnection().createStatement().executeQuery(
                    "SELECT * FROM Roles WHERE rolename='" + username + "';"
            );
            while (results.next()) {
                userRole = Roles.cloneRoles(
                        results.getInt("uid"),
                        results.getString("rolename"),
                        results.getString("targetname"),
                        results.getBoolean("permissions-view"),
                        results.getBoolean("permissions-add"),
                        results.getBoolean("permissions-delete"),
                        results.getBoolean("permissions-edit"),
                        results.getBoolean("permissions-reload")
                );
            }
        } catch (Exception any) {
            any.printStackTrace();
        }
        //System.out.println(userRole.getRolename());             //checks what role is rolename
        return (userRole != null);

    }
    public Roles getUserRole() {
        //System.out.println(userRole);                         //gets location of role
        return userRole;
    }
}
