package com.triit.trinetv2.model;

import java.time.LocalTime;

public class Data {
    private LocalTime time;
    private double data;

    public Data() {
        this.time = LocalTime.now();
    }
    public Data(double data) {
        this.time = LocalTime.now();
        this.data = data;
    }

    public LocalTime getTime() { return this.time; }
    public double getData() { return this.data; }
    public void setData(double data) { this.data = data; }

    @Override
    public String toString() {
        return String.format("Data[value = %s]", this.data);
    }
}
