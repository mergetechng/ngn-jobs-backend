package com.mergetechng.jobs.exceptions;

public class EmailVerificationLinkSendingFailedException extends RuntimeException{
    public EmailVerificationLinkSendingFailedException(String message){
        super(message);
    }
}
