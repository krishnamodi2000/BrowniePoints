package com.GRP3.BPA.utils;

public class Utils {
    public static boolean isValidPassword(String password) {
        if(password.length() >= 8) return true;
        return false;
    }
}
