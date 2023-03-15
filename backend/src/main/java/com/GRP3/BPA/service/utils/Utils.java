package com.GRP3.BPA.service.utils;

public class Utils {
    public static boolean isValidPassword(String password) {
        if(password.length() >= 8) return true;
        return false;
    }
}
