package com.triit.trinetv2.adapter;

import com.triit.trinetv2.models.Unit;

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
    }
    public static int findIndexBit(String currentBit) {
        int index = -1;
        for(Unit unit : unitBit) {
            if(unit.getUnitName().equals(currentBit)) {
                index = unit.getIndex();
                break;
            }
        }

        return index;
    }
    public static int findIndexByte(String currentByte) {
        int index = -1;
        for(Unit unit : unitByte)
            if(unit.getUnitName().equals(currentByte)) {
                index = unit.getIndex();
                break;
            }

        return index;
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
    }
    public static int getDistance(int mode, int x, int y) {
        if (mode == 0) {
            return unitBit.get(y - 1).getIndex() - unitByte.get(x - 1).getIndex();
        }

        return unitByte.get(x - 1).getIndex() - unitBit.get(y - 1).getIndex();
    }
    public static double calculate(int mode, int size, double value) {
        int realSize = (int)Math.pow(1000, size);
        switch(mode) {
            case 0: {
                // Byte * 8 = bit
                if(size == 0) {
                    return (value * 8);
                } else {
                    if(size < 0) {
                        return (value * 8) / realSize;
                    } else {
                        return (value * 8) * realSize;
                    }
                }
            }
            default: {
                // bit / 8 = Byte
                if(size == 0) {
                    return (value / 8);
                } else {
                    if(size < 0) {
                        return (value / 8) / realSize;
                    } else {
                        return (value / 8) * realSize;
                    }
                }
            }
        }
    }
}
