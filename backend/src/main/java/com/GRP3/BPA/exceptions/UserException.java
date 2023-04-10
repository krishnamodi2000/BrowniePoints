package com.GRP3.BPA.exceptions;

/**
 * Custom exception class for user-related exceptions.
 */
public class UserException {

    /**
     * The error message associated with the exception.
     */
    String message;


    /**
     * Constructs a new UserException with the specified error message.
     *
     * @param message the error message to associate with the exception
     */
    public UserException(String message) {
        this.message = message;
    }


    /**
     * Returns the error message associated with the exception.
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}