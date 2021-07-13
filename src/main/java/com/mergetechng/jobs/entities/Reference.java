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
 * @author @keemsisi
 */
@Entity
@Table(catalog = "ngn_jobs", schema = "POSTGRES")
@NamedQueries({
    @NamedQuery(name = "Reference.findAll", query = "SELECT r FROM Reference r"),
    @NamedQuery(name = "Reference.findByReferenceId", query = "SELECT r FROM Reference r WHERE r.referenceId = :referenceId"),
    @NamedQuery(name = "Reference.findByDateCreated", query = "SELECT r FROM Reference r WHERE r.dateCreated = :dateCreated"),
    @NamedQuery(name = "Reference.findByCreatedBy", query = "SELECT r FROM Reference r WHERE r.createdBy = :createdBy"),
    @NamedQuery(name = "Reference.findByModifiedBy", query = "SELECT r FROM Reference r WHERE r.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Reference.findByDateModified", query = "SELECT r FROM Reference r WHERE r.dateModified = :dateModified")})
public class Reference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reference_id", nullable = false, length = 36)
    private String referenceId;
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

    public Reference(String referenceId) {
        this.referenceId = referenceId;
    }

    public Reference(String referenceId, Date dateCreated, String createdBy) {
        this.referenceId = referenceId;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
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
        hash += (referenceId != null ? referenceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reference)) {
            return false;
        }
        Reference other = (Reference) object;
        if ((this.referenceId == null && other.referenceId != null) || (this.referenceId != null && !this.referenceId.equals(other.referenceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.Reference[ referenceId=" + referenceId + " ]";
    }
    
}
