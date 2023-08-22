package com.triit.trinetv2.adapter;

import com.triit.trinetv2.model.Unit;

import java.util.ArrayList;

public class UnitAdapter {
    public static ArrayList<Unit> unitByte = new ArrayList<>();
    public static ArrayList<Unit> unitBit  = new ArrayList<>();

    public static void clearUnitBit() { unitBit.clear(); }
    public static void clearUnitByte() { unitByte.clear(); }
    public static void loadUnitByte() {
        clearUnitByte();
        unitByte.add(new Unit(1, "B/s"));
        unitByte.add(new Unit(2, "KB/s"));
        unitByte.add(new Unit(3, "MB/s"));
        unitByte.add(new Unit(4, "GB/s"));
        unitByte.add(new Unit(5, "TB/s"));
        unitByte.add(new Unit(6, "PB/s"));
        unitByte.add(new Unit(7, "EB/s"));
        unitByte.add(new Unit(8, "ZB/s"));
        unitByte.add(new Unit(9, "YB/s"));
    }
    public static int findIndexBit(String currentBit) {
        int ans = -1;
        for(Unit unit : unitBit) {
            if(unit.getUnitName().equals(currentBit)) {
                ans = unit.getIndex();
                break;
            }
        }

        return ans;
    }
    public static int findIndexByte(String currentByte) {
        int ans = -1;
        for(Unit unit : unitByte)
            if(unit.getUnitName().equals(currentByte)) {
                ans = unit.getIndex();
                break;
            }

        return ans;
    }
    public static ArrayList<String> getStrBitList() {
        ArrayList<String> strUnitBit = new ArrayList<>();
        for(Unit unit : unitBit) {
            strUnitBit.add(unit.getUnitName());
        }

        return strUnitBit;
    }
    public static ArrayList<String> getStrByteList() {
        ArrayList<String> strUnitByte = new ArrayList<>();
        for(Unit unit : unitByte)
            strUnitByte.add(unit.getUnitName());

        return strUnitByte;
    }
    public static void loadUnitBit() {
        clearUnitBit();
        unitBit.add(new Unit(1, "bps"));
        unitBit.add(new Unit(2, "Kbps"));
        unitBit.add(new Unit(3, "Mbps"));
        unitBit.add(new Unit(4, "Gbps"));
        unitBit.add(new Unit(5, "Tbps"));
        unitBit.add(new Unit(6, "Pbps"));
        unitBit.add(new Unit(7, "Ebps"));
        unitBit.add(new Unit(8, "Zbps"));
        unitBit.add(new Unit(9, "Ybps"));
    }
    public static int getDistance(int mode, int x, int y) {
        switch(mode) {
            case 0: {
                return Math.abs(unitBit.get(y).getIndex() - unitByte.get(x).getIndex());
            }
            default: {
                return Math.abs(unitByte.get(x).getIndex() - unitBit.get(y).getIndex());
            }
        }
    }
    public static double calculate(int mode, int size, double value) {
        int realSize = (int)Math.pow(1000, size);
        switch(mode) {
            case 0: {
                // Byte * 8 = bit
                if(size == 0) {
                    return value * 8;
                } else {
                    if(size < 0) {
                        return value * 8 * realSize;
                    } else {
                        return value * 8 / realSize;
                    }
                }
            }
            default: {
                // bit / 8 = Byte
                if(size == 0) {
                    return value / 8;
                } else {
                    if(size < 0) {
                        return value / 8 * realSize;
                    } else {
                        return value / 8 / realSize;
                    }
                }
            }
        }
    }
}
