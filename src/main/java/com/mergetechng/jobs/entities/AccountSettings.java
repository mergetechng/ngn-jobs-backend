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
@Table(name = "account_settings", catalog = "ngn_jobs", schema = "POSTGRES")
@Document
@NamedQueries({
    @NamedQuery(name = "AccountSettings.findAll", query = "SELECT a FROM AccountSettings a"),
    @NamedQuery(name = "AccountSettings.findById", query = "SELECT a FROM AccountSettings a WHERE a.id = :id"),
    @NamedQuery(name = "AccountSettings.findByLastLoginInfo", query = "SELECT a FROM AccountSettings a WHERE a.lastLoginInfo = :lastLoginInfo"),
    @NamedQuery(name = "AccountSettings.findByReceiveJobAlert", query = "SELECT a FROM AccountSettings a WHERE a.receiveJobAlert = :receiveJobAlert"),
    @NamedQuery(name = "AccountSettings.findByDateCreated", query = "SELECT a FROM AccountSettings a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "AccountSettings.findByCreatedBy", query = "SELECT a FROM AccountSettings a WHERE a.createdBy = :createdBy"),
    @NamedQuery(name = "AccountSettings.findByDateModified", query = "SELECT a FROM AccountSettings a WHERE a.dateModified = :dateModified"),
    @NamedQuery(name = "AccountSettings.findByModifiedBy", query = "SELECT a FROM AccountSettings a WHERE a.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "AccountSettings.findByJobAlertSubscriptionId", query = "SELECT a FROM AccountSettings a WHERE a.jobAlertSubscriptionId = :jobAlertSubscriptionId")})
public class AccountSettings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "_id", nullable = false, length = 36)
    private String id;
    @Column(name = "last_login_info", length = 2147483647)
    private String lastLoginInfo;
    @Column(name = "receive_job_alert")
    private Boolean receiveJobAlert;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Column(name = "modified_by", length = 250)
    private String modifiedBy;
    @Basic(optional = false)
    @Column(name = "job_alert_subscription_id", nullable = false, length = 36)
    private String jobAlertSubscriptionId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public AccountSettings() {
    }

    public AccountSettings(String id) {
        this.id = id;
    }

    public AccountSettings(String id, Date dateCreated, String createdBy, String jobAlertSubscriptionId) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.jobAlertSubscriptionId = jobAlertSubscriptionId;
    }

    public String getAccountSettingId() {
        return id;
    }

    public void setAccountSettingId(String id) {
        this.id = id;
    }

    public String getLastLoginInfo() {
        return lastLoginInfo;
    }

    public void setLastLoginInfo(String lastLoginInfo) {
        this.lastLoginInfo = lastLoginInfo;
    }

    public Boolean getReceiveJobAlert() {
        return receiveJobAlert;
    }

    public void setReceiveJobAlert(Boolean receiveJobAlert) {
        this.receiveJobAlert = receiveJobAlert;
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

    public String getJobAlertSubscriptionId() {
        return jobAlertSubscriptionId;
    }

    public void setJobAlertSubscriptionId(String jobAlertSubscriptionId) {
        this.jobAlertSubscriptionId = jobAlertSubscriptionId;
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
        if (!(object instanceof AccountSettings)) {
            return false;
        }
        AccountSettings other = (AccountSettings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.AccountSettings[ id=" + id + " ]";
    }
    
}
