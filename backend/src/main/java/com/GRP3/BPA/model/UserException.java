package com.GRP3.BPA.model;

public class UserException {

    String message;

    public UserException(String message) {
//        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}