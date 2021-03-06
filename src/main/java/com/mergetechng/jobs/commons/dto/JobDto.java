package com.mergetechng.jobs.commons.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class JobDto {
    private String id;
    @NotNull
    @NotEmpty
    private String jobTitle;
    @NotNull
    @NotBlank
    private String jobDescription;
    @NotNull
    @NotBlank
    private String emailAddress;
    @NotNull
    @NotBlank
    private Float offeredSalary;
    @NotNull
    @NotBlank
    private String specialims;
    @NotNull
    @NotBlank
    private String experience;
    @NotNull
    @NotBlank
    private String industry;
    @NotNull
    @NotBlank
    private String applicationDeadline; // string in datetime
    @NotNull
    @NotBlank
    private String completeAddress;

    private String latitude;
    private String longitude;
    @NotNull
    @NotBlank
    private String packagePaymentDescription;
    @NotNull
    @NotBlank
    private String country;
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String qualification;
    @NotNull
    @NotBlank
    private String gender;
    @NotNull
    @NotBlank
    private String careerType;
    @NotNull
    @NotBlank
    private String jobType;
    private Date dateCreated;
    private Date dateModified;
    private String modifiedBy;
    private String createdBy;
    private Float  textScore;
    private String jobStatus;


    public JobDto(){}

    public String getId() {
        return id;
    }

    public void setId(String jobId) {
        this.id = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Float getOfferedSalary() {
        return offeredSalary;
    }

    public void setOfferedSalary(Float offeredSalary) {
        this.offeredSalary = offeredSalary;
    }

    public String getSpecialims() {
        return specialims;
    }

    public void setSpecialims(String specialims) {
        this.specialims = specialims;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(String applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPackagePaymentDescription() {
        return packagePaymentDescription;
    }

    public void setPackagePaymentDescription(String packagePaymentDescription) {
        this.packagePaymentDescription = packagePaymentDescription;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCareerType() {
        return careerType;
    }

    public void setCareerType(String careerType) {
        this.careerType = careerType;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Float getTextScore() {
        return textScore;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public void setTextScore(Float textScore) {
        this.textScore = textScore;
    }
}
