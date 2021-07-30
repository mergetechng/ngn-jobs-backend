package com.mergetechng.jobs.exceptions;

public class UserUploadedDocumentNotFoundException extends RuntimeException{
    public UserUploadedDocumentNotFoundException(String message){
        super(message);
    }
}
