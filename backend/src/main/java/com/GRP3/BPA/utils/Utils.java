package com.GRP3.BPA.utils;

import lombok.Getter;
import lombok.Setter;

public class Utils {

    @Getter @Setter
    private boolean status;

    @Getter @Setter
    private String message;


    public Utils(String message, boolean status) {
        this.message = message;
        this.status =status;
    }

    public static boolean isValidPassword(String password) {
        if(password.length() >= 8) return true;
        return false;
    }
}
