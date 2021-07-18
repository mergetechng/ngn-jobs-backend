package com.mergetechng.jobs.commons.dto;

import javax.persistence.*;
import java.util.Date;

public class ApiLogRequestDto {
    private String apiLogRequestId;
    private String logRequestBody;
    private Date dateCreated;
    private String createdBy;

    public ApiLogRequestDto(String apiLogRequestId, String logRequestBody, Date dateCreated, String createdBy) {
        this.apiLogRequestId = apiLogRequestId;
        this.logRequestBody = logRequestBody;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public  ApiLogRequestDto(){}

    public String getApiLogRequestId() {
        return apiLogRequestId;
    }

    public void setApiLogRequestId(String apiLogRequestId) {
        this.apiLogRequestId = apiLogRequestId;
    }

    public String getLogRequestBody() {
        return logRequestBody;
    }

    public void setLogRequestBody(String logRequestBody) {
        this.logRequestBody = logRequestBody;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
