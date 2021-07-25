package com.mergetechng.jobs.exceptions;

public class NgJobTotalAllowedApplicantsExceededException extends BadRequestException{
    public NgJobTotalAllowedApplicantsExceededException(String message) {
        super(message);
    }
}
