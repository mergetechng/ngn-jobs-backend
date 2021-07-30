package com.mergetechng.jobs.exceptions;

public class UserAccountEmailNotVerifiedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UserAccountEmailNotVerifiedException(String message) {
        super(message);
    }
}
