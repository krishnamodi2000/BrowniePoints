package com.GRP3.BPA.utils;

public class Utils {

    private String message;

    public Utils(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public static boolean isValidPassword(String password) {
        if(password.length() >= 8) return true;
        return false;
    }
}
