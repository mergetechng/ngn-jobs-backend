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
@Table(name = "job_applicant", catalog = "ngn_jobs", schema = "POSTGRES")
@Document
@NamedQueries({
    @NamedQuery(name = "JobApplicantDto.findAll", query = "SELECT j FROM JobApplicantDto j"),
    @NamedQuery(name = "JobApplicantDto.findByJobApplicantId", query = "SELECT j FROM JobApplicantDto j WHERE j.id = :id"),
    @NamedQuery(name = "JobApplicantDto.findByJobCapaignId", query = "SELECT j FROM JobApplicantDto j WHERE j.jobCapaignId = :jobCapaignId"),
    @NamedQuery(name = "JobApplicantDto.findByDateCreated", query = "SELECT j FROM JobApplicantDto j WHERE j.dateCreated = :dateCreated"),
    @NamedQuery(name = "JobApplicantDto.findByDateModified", query = "SELECT j FROM JobApplicantDto j WHERE j.dateModified = :dateModified"),
    @NamedQuery(name = "JobApplicantDto.findByCreatedBy", query = "SELECT j FROM JobApplicantDto j WHERE j.createdBy = :createdBy"),
    @NamedQuery(name = "JobApplicantDto.findByModifiedBy", query = "SELECT j FROM JobApplicantDto j WHERE j.modifiedBy = :modifiedBy")})
public class JobApplicant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @Column(name = "job_id", nullable = false, length = 36)
    private String jobId;
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

    public JobApplicant(String id) {
        this.id = id;
    }

    public JobApplicant(String id, String jobId, Date dateCreated, String createdBy, Date modifiedBy) {
        this.id = id;
        this.jobId = jobId;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public String getJobApplicantId() {
        return id;
    }

    public void setJobApplicantId(String id) {
        this.id = id;
    }

    public String getJobCapaignId() {
        return jobId;
    }

    public void setJobCapaignId(String jobCapaignId) {
        this.jobId = jobCapaignId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobApplicant)) {
            return false;
        }
        JobApplicant other = (JobApplicant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.JobApplicantDto[ id=" + id + " ]";
    }
    
}
