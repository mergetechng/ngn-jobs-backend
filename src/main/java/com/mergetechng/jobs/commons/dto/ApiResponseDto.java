package com.mergetechng.jobs.commons.dto;


import java.util.Date;

public class ApiResponseDto<T> {
    private String message;
    private String statusCode;
    private String action;
    private Date responseDate;
    private T data;

    public ApiResponseDto(){}

    public ApiResponseDto(String message, String statusCode, String action, Date requestDate, T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.action = action;
        this.responseDate = requestDate;
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponseDate(Date requestDate) {
        this.responseDate = requestDate;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getAction() {
        return action;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public Object getData() {
        return data;
    }
}

