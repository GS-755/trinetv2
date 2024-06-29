package com.triit.trinetv2.module;

import com.triit.trinetv2.models.Data;

import java.util.ArrayList;

public class HistoryModule {
    public static ArrayList<Data> dataArrayList = new ArrayList<>();

    public static int getItemCount()
    {
        return dataArrayList.size();
    }
    public static ArrayList<Data> getDataList() { return dataArrayList; }
    public static void addHistory(Data data) { dataArrayList.add(data); }
}
