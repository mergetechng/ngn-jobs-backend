/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

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
    @NamedQuery(name = "JobApplicantDto.findByjobCampaignId", query = "SELECT j FROM JobApplicantDto j WHERE j.jobCampaignId = :jobCampaignId"),
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
    private String modifiedBy;
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
//    @ManyToOne(optional = false)
    private String ownedByUsername;
    @Column(name = "resume_or_cv_document_file_name")
    private String resumeDocumentFileName;
    @Column(name = "cover_letter_document_url")
    private String coverLetterDocumentUrl;
//    @ManyToOne(optional = false , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//    private Job job;




    public JobApplicant() {
    }

    public JobApplicant(String id) {
        this.id = id;
    }

    public String getResumeDocumentFileName() {
        return resumeDocumentFileName;
    }

    public void setResumeDocumentFileName(String resumeDocumentFileName) {
        this.resumeDocumentFileName = resumeDocumentFileName;
    }

    public JobApplicant(String id, String jobId, Date dateCreated, String createdBy, String modifiedBy) {
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

    public String getOwnedByUsername() {
        return ownedByUsername;
    }

    public void setOwnedByUsername(String userId) {
        this.ownedByUsername = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getResumeDocumentUrl() {
        return resumeDocumentFileName;
    }

    public void setResumeDocumentUrl(String resumeDocumentFileName) {
        this.resumeDocumentFileName = resumeDocumentFileName;
    }

    public String getCoverLetterDocumentUrl() {
        return coverLetterDocumentUrl;
    }

    public void setCoverLetterDocumentUrl(String coverLetterDocumentUrl) {
        this.coverLetterDocumentUrl = coverLetterDocumentUrl;
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
