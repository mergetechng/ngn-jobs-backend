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
@Table(name = "job_alert_subcription", catalog = "ngn_jobs", schema = "POSTGRES")
@NamedQueries({
    @NamedQuery(name = "JobAlertSubcription.findAll", query = "SELECT j FROM JobAlertSubcription j"),
    @NamedQuery(name = "JobAlertSubcription.findByJobSubscriptionId", query = "SELECT j FROM JobAlertSubcription j WHERE j.jobSubscriptionId = :jobSubscriptionId"),
    @NamedQuery(name = "JobAlertSubcription.findBySubscriptionDate", query = "SELECT j FROM JobAlertSubcription j WHERE j.subscriptionDate = :subscriptionDate"),
    @NamedQuery(name = "JobAlertSubcription.findByDateCreated", query = "SELECT j FROM JobAlertSubcription j WHERE j.dateCreated = :dateCreated"),
    @NamedQuery(name = "JobAlertSubcription.findByDateModified", query = "SELECT j FROM JobAlertSubcription j WHERE j.dateModified = :dateModified"),
    @NamedQuery(name = "JobAlertSubcription.findByModifiedBy", query = "SELECT j FROM JobAlertSubcription j WHERE j.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "JobAlertSubcription.findByJobRole", query = "SELECT j FROM JobAlertSubcription j WHERE j.jobRole = :jobRole")})
public class JobAlertSubcription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "job_subscription_id", nullable = false, length = 36)
    private String jobSubscriptionId;
    @Column(name = "subscription_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptionDate;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Column(name = "modified_by", length = 250)
    private String modifiedBy;
    @Column(name = "job_role", length = 500)
    private String jobRole;
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    @ManyToOne(optional = false)
    private Location locationId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public JobAlertSubcription() {
    }

    public JobAlertSubcription(String jobSubscriptionId) {
        this.jobSubscriptionId = jobSubscriptionId;
    }

    public JobAlertSubcription(String jobSubscriptionId, Date dateCreated) {
        this.jobSubscriptionId = jobSubscriptionId;
        this.dateCreated = dateCreated;
    }

    public String getJobSubscriptionId() {
        return jobSubscriptionId;
    }

    public void setJobSubscriptionId(String jobSubscriptionId) {
        this.jobSubscriptionId = jobSubscriptionId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
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

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
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
        hash += (jobSubscriptionId != null ? jobSubscriptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobAlertSubcription)) {
            return false;
        }
        JobAlertSubcription other = (JobAlertSubcription) object;
        if ((this.jobSubscriptionId == null && other.jobSubscriptionId != null) || (this.jobSubscriptionId != null && !this.jobSubscriptionId.equals(other.jobSubscriptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.JobAlertSubcription[ jobSubscriptionId=" + jobSubscriptionId + " ]";
    }
    
}
