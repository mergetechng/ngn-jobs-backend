/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

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
 * @author Integral
 */
@Entity
@Table(name = "job_applicant", catalog = "ngn_jobs", schema = "POSTGRES")
@NamedQueries({
    @NamedQuery(name = "JobApplicantDto.findAll", query = "SELECT j FROM JobApplicantDto j"),
    @NamedQuery(name = "JobApplicantDto.findByJobApplicantId", query = "SELECT j FROM JobApplicantDto j WHERE j.jobApplicantId = :jobApplicantId"),
    @NamedQuery(name = "JobApplicantDto.findByJobCapaignId", query = "SELECT j FROM JobApplicantDto j WHERE j.jobCapaignId = :jobCapaignId"),
    @NamedQuery(name = "JobApplicantDto.findByDateCreated", query = "SELECT j FROM JobApplicantDto j WHERE j.dateCreated = :dateCreated"),
    @NamedQuery(name = "JobApplicantDto.findByDateModified", query = "SELECT j FROM JobApplicantDto j WHERE j.dateModified = :dateModified"),
    @NamedQuery(name = "JobApplicantDto.findByCreatedBy", query = "SELECT j FROM JobApplicantDto j WHERE j.createdBy = :createdBy"),
    @NamedQuery(name = "JobApplicantDto.findByModifiedBy", query = "SELECT j FROM JobApplicantDto j WHERE j.modifiedBy = :modifiedBy")})
public class JobApplicant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "job_applicant_id", nullable = false, length = 36)
    private String jobApplicantId;
    @Basic(optional = false)
    @Column(name = "job_capaign_id", nullable = false, length = 36)
    private String jobCapaignId;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "modified_by", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedBy;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public JobApplicant() {
    }

    public JobApplicant(String jobApplicantId) {
        this.jobApplicantId = jobApplicantId;
    }

    public JobApplicant(String jobApplicantId, String jobCapaignId, Date dateCreated, String createdBy, Date modifiedBy) {
        this.jobApplicantId = jobApplicantId;
        this.jobCapaignId = jobCapaignId;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public String getJobApplicantId() {
        return jobApplicantId;
    }

    public void setJobApplicantId(String jobApplicantId) {
        this.jobApplicantId = jobApplicantId;
    }

    public String getJobCapaignId() {
        return jobCapaignId;
    }

    public void setJobCapaignId(String jobCapaignId) {
        this.jobCapaignId = jobCapaignId;
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

    public Date getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobApplicantId != null ? jobApplicantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobApplicant)) {
            return false;
        }
        JobApplicant other = (JobApplicant) object;
        if ((this.jobApplicantId == null && other.jobApplicantId != null) || (this.jobApplicantId != null && !this.jobApplicantId.equals(other.jobApplicantId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.JobApplicantDto[ jobApplicantId=" + jobApplicantId + " ]";
    }
    
}
