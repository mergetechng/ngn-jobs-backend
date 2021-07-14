package com.mergetechng.jobs.exceptions;


import com.mergetechng.jobs.commons.dto.RestErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestControllerAdviceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RestErrorMessageDto resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        RestErrorMessageDto message = new RestErrorMessageDto(
                "Resource Not Found and",
                HttpStatus.NOT_FOUND.value(),
                "Failed to get the resource requested"
        );
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorMessageDto globalExceptionHandler(Exception ex, WebRequest request) {
        RestErrorMessageDto message = new RestErrorMessageDto(
                "Server failed to process your request",
                HttpStatus.NOT_FOUND.value(),
                "Internal server error occurred."
        );
        return message;
    }
}