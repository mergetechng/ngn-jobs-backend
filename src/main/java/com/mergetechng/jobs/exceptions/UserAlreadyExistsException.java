package com.mergetechng.jobs.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message){
        super(message);
    }
    public UserAlreadyExistsException(String message , Throwable throwable){
        super(message, throwable);
    }
}
