package com.mergetechng.jobs.exceptions;

public class NgJobExpiredException extends IllegalStateException{
    public NgJobExpiredException(String message){
        super(message);
    }
}
