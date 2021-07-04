package com.mergetechng.jobs.A.job.application.project.to.create.view.and.Send.Job.Seekers.Information.to.the.employer.common.dto;

import lombok.NonNull;

public class RestErrorMessage {
    private String message;
    private Integer statusCode;
    private String description;

    public RestErrorMessage(String message, Integer statusCode, String description) {
        this.message = message;
        this.statusCode = statusCode;
        this.description = description;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
