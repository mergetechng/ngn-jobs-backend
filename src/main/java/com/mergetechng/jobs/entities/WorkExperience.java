/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author @keemsisi
 */
@Entity
@Table(name = "work_experience", catalog = "ngn_jobs", schema = "POSTGRES")
@Document
@NamedQueries({
    @NamedQuery(name = "WorkExperienceDto.findAll", query = "SELECT w FROM WorkExperienceDto w"),
    @NamedQuery(name = "WorkExperienceDto.findByWorkExperienceId", query = "SELECT w FROM WorkExperienceDto w WHERE w.id = :id"),
    @NamedQuery(name = "WorkExperienceDto.findByIsCurrentJob", query = "SELECT w FROM WorkExperienceDto w WHERE w.isCurrentJob = :isCurrentJob"),
    @NamedQuery(name = "WorkExperienceDto.findByJobTitle", query = "SELECT w FROM WorkExperienceDto w WHERE w.jobTitle = :jobTitle"),
    @NamedQuery(name = "WorkExperienceDto.findByStartDate", query = "SELECT w FROM WorkExperienceDto w WHERE w.startDate = :startDate"),
    @NamedQuery(name = "WorkExperienceDto.findByEndDate", query = "SELECT w FROM WorkExperienceDto w WHERE w.endDate = :endDate"),
    @NamedQuery(name = "WorkExperienceDto.findByCompanyName", query = "SELECT w FROM WorkExperienceDto w WHERE w.companyName = :companyName"),
    @NamedQuery(name = "WorkExperienceDto.findByJobLocationCity", query = "SELECT w FROM WorkExperienceDto w WHERE w.jobLocationCity = :jobLocationCity"),
    @NamedQuery(name = "WorkExperienceDto.findByJobLocationState", query = "SELECT w FROM WorkExperienceDto w WHERE w.jobLocationState = :jobLocationState"),
    @NamedQuery(name = "WorkExperienceDto.findByJobLocationCountry", query = "SELECT w FROM WorkExperienceDto w WHERE w.jobLocationCountry = :jobLocationCountry"),
    @NamedQuery(name = "WorkExperienceDto.findByDateModified", query = "SELECT w FROM WorkExperienceDto w WHERE w.dateModified = :dateModified"),
    @NamedQuery(name = "WorkExperienceDto.findByModifiedBy", query = "SELECT w FROM WorkExperienceDto w WHERE w.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "WorkExperienceDto.findByCreatedBy", query = "SELECT w FROM WorkExperienceDto w WHERE w.createdBy = :createdBy"),
    @NamedQuery(name = "WorkExperienceDto.findByJobDescription", query = "SELECT w FROM WorkExperienceDto w WHERE w.jobDescription = :jobDescription"),
    @NamedQuery(name = "WorkExperienceDto.findByDateCreated", query = "SELECT w FROM WorkExperienceDto w WHERE w.dateCreated = :dateCreated")})
public class WorkExperience implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @Column(name = "is_current_job", nullable = false, length = 250)
    private String isCurrentJob;
    @Basic(optional = false)
    @Column(name = "job_title", nullable = false, length = 250)
    private String jobTitle;
    @Basic(optional = false)
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "company_name", nullable = false, length = 250)
    private String companyName;
    @Basic(optional = false)
    @Column(name = "job_location_city", nullable = false, length = 100)
    private String jobLocationCity;
    @Basic(optional = false)
    @Column(name = "job_location_state", nullable = false, length = 100)
    private String jobLocationState;
    @Basic(optional = false)
    @Column(name = "job_location_country", nullable = false, length = 250)
    private String jobLocationCountry;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Column(name = "modified_by", length = 250)
    private String modifiedBy;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "job_description", nullable = false, length = 2147483647)
    private String jobDescription;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "user_cv_id ", referencedColumnName = "user_cv_id", nullable = false)
    @ManyToOne(optional = false)
    private UserCv userCvId;

    public WorkExperience() {
    }

    public WorkExperience(String id) {
        this.id = id;
    }

    public WorkExperience(String id, String isCurrentJob, String jobTitle, Date startDate, String companyName, String jobLocationCity, String jobLocationState, String jobLocationCountry, String createdBy, String jobDescription, Date dateCreated) {
        this.id = id;
        this.isCurrentJob = isCurrentJob;
        this.jobTitle = jobTitle;
        this.startDate = startDate;
        this.companyName = companyName;
        this.jobLocationCity = jobLocationCity;
        this.jobLocationState = jobLocationState;
        this.jobLocationCountry = jobLocationCountry;
        this.createdBy = createdBy;
        this.jobDescription = jobDescription;
        this.dateCreated = dateCreated;
    }

    public String getWorkExperienceId() {
        return id;
    }

    public void setWorkExperienceId(String id) {
        this.id = id;
    }

    public String getIsCurrentJob() {
        return isCurrentJob;
    }

    public void setIsCurrentJob(String isCurrentJob) {
        this.isCurrentJob = isCurrentJob;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobLocationCity() {
        return jobLocationCity;
    }

    public void setJobLocationCity(String jobLocationCity) {
        this.jobLocationCity = jobLocationCity;
    }

    public String getJobLocationState() {
        return jobLocationState;
    }

    public void setJobLocationState(String jobLocationState) {
        this.jobLocationState = jobLocationState;
    }

    public String getJobLocationCountry() {
        return jobLocationCountry;
    }

    public void setJobLocationCountry(String jobLocationCountry) {
        this.jobLocationCountry = jobLocationCountry;
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

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public UserCv getUserCvId() {
        return userCvId;
    }

    public void setUserCvId(UserCv userCvId) {
        this.userCvId = userCvId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkExperience)) {
            return false;
        }
        WorkExperience other = (WorkExperience) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.WorkExperienceDto[ id=" + id + " ]";
    }
    
}
