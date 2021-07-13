/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findByLocationId", query = "SELECT l FROM Location l WHERE l.locationId = :locationId"),
    @NamedQuery(name = "Location.findByCity", query = "SELECT l FROM Location l WHERE l.city = :city"),
    @NamedQuery(name = "Location.findByState", query = "SELECT l FROM Location l WHERE l.state = :state"),
    @NamedQuery(name = "Location.findByCountry", query = "SELECT l FROM Location l WHERE l.country = :country"),
    @NamedQuery(name = "Location.findByDateCreated", query = "SELECT l FROM Location l WHERE l.dateCreated = :dateCreated"),
    @NamedQuery(name = "Location.findByDateModified", query = "SELECT l FROM Location l WHERE l.dateModified = :dateModified"),
    @NamedQuery(name = "Location.findByModifiedBy", query = "SELECT l FROM Location l WHERE l.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Location.findByCreatedBy", query = "SELECT l FROM Location l WHERE l.createdBy = :createdBy")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "location_id", nullable = false, length = 36)
    private String locationId;
    @Basic(optional = false)
    @Column(nullable = false, length = 36)
    private String city;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date state;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String country;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private Collection<JobAlertSubcription> jobAlertSubcriptionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private Collection<UserLocationInterest> userLocationInterestCollection;

    public Location() {
    }

    public Location(String locationId) {
        this.locationId = locationId;
    }

    public Location(String locationId, String city, Date state, String country, Date dateCreated, String createdBy) {
        this.locationId = locationId;
        this.city = city;
        this.state = state;
        this.country = country;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getState() {
        return state;
    }

    public void setState(Date state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Collection<JobAlertSubcription> getJobAlertSubcriptionCollection() {
        return jobAlertSubcriptionCollection;
    }

    public void setJobAlertSubcriptionCollection(Collection<JobAlertSubcription> jobAlertSubcriptionCollection) {
        this.jobAlertSubcriptionCollection = jobAlertSubcriptionCollection;
    }

    public Collection<UserLocationInterest> getUserLocationInterestCollection() {
        return userLocationInterestCollection;
    }

    public void setUserLocationInterestCollection(Collection<UserLocationInterest> userLocationInterestCollection) {
        this.userLocationInterestCollection = userLocationInterestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationId != null ? locationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.Location[ locationId=" + locationId + " ]";
    }
    
}
