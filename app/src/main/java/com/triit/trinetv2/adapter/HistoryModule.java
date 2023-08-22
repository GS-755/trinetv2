package com.triit.trinetv2.adapter;

import com.triit.trinetv2.model.Data;

import java.util.ArrayList;

public class HistoryModule {
    public static ArrayList<Data> dataArrayList = new ArrayList<>();

    public static ArrayList<Data> getDataList() { return dataArrayList; }
    public static void addHistory(Data data) { dataArrayList.add(data); }
}
