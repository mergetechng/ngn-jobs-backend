package com.mergetechng.jobs.common.dto;


import java.util.Objects;

public class ApiResponse {
    private String message;
    private String statusCode;
    private String requestDate;
    private String requestType;
    private Object data;

    public ApiResponse(){}

    public ApiResponse(String message, String statusCode, String requestDate, String requestType, Object data) {
        this.message = message;
        this.statusCode = statusCode;
        this.requestDate = requestDate;
        this.requestType = requestType;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
