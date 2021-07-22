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
    @NamedQuery(name = "OrganizationDto.findAll", query = "SELECT o FROM OrganizationDto o"),
    @NamedQuery(name = "OrganizationDto.findByOrganizationId", query = "SELECT o FROM OrganizationDto o WHERE o.id = :id"),
    @NamedQuery(name = "OrganizationDto.findByDateStart", query = "SELECT o FROM OrganizationDto o WHERE o.dateStart = :dateStart"),
    @NamedQuery(name = "OrganizationDto.findByDateEnd", query = "SELECT o FROM OrganizationDto o WHERE o.dateEnd = :dateEnd"),
    @NamedQuery(name = "OrganizationDto.findByOrganizationName", query = "SELECT o FROM OrganizationDto o WHERE o.organizationName = :organizationName"),
    @NamedQuery(name = "OrganizationDto.findByOrganizationType", query = "SELECT o FROM OrganizationDto o WHERE o.organizationType = :organizationType"),
    @NamedQuery(name = "OrganizationDto.findByOrganizationSize", query = "SELECT o FROM OrganizationDto o WHERE o.organizationSize = :organizationSize"),
    @NamedQuery(name = "OrganizationDto.findByOrganizationIndustry", query = "SELECT o FROM OrganizationDto o WHERE o.organizationIndustry = :organizationIndustry"),
    @NamedQuery(name = "OrganizationDto.findByRole", query = "SELECT o FROM OrganizationDto o WHERE o.role = :role"),
    @NamedQuery(name = "OrganizationDto.findByDateCreated", query = "SELECT o FROM OrganizationDto o WHERE o.dateCreated = :dateCreated"),
    @NamedQuery(name = "OrganizationDto.findByDateModified", query = "SELECT o FROM OrganizationDto o WHERE o.dateModified = :dateModified"),
    @NamedQuery(name = "OrganizationDto.findByCreatedBy", query = "SELECT o FROM OrganizationDto o WHERE o.createdBy = :createdBy"),
    @NamedQuery(name = "OrganizationDto.findByModifiedBy", query = "SELECT o FROM OrganizationDto o WHERE o.modifiedBy = :modifiedBy")})
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @Column(name = "date_start", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    @Basic(optional = false)
    @Column(name = "date_end", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    @Basic(optional = false)
    @Column(name = "organization_name", nullable = false, length = 250)
    private String organizationName;
    @Basic(optional = false)
    @Column(name = "organization_type", nullable = false, length = 250)
    private String organizationType;
    @Column(name = "organization_size")
    private Integer organizationSize;
    @Basic(optional = false)
    @Column(name = "organization_industry", nullable = false, length = 250)
    private String organizationIndustry;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String role;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "date_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @Column(name = "modified_by", length = 250)
    private String modifiedBy;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "user_cv_id", referencedColumnName = "user_cv_id", nullable = false)
    @ManyToOne(optional = false)
    private UserCv userCvId;

    public Organization() {
    }

    public Organization(String id) {
        this.id = id;
    }

    public Organization(String id, Date dateStart, Date dateEnd, String organizationName, String organizationType, String organizationIndustry, String role, Date dateCreated, Date dateModified, String createdBy) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.organizationName = organizationName;
        this.organizationType = organizationType;
        this.organizationIndustry = organizationIndustry;
        this.role = role;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.createdBy = createdBy;
    }

    public String getOrganizationId() {
        return id;
    }

    public void setOrganizationId(String id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public Integer getOrganizationSize() {
        return organizationSize;
    }

    public void setOrganizationSize(Integer organizationSize) {
        this.organizationSize = organizationSize;
    }

    public String getOrganizationIndustry() {
        return organizationIndustry;
    }

    public void setOrganizationIndustry(String organizationIndustry) {
        this.organizationIndustry = organizationIndustry;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization other = (Organization) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.OrganizationDto[ id=" + id + " ]";
    }
    
}
