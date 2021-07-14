/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author keemsisi
 */
@Entity
@Table(name = "api_log_request", catalog = "faston", schema = "public")
@NamedQueries({
    @NamedQuery(name = "ApiLogRequest.findAll", query = "SELECT a FROM ApiLogRequest a"),
    @NamedQuery(name = "ApiLogRequest.findByApiLogRequestId", query = "SELECT a FROM ApiLogRequest a WHERE a.apiLogRequestId = :apiLogRequestId"),
    @NamedQuery(name = "ApiLogRequest.findByLogRequestBody", query = "SELECT a FROM ApiLogRequest a WHERE a.logRequestBody = :logRequestBody"),
    @NamedQuery(name = "ApiLogRequest.findByDateCreated", query = "SELECT a FROM ApiLogRequest a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "ApiLogRequest.findByCreatedBy", query = "SELECT a FROM ApiLogRequest a WHERE a.createdBy = :createdBy")})
public class ApiLogRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "api_log_request_id")
    private String apiLogRequestId;
    @Column(name = "log_request_body")
    private String logRequestBody;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "created_by")
    private String createdBy;

    public ApiLogRequest() {
    }

    public ApiLogRequest(String apiLogRequestId) {
        this.apiLogRequestId = apiLogRequestId;
    }

    public ApiLogRequest(String apiLogRequestId, Date dateCreated, String createdBy) {
        this.apiLogRequestId = apiLogRequestId;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getApiLogRequestId() {
        return apiLogRequestId;
    }

    public void setApiLogRequestId(String apiLogRequestId) {
        this.apiLogRequestId = apiLogRequestId;
    }

    public String getLogRequestBody() {
        return logRequestBody;
    }

    public void setLogRequestBody(String logRequestBody) {
        this.logRequestBody = logRequestBody;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apiLogRequestId != null ? apiLogRequestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApiLogRequest)) {
            return false;
        }
        ApiLogRequest other = (ApiLogRequest) object;
        if ((this.apiLogRequestId == null && other.apiLogRequestId != null) || (this.apiLogRequestId != null && !this.apiLogRequestId.equals(other.apiLogRequestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.ApiLogRequest[ apiLogRequestId=" + apiLogRequestId + " ]";
    }
    
}
