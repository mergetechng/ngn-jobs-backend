package com.mergetechng.jobs.entities;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author keemsisi
 */
@Entity
@Table(name = "token_to_email_map", catalog = "faston", schema = "public")
@Document
public class TokenToEmailMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String emailAddress;

    @Basic(optional = false)
    @Column(name = "token")
    private String token;

    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Basic(optional = false)
    @Column(name = "date_sent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSent;

    @Basic(optional = false)
    @Column(name = "date_sent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Basic(optional = false)
    @Column(name = "token_type")
    private String tokenType;

    @Basic(optional = false)
    @Column(name = "expired")
    private boolean expired;


    public TokenToEmailMap() {
    }

    public TokenToEmailMap(String id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTokenEmailMapId() {
        return id;
    }

    public void setTokenEmailMapId(String id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
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
        if (!(object instanceof TokenToEmailMap)) {
            return false;
        }
        TokenToEmailMap other = (TokenToEmailMap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.faston.backend.api.entity.TokenEmailMap[ id=" + id + " ]";
    }



}
