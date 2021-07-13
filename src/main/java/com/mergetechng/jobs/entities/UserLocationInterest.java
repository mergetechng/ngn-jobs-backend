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
@Table(name = "user_location_interest", catalog = "ngn_jobs", schema = "POSTGRES")
@NamedQueries({
    @NamedQuery(name = "UserLocationInterestDto.findAll", query = "SELECT u FROM UserLocationInterestDto u"),
    @NamedQuery(name = "UserLocationInterestDto.findByUserLocationInterestId", query = "SELECT u FROM UserLocationInterestDto u WHERE u.userLocationInterestId = :userLocationInterestId"),
    @NamedQuery(name = "UserLocationInterestDto.findByDateCreated", query = "SELECT u FROM UserLocationInterestDto u WHERE u.dateCreated = :dateCreated"),
    @NamedQuery(name = "UserLocationInterestDto.findByDateModified", query = "SELECT u FROM UserLocationInterestDto u WHERE u.dateModified = :dateModified"),
    @NamedQuery(name = "UserLocationInterestDto.findByModifiedBy", query = "SELECT u FROM UserLocationInterestDto u WHERE u.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "UserLocationInterestDto.findByCreatedBy", query = "SELECT u FROM UserLocationInterestDto u WHERE u.createdBy = :createdBy")})
public class UserLocationInterest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_location_interest_id", nullable = false, length = 36)
    private String userLocationInterestId;
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
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    @ManyToOne(optional = false)
    private Location locationId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public UserLocationInterest() {
    }

    public UserLocationInterest(String userLocationInterestId) {
        this.userLocationInterestId = userLocationInterestId;
    }

    public UserLocationInterest(String userLocationInterestId, Date dateCreated, String createdBy) {
        this.userLocationInterestId = userLocationInterestId;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getUserLocationInterestId() {
        return userLocationInterestId;
    }

    public void setUserLocationInterestId(String userLocationInterestId) {
        this.userLocationInterestId = userLocationInterestId;
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

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
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
        hash += (userLocationInterestId != null ? userLocationInterestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserLocationInterest)) {
            return false;
        }
        UserLocationInterest other = (UserLocationInterest) object;
        if ((this.userLocationInterestId == null && other.userLocationInterestId != null) || (this.userLocationInterestId != null && !this.userLocationInterestId.equals(other.userLocationInterestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.UserLocationInterestDto[ userLocationInterestId=" + userLocationInterestId + " ]";
    }
    
}
