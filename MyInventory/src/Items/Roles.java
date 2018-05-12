package Items;

public class Roles {

    private long uid =0;
    private String rolename;
    private String name;
    private boolean view;
    private boolean add;
    private boolean delete;
    private boolean edit;
    private boolean reload;
    private String targetname;

    public Roles(){uid++;}

    public Roles(long NewUID){this.uid = NewUID;}

    public boolean canView() { return view; }
    public boolean canAdd() { return add; }
    public boolean canDelete() { return delete; }
    public boolean canEdit() { return edit; }
    public boolean canReload() { return reload; }

    public long getUid() {
        return uid;
    }

    public void setPermissions(boolean view, boolean add, boolean delete, boolean edit, boolean reload) {
        this.view = view;
        this.add = add;
        this.delete = delete;
        this.edit = edit;
        this.reload = reload;
    }
    public void setTargetname(String targetname) {
        this.targetname = targetname;
    }
    public String getTargetname() {
        return targetname;
    }
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    public String getRolename() {
        return this.rolename;
    }

    public static Roles cloneRoles (int uid, String rolename, String targetname, boolean view, boolean add, boolean delete, boolean edit, boolean reload) {
        Roles clone = new Roles(uid);
        clone.uid = uid;
        clone.setRolename(rolename);
        clone.setTargetname(targetname);
        clone.setPermissions(view, add, delete, edit, reload);
        return clone;
    }
}
