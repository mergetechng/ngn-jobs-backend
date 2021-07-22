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
    @NamedQuery(name = "Reference.findAll", query = "SELECT r FROM Reference r"),
    @NamedQuery(name = "Reference.findByReferenceId", query = "SELECT r FROM Reference r WHERE r.id = :id"),
    @NamedQuery(name = "Reference.findByDateCreated", query = "SELECT r FROM Reference r WHERE r.dateCreated = :dateCreated"),
    @NamedQuery(name = "Reference.findByCreatedBy", query = "SELECT r FROM Reference r WHERE r.createdBy = :createdBy"),
    @NamedQuery(name = "Reference.findByModifiedBy", query = "SELECT r FROM Reference r WHERE r.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Reference.findByDateModified", query = "SELECT r FROM Reference r WHERE r.dateModified = :dateModified")})
public class Reference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @Column(name = "modified_by", length = 250)
    private String modifiedBy;
    @Column(name = "date_modified", length = 36)
    private String dateModified;
    @JoinColumn(name = "refree_id", referencedColumnName = "refree_id", nullable = false)
    @ManyToOne(optional = false)
    private Refree refreeId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "user_cv_id", referencedColumnName = "user_cv_id", nullable = false)
    @ManyToOne(optional = false)
    private UserCv userCvId;

    public Reference() {
    }

    public Reference(String id) {
        this.id = id;
    }

    public Reference(String id, Date dateCreated, String createdBy) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getReferenceId() {
        return id;
    }

    public void setReferenceId(String id) {
        this.id = id;
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

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public Refree getRefreeId() {
        return refreeId;
    }

    public void setRefreeId(Refree refreeId) {
        this.refreeId = refreeId;
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
        if (!(object instanceof Reference)) {
            return false;
        }
        Reference other = (Reference) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.Reference[ id=" + id + " ]";
    }
    
}
