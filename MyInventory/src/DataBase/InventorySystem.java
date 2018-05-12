package DataBase;

public abstract class InventorySystem {
    public static int inventorynumber = 0;
    public int Num = 0;

    public String name;                                                             // name of the item
    public float worth;                                                             // worth of the item

    public InventorySystem(){
        inventorynumber++;
        Num = inventorynumber;
    }
    public String getName(){
        return name;
    }
    public float getWorth(){
        return worth;
    }

    public InventorySystem( String name ) {
        name = name;
    }

    public InventorySystem cloneInventory(InventorySystem copyfrom) {
        this.name = copyfrom.name;
        this.worth = copyfrom.worth;
        this.inventorynumber = copyfrom.inventorynumber;
        return this;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public void setInventoryNumber(int uid) { this.inventorynumber = uid; }

    public void setValue(float value) { this.worth = value; }

    protected static void setUid(int StartNumber){inventorynumber =StartNumber;}

    public int increaseuid(int bigger){
        Num=bigger+1;
              return Num;
    }

    public int getInventoryNumber() {
        return Num;                  //return num because it will change thing later
    }

}
