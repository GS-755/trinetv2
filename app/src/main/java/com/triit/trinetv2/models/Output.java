package com.triit.trinetv2.models;

public class Output {
    private static double output = 0;

    public static double getOutput() { return output; }
    public static void setOutput(double newOutput) { output = newOutput; }
    public static void clearOutput() { output = 0; }
}
