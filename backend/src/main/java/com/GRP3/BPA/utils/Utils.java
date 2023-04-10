package com.GRP3.BPA.utils;

import lombok.Getter;
import lombok.Setter;

public class Utils {

    private static final int MAX_LENGTH=8;
    @Getter @Setter
    private boolean status;

    @Getter @Setter
    private String message;

    public Utils(){

    }


    public Utils(String message, boolean status) {
        this.message = message;
        this.status =status;
    }

    public static boolean isValidPassword(String password) {
        if(password.length() >= MAX_LENGTH) return true;
        return false;
    }
}
