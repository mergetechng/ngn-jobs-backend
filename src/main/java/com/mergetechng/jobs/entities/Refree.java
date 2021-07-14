/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "RefreeDto.findAll", query = "SELECT r FROM RefreeDto r"),
    @NamedQuery(name = "RefreeDto.findByRefreeId", query = "SELECT r FROM RefreeDto r WHERE r.refreeId = :refreeId"),
    @NamedQuery(name = "RefreeDto.findByRefreeFullName", query = "SELECT r FROM RefreeDto r WHERE r.refreeFullName = :refreeFullName"),
    @NamedQuery(name = "RefreeDto.findByRefreePhone", query = "SELECT r FROM RefreeDto r WHERE r.refreePhone = :refreePhone"),
    @NamedQuery(name = "RefreeDto.findByRefreeTitle", query = "SELECT r FROM RefreeDto r WHERE r.refreeTitle = :refreeTitle"),
    @NamedQuery(name = "RefreeDto.findByRefreeOccupation", query = "SELECT r FROM RefreeDto r WHERE r.refreeOccupation = :refreeOccupation"),
    @NamedQuery(name = "RefreeDto.findByRefreeEmail", query = "SELECT r FROM RefreeDto r WHERE r.refreeEmail = :refreeEmail"),
    @NamedQuery(name = "RefreeDto.findByDateCreated", query = "SELECT r FROM RefreeDto r WHERE r.dateCreated = :dateCreated"),
    @NamedQuery(name = "RefreeDto.findByCreatedBy", query = "SELECT r FROM RefreeDto r WHERE r.createdBy = :createdBy"),
    @NamedQuery(name = "RefreeDto.findByModifiedBy", query = "SELECT r FROM RefreeDto r WHERE r.modifiedBy = :modifiedBy")})
public class Refree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "refree_id", nullable = false, length = 36)
    private String refreeId;
    @Basic(optional = false)
    @Column(name = "refree_full_name", nullable = false, length = 250)
    private String refreeFullName;
    @Column(name = "refree_phone", length = 15)
    private String refreePhone;
    @Basic(optional = false)
    @Column(name = "refree_title", nullable = false, length = 15)
    private String refreeTitle;
    @Column(name = "refree_occupation", length = 250)
    private String refreeOccupation;
    @Column(name = "refree_email", length = 250)
    private String refreeEmail;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @Column(name = "modified_by", length = 250)
    private String modifiedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refreeId")
    private List<Reference> referenceList;
    @JoinColumn(name = "user_cv_id", referencedColumnName = "user_cv_id", nullable = false)
    @ManyToOne(optional = false)
    private UserCv userCvId;

    public Refree() {
    }

    public Refree(String refreeId) {
        this.refreeId = refreeId;
    }

    public Refree(String refreeId, String refreeFullName, String refreeTitle, Date dateCreated, String createdBy) {
        this.refreeId = refreeId;
        this.refreeFullName = refreeFullName;
        this.refreeTitle = refreeTitle;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getRefreeId() {
        return refreeId;
    }

    public void setRefreeId(String refreeId) {
        this.refreeId = refreeId;
    }

    public String getRefreeFullName() {
        return refreeFullName;
    }

    public void setRefreeFullName(String refreeFullName) {
        this.refreeFullName = refreeFullName;
    }

    public String getRefreePhone() {
        return refreePhone;
    }

    public void setRefreePhone(String refreePhone) {
        this.refreePhone = refreePhone;
    }

    public String getRefreeTitle() {
        return refreeTitle;
    }

    public void setRefreeTitle(String refreeTitle) {
        this.refreeTitle = refreeTitle;
    }

    public String getRefreeOccupation() {
        return refreeOccupation;
    }

    public void setRefreeOccupation(String refreeOccupation) {
        this.refreeOccupation = refreeOccupation;
    }

    public String getRefreeEmail() {
        return refreeEmail;
    }

    public void setRefreeEmail(String refreeEmail) {
        this.refreeEmail = refreeEmail;
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

    public List<Reference> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(List<Reference> referenceList) {
        this.referenceList = referenceList;
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
        hash += (refreeId != null ? refreeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Refree)) {
            return false;
        }
        Refree other = (Refree) object;
        if ((this.refreeId == null && other.refreeId != null) || (this.refreeId != null && !this.refreeId.equals(other.refreeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.RefreeDto[ refreeId=" + refreeId + " ]";
    }
    
}
