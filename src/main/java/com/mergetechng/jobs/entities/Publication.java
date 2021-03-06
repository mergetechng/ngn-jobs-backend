package com.mergetechng.jobs.entities;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    @NamedQuery(name = "PublicationDto.findAll", query = "SELECT p FROM PublicationDto p"),
    @NamedQuery(name = "PublicationDto.findByPublicationId", query = "SELECT p FROM PublicationDto p WHERE p.id = :id"),
    @NamedQuery(name = "PublicationDto.findByDatePublished", query = "SELECT p FROM PublicationDto p WHERE p.datePublished = :datePublished"),
    @NamedQuery(name = "PublicationDto.findByCountry", query = "SELECT p FROM PublicationDto p WHERE p.country = :country"),
    @NamedQuery(name = "PublicationDto.findByState", query = "SELECT p FROM PublicationDto p WHERE p.state = :state"),
    @NamedQuery(name = "PublicationDto.findByCity", query = "SELECT p FROM PublicationDto p WHERE p.city = :city"),
    @NamedQuery(name = "PublicationDto.findByPublicationTopic", query = "SELECT p FROM PublicationDto p WHERE p.publicationTopic = :publicationTopic"),
    @NamedQuery(name = "PublicationDto.findByPublicationDescription", query = "SELECT p FROM PublicationDto p WHERE p.publicationDescription = :publicationDescription"),
    @NamedQuery(name = "PublicationDto.findByDateCreated", query = "SELECT p FROM PublicationDto p WHERE p.dateCreated = :dateCreated"),
    @NamedQuery(name = "PublicationDto.findByDateModified", query = "SELECT p FROM PublicationDto p WHERE p.dateModified = :dateModified"),
    @NamedQuery(name = "PublicationDto.findByModifiedBy", query = "SELECT p FROM PublicationDto p WHERE p.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "PublicationDto.findByCreatedBy", query = "SELECT p FROM PublicationDto p WHERE p.createdBy = :createdBy")})
public class Publication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @Column(name = "date_published", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePublished;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String country;
    @Column(length = 250)
    private String state;
    @Column(length = 250)
    private String city;
    @Basic(optional = false)
    @Column(name = "publication_topic", nullable = false, length = 250)
    private String publicationTopic;
    @Basic(optional = false)
    @Column(name = "publication_description", nullable = false, length = 250)
    private String publicationDescription;
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

    public Publication() {
    }

    public Publication(String id) {
        this.id = id;
    }

    public Publication(String id, Date datePublished, String country, String publicationTopic, String publicationDescription, Date dateCreated, String createdBy) {
        this.id = id;
        this.datePublished = datePublished;
        this.country = country;
        this.publicationTopic = publicationTopic;
        this.publicationDescription = publicationDescription;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getPublicationId() {
        return id;
    }

    public void setPublicationId(String id) {
        this.id = id;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPublicationTopic() {
        return publicationTopic;
    }

    public void setPublicationTopic(String publicationTopic) {
        this.publicationTopic = publicationTopic;
    }

    public String getPublicationDescription() {
        return publicationDescription;
    }

    public void setPublicationDescription(String publicationDescription) {
        this.publicationDescription = publicationDescription;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publication)) {
            return false;
        }
        Publication other = (Publication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.PublicationDto[ id=" + id + " ]";
    }
    
}
