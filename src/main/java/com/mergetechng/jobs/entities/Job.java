package com.mergetechng.jobs.entities;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Document()
@Entity
@Table()
public class Job {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @TextIndexed(weight = 5)
    @Basic(optional = false)
    @Column(name = "job_type", nullable = false)
    private String jobTitle;

    @TextIndexed(weight = 4)
    @Basic(optional = false)
    @Column(nullable = false, length = 10000)
    private String jobDescription;

    @Basic(optional = false)
    @Column(nullable = false)
    private String emailAddress;

    @Basic(optional = false)
    @Column(nullable = false)
    private Float offeredSalary;

    @TextIndexed(weight = 3)
    @Basic(optional = false)
    @Column(nullable = false)
    private String specialims;

    @Basic(optional = false)
    @Column(nullable = false)
    private String experience;

    @TextIndexed(weight = 2)
    @Basic(optional = false)
    @Column(nullable = false)
    private String industry;

    @Basic(optional = false)
    @Column(nullable = false)
    private String applicationDeadline; // string in datetime

    @Basic(optional = false)
    @Column(nullable = false)
    private String completeAddress;

    private String latitude;
    private String longitude;

    @Basic(optional = false)
    @Column(nullable = false)
    private String packagePaymentDescription;

    @Basic(optional = false)
    @Column(nullable = false)
    private String country;

    @Basic(optional = false)
    @Column(nullable = false)
    private String city;

    @Basic(optional = false)
    @Column(nullable = false)
    private String qualification;

    @Basic(optional = false)
    @Column(nullable = false)
    private String gender;

    @Basic(optional = false)
    @Column(nullable = false)
    private String career_type;

    @Basic(optional = false)
    @Column(nullable = false , name = "job_category")
    private String jobCategory;

    @Basic(optional = false)
    @Column(nullable = false)
    private String jobType;

    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

    @Column(name = "modified_by", length = 250)
    private String modifiedBy;

    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;

    @Basic(optional = false)
    @Column(name = "post_code", nullable = false, length = 250)
    private String jobPostCode;

    @TextScore
    private Float  textScore;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "", fetch = FetchType.LAZY)
    private List<JobApplicant> jobApplicants ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<JobApplicant> getJobApplicants() {
        return jobApplicants;
    }

    public void setJobApplicants(List<JobApplicant> jobApplicants) {
        this.jobApplicants = jobApplicants;
    }

    public String getJobId() {
        return id;
    }

    public Job(String id) {
        this.id = id;
    }

    public Job(String id, String jobTitle, String jobDescription, String emailAddress, Float offeredSalary, String specialims, String experience, String industry, String applicationDeadline, String completeAddress, String latitude, String longitude, String packagePaymentDescription, String country, String city, String qualification, String gender, String career_type, String jobType) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.emailAddress = emailAddress;
        this.offeredSalary = offeredSalary;
        this.specialims = specialims;
        this.experience = experience;
        this.industry = industry;
        this.applicationDeadline = applicationDeadline;
        this.completeAddress = completeAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.packagePaymentDescription = packagePaymentDescription;
        this.country = country;
        this.city = city;
        this.qualification = qualification;
        this.gender = gender;
        this.career_type = career_type;
        this.jobType = jobType;
    }

    public Job() {
    }

    public void setJobId(String id) {
        this.id = id;
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

    public String getCareer_type() {
        return career_type;
    }

    public void setCareer_type(String career_type) {
        this.career_type = career_type;
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

    public Float  getTextScore() {
        return textScore;
    }

    public void setTextScore(Float  textScore) {
        this.textScore = textScore;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJobPostCode() {
        return jobPostCode;
    }

    public void setJobPostCode(String jobPostCode) {
        this.jobPostCode = jobPostCode;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Group1)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.Job[ id=" + id + " ]";
    }
}
