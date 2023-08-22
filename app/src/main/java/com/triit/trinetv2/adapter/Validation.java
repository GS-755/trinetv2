package com.triit.trinetv2.adapter;

import java.util.regex.Pattern;

public class Validation {
    public static boolean isValidNumber(String input) {
        String numberPattern = "^[-+]?\\d+(\\.\\d+)?$";

        return Pattern.matches(numberPattern, input);
    }
}
