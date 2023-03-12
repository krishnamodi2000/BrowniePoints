package com.GRP3.BPA.model;

public class UserException extends User{

    String message;

    public UserException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
