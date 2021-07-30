package com.mergetechng.jobs.exceptions;


public class NgJobApplicationClosedException extends BadRequestException{
    public NgJobApplicationClosedException(String message){
        super(message);
    }
}
