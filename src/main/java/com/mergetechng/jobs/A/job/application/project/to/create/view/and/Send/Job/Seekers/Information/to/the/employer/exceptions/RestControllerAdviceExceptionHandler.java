package com.mergetechng.jobs.A.job.application.project.to.create.view.and.Send.Job.Seekers.Information.to.the.employer.exceptions;


import com.mergetechng.jobs.A.job.application.project.to.create.view.and.Send.Job.Seekers.Information.to.the.employer.common.dto.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestControllerAdviceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RestErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        RestErrorMessage message = new RestErrorMessage(
                "Resource Not Found and",
                HttpStatus.NOT_FOUND.value(),
                "Failed to get the resource requested"
        );
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
        RestErrorMessage message = new RestErrorMessage(
                "Server failed to process your request",
                HttpStatus.NOT_FOUND.value(),
                "Internal server error occurred."
        );
        return message;
    }
}