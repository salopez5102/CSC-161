package edu.ccd.model.inventoryitems;

import DataBase.SerialNumber;

public class CPU extends SerialNumber {

    public CPU() {};

    public CPU(String name, float value) {
        if(value < 1) {
            worth = 1;
        } else {
            worth = value;
        }
        name = name;
    }

    public CPU clone(CPU copyfrom) {
        this.name = copyfrom.name;
        this.worth = copyfrom.worth;
        this.inventorynumber = copyfrom.inventorynumber;
        this.setSerialNumber(copyfrom.getSerialNumber());
        return this;
    }
}
