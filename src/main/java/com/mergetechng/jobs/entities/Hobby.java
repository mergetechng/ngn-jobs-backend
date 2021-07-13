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
@Table(catalog = "ngn_jobs", schema = "POSTGRES")
@NamedQueries({
    @NamedQuery(name = "HobbyDto.findAll", query = "SELECT h FROM HobbyDto h"),
    @NamedQuery(name = "HobbyDto.findByHobbyId", query = "SELECT h FROM HobbyDto h WHERE h.hobbyId = :hobbyId"),
    @NamedQuery(name = "HobbyDto.findByHobbyName", query = "SELECT h FROM HobbyDto h WHERE h.hobbyName = :hobbyName"),
    @NamedQuery(name = "HobbyDto.findByDescription", query = "SELECT h FROM HobbyDto h WHERE h.description = :description"),
    @NamedQuery(name = "HobbyDto.findByDateCreated", query = "SELECT h FROM HobbyDto h WHERE h.dateCreated = :dateCreated"),
    @NamedQuery(name = "HobbyDto.findByDateModified", query = "SELECT h FROM HobbyDto h WHERE h.dateModified = :dateModified"),
    @NamedQuery(name = "HobbyDto.findByModifiedBy", query = "SELECT h FROM HobbyDto h WHERE h.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "HobbyDto.findByCreatedBy", query = "SELECT h FROM HobbyDto h WHERE h.createdBy = :createdBy")})
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hobby_id", nullable = false, length = 36)
    private String hobbyId;
    @Basic(optional = false)
    @Column(name = "hobby_name", nullable = false, length = 250)
    private String hobbyName;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String description;
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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "user_cv_id", referencedColumnName = "user_cv_id", nullable = false)
    @ManyToOne(optional = false)
    private UserCv userCvId;

    public Hobby() {
    }

    public Hobby(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    public Hobby(String hobbyId, String hobbyName, String description, Date dateCreated, String createdBy) {
        this.hobbyId = hobbyId;
        this.hobbyName = hobbyName;
        this.description = description;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (hobbyId != null ? hobbyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hobby)) {
            return false;
        }
        Hobby other = (Hobby) object;
        if ((this.hobbyId == null && other.hobbyId != null) || (this.hobbyId != null && !this.hobbyId.equals(other.hobbyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.HobbyDto[ hobbyId=" + hobbyId + " ]";
    }
    
}
