package com.GRP3.BPA.service;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class EmailValidator {

    private static final String EMAIL_PATTERN = "^[\\w\\.\\d]+@dal\\.ca$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * This method checks if the provided email address is valid or not.
     *
     * @param email the email address to be validated
     * @return true if the email is valid according to the regular expression, false otherwise
     */
    public static boolean isValid(String email) {
        return pattern.matcher(email).matches();
    }
}

