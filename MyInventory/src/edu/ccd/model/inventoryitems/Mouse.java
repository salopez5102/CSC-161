package edu.ccd.model.inventoryitems;

import DataBase.InventorySystem;

public class Mouse extends InventorySystem {

    public Mouse() {};

    public Mouse clone(Mouse copyfrom) {
        this.name = copyfrom.name;
        this.worth = copyfrom.worth;
        this.inventorynumber = copyfrom.inventorynumber;
        return this;
    }
}
