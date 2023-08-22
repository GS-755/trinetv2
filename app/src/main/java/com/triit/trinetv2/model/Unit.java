package com.triit.trinetv2.model;

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
    public void setIndex(int index) { this.index = index; }
    public void setUnitName(String unitName) { this.unitName = unitName; }

    @Override
    public String toString() {
        return String.format("Unit[ID: %d, Name: %s]", this.index, this.unitName);
    }
}
