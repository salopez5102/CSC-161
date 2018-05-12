package edu.ccd.model.inventoryitems;

import DataBase.SerialNumber;

public class Monitor extends SerialNumber {

    public Monitor() {};

    public Monitor(String name, float value) {
        name = name;
        worth = value;
    }

    public Monitor clone(Monitor copyfrom) {
        this.name = copyfrom.name;
        this.worth = copyfrom.worth;
        this.inventorynumber = copyfrom.inventorynumber;
        this.setSerialNumber(copyfrom.getSerialNumber());
        return this;
    }
}
