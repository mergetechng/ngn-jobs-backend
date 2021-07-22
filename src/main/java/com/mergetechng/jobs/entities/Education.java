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
@Table(catalog = "ngn_jobs", schema = "POSTGRES")
@Document
@NamedQueries({
    @NamedQuery(name = "EducationDto.findAll", query = "SELECT e FROM EducationDto e"),
    @NamedQuery(name = "EducationDto.findById", query = "SELECT e FROM EducationDto e WHERE e.id = :id"),
    @NamedQuery(name = "EducationDto.findByDateEarned", query = "SELECT e FROM EducationDto e WHERE e.dateEarned = :dateEarned"),
    @NamedQuery(name = "EducationDto.findByInstitution", query = "SELECT e FROM EducationDto e WHERE e.institution = :institution"),
    @NamedQuery(name = "EducationDto.findByState", query = "SELECT e FROM EducationDto e WHERE e.state = :state"),
    @NamedQuery(name = "EducationDto.findByCountry", query = "SELECT e FROM EducationDto e WHERE e.country = :country"),
    @NamedQuery(name = "EducationDto.findByCourseStudied", query = "SELECT e FROM EducationDto e WHERE e.courseStudied = :courseStudied"),
    @NamedQuery(name = "EducationDto.findByDescription", query = "SELECT e FROM EducationDto e WHERE e.description = :description"),
    @NamedQuery(name = "EducationDto.findByDateCreated", query = "SELECT e FROM EducationDto e WHERE e.dateCreated = :dateCreated"),
    @NamedQuery(name = "EducationDto.findByCreatedBy", query = "SELECT e FROM EducationDto e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "EducationDto.findByModifiedBy", query = "SELECT e FROM EducationDto e WHERE e.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "EducationDto.findByDateModified", query = "SELECT e FROM EducationDto e WHERE e.dateModified = :dateModified")})
public class Education implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @Column(name = "date_earned", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEarned;
    @Basic(optional = false)
    @Column(nullable = false, length = 500)
    private String institution;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String state;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String country;
    @Basic(optional = false)
    @Column(name = "course_studied", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date courseStudied;
    @Temporal(TemporalType.TIMESTAMP)
    private Date description;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @Column(name = "modified_by", length = 250)
    private String modifiedBy;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "user_cv_id", referencedColumnName = "user_cv_id", nullable = false)
    @ManyToOne(optional = false)
    private UserCv userCvId;

    public Education() {
    }

    public Education(String id) {
        this.id = id;
    }

    public Education(String id, Date dateEarned, String institution, String state, String country, Date courseStudied, Date dateCreated, String createdBy) {
        this.id = id;
        this.dateEarned = dateEarned;
        this.institution = institution;
        this.state = state;
        this.country = country;
        this.courseStudied = courseStudied;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getEducationId() {
        return id;
    }

    public void setEducationId(String id) {
        this.id = id;
    }

    public Date getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(Date dateEarned) {
        this.dateEarned = dateEarned;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getCourseStudied() {
        return courseStudied;
    }

    public void setCourseStudied(Date courseStudied) {
        this.courseStudied = courseStudied;
    }

    public Date getDescription() {
        return description;
    }

    public void setDescription(Date description) {
        this.description = description;
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
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
        if (!(object instanceof Education)) {
            return false;
        }
        Education other = (Education) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.EducationDto[ id=" + id + " ]";
    }
    
}
