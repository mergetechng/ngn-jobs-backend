package com.mergetechng.jobs.commons.dto;

import java.util.Date;

public class JobApplicantDto {
    private String id;
    private Date dateCreated;
    private Date dateModified;
    private String createdBy;
    private String modifiedBy;
    private String applicantUsername;
    private String resumeDocumentFileName;
    private String coverLetterDocumentUrl;

    public JobApplicantDto() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getApplicantUsername() {
        return applicantUsername;
    }

    public void setApplicantUsername(String applicantUsername) {
        this.applicantUsername = applicantUsername;
    }

    public String getResumeDocumentFileName() {
        return resumeDocumentFileName;
    }

    public void setResumeDocumentFileName(String resumeDocumentFileName) {
        this.resumeDocumentFileName = resumeDocumentFileName;
    }

    public String getCoverLetterDocumentUrl() {
        return coverLetterDocumentUrl;
    }

    public void setCoverLetterDocumentUrl(String coverLetterDocumentUrl) {
        this.coverLetterDocumentUrl = coverLetterDocumentUrl;
    }
}
