package com.mergetechng.jobs.exceptions;

public class NgJobApplicationSuspendedException extends BadRequestException{
    public NgJobApplicationSuspendedException(String message) {
        super(message);
    }
}
