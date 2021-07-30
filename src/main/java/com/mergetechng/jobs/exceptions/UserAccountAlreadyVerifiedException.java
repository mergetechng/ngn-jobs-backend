package com.mergetechng.jobs.exceptions;

public class UserAccountAlreadyVerifiedException extends BadRequestException{
    public UserAccountAlreadyVerifiedException(String message) {
        super(message);
    }
}
