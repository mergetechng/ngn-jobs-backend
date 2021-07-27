package com.mergetechng.jobs.exceptions;


public class JobNotExistsException extends Exception{
    private static final long serialVersionUID = 1L;
    public JobNotExistsException(String message){
        super(message);
    }
}
