package com.triit.trinetv2.models;

public class Unit {
    private int index;
    private String unitName;

    public Unit() { }
    public Unit(int index, String unitName) {
        this.index = index;
        this.unitName = unitName;
    }

    public int getIndex() { return this.index; }
    public String getUnitName() { return this.unitName; }
}
