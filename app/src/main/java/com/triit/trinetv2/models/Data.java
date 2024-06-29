package com.triit.trinetv2.models;

import java.time.LocalTime;

public class Data {
    private LocalTime time;
    private double data;

    public Data() {
        this.data = 0;
        this.time = LocalTime.now();
    }
    public Data(double data) {
        this.data = data;
        this.time = LocalTime.now();
    }

    public LocalTime getTime() { return this.time; }
    public double getData() { return this.data; }
    public void setData(double data) { this.data = data; }
}
