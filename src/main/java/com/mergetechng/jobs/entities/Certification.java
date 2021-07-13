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
    @NamedQuery(name = "CertificationDto.findAll", query = "SELECT c FROM CertificationDto c"),
    @NamedQuery(name = "CertificationDto.findByCertificationId", query = "SELECT c FROM CertificationDto c WHERE c.certificationId = :certificationId"),
    @NamedQuery(name = "CertificationDto.findByGrantingOrganization", query = "SELECT c FROM CertificationDto c WHERE c.grantingOrganization = :grantingOrganization"),
    @NamedQuery(name = "CertificationDto.findByStartDate", query = "SELECT c FROM CertificationDto c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "CertificationDto.findByEndDate", query = "SELECT c FROM CertificationDto c WHERE c.endDate = :endDate"),
    @NamedQuery(name = "CertificationDto.findByCity", query = "SELECT c FROM CertificationDto c WHERE c.city = :city"),
    @NamedQuery(name = "CertificationDto.findByState", query = "SELECT c FROM CertificationDto c WHERE c.state = :state"),
    @NamedQuery(name = "CertificationDto.findByCountry", query = "SELECT c FROM CertificationDto c WHERE c.country = :country"),
    @NamedQuery(name = "CertificationDto.findByDateCreated", query = "SELECT c FROM CertificationDto c WHERE c.dateCreated = :dateCreated"),
    @NamedQuery(name = "CertificationDto.findByDateModified", query = "SELECT c FROM CertificationDto c WHERE c.dateModified = :dateModified"),
    @NamedQuery(name = "CertificationDto.findByModifiedBy", query = "SELECT c FROM CertificationDto c WHERE c.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "CertificationDto.findByUserId", query = "SELECT c FROM CertificationDto c WHERE c.userId = :userId"),
    @NamedQuery(name = "CertificationDto.findByCreatedBy", query = "SELECT c FROM CertificationDto c WHERE c.createdBy = :createdBy")})
public class Certification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "certification_id", nullable = false, length = 36)
    private String certificationId;
    @Basic(optional = false)
    @Column(name = "granting_organization", nullable = false, length = 250)
    private String grantingOrganization;
    @Basic(optional = false)
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String city;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String state;
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
    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 350)
    private String createdBy;
    @JoinColumn(name = "user_cv_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userCvId;
    @JoinColumn(name = "user_cv_id", referencedColumnName = "user_cv_id", nullable = false)
    @ManyToOne(optional = false)
    private UserCv userCvId1;

    public Certification() {
    }

    public Certification(String certificationId) {
        this.certificationId = certificationId;
    }

    public Certification(String certificationId, String grantingOrganization, Date startDate, Date endDate, String city, String state, String country, Date dateCreated, String userId, String createdBy) {
        this.certificationId = certificationId;
        this.grantingOrganization = grantingOrganization;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.state = state;
        this.country = country;
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.createdBy = createdBy;
    }

    public String getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(String certificationId) {
        this.certificationId = certificationId;
    }

    public String getGrantingOrganization() {
        return grantingOrganization;
    }

    public void setGrantingOrganization(String grantingOrganization) {
        this.grantingOrganization = grantingOrganization;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public User getUserCvId() {
        return userCvId;
    }

    public void setUserCvId(User userCvId) {
        this.userCvId = userCvId;
    }

    public UserCv getUserCvId1() {
        return userCvId1;
    }

    public void setUserCvId1(UserCv userCvId1) {
        this.userCvId1 = userCvId1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (certificationId != null ? certificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certification)) {
            return false;
        }
        Certification other = (Certification) object;
        if ((this.certificationId == null && other.certificationId != null) || (this.certificationId != null && !this.certificationId.equals(other.certificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.CertificationDto[ certificationId=" + certificationId + " ]";
    }
    
}
