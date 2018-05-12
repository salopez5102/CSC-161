package edu.ccd.model.inventoryitems;

import DataBase.InventorySystem;

public class Keyboard extends InventorySystem {
    public Keyboard() {

    }

    public Keyboard clone(Keyboard copyfrom) {
        this.name = copyfrom.name;
        this.worth = copyfrom.worth;
        this.inventorynumber = copyfrom.inventorynumber;
        return this;
    }

    public Keyboard(String name, float value) {
        this.name = name;
        worth = value;
    }

}
