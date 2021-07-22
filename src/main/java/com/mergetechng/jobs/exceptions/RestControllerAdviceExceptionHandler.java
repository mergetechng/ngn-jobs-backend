package com.mergetechng.jobs.exceptions;


import com.mergetechng.jobs.commons.dto.RestErrorMessageDto;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestControllerAdviceExceptionHandler  {

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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RestErrorMessageDto resourceNotFoundException(MissingServletRequestParameterException ex, WebRequest request) {
        RestErrorMessageDto message = new RestErrorMessageDto(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                "Failed to get the resource requested"
        );
        return message;
    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RestErrorMessageDto globalUserNotFoundExceptionHandler(UserNotFoundException ex, WebRequest request) {
        RestErrorMessageDto message = new RestErrorMessageDto(
                "Server failed to process your request",
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return message;
//        return ResponseEntity.badRequest().body(new RestErrorMessageDto("Error" , 400 , "Error"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorMessageDto globalIllegalArgumentExceptionHandler(IllegalArgumentException ex, WebRequest request) {
        RestErrorMessageDto message = new RestErrorMessageDto(
                "Server failed to process your request",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
        return message;
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorMessageDto globalMalformedJwtExceptionExceptionHandler(MalformedJwtException ex, WebRequest request) {
        RestErrorMessageDto message = new RestErrorMessageDto(
                "Server failed to process your request",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
        return message;
    }

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorMessageDto globalIncorrectResultSizeDataAccessExceptionExceptionExceptionHandler(IncorrectResultSizeDataAccessException ex, WebRequest request) {
        RestErrorMessageDto message = new RestErrorMessageDto(
                "Query Exception. multiple result set gotten",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
        return message;
    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public RestErrorMessageDto globalExceptionHandler(Exception ex, WebRequest request) {
//        RestErrorMessageDto message = new RestErrorMessageDto(
//                "Server failed to process your request",
//                HttpStatus.NOT_FOUND.value(),
//                "Internal server error occurred."
//        );
//        return message;
//    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorMessageDto globalHttpRequestMethodNotSupportedExceptionHandler(Exception ex, WebRequest request) {
        RestErrorMessageDto message = new RestErrorMessageDto(
                "Server failed to process your request",
                HttpStatus.NOT_FOUND.value(),
                "Internal server error occurred."
        );
        return message;
    }
}