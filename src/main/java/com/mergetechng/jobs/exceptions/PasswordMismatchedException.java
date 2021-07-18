package com.mergetechng.jobs.exceptions;

public class PasswordMismatchedException extends RuntimeException{
    public PasswordMismatchedException(String message) {
        super(message);
    }
}
