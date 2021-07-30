package com.mergetechng.jobs.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Document
public class UserUploadDocument {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 36)
    private String username;
    @Basic(optional = false)
    @Column(name = "document_url", nullable = false)
    private String documentUrl;
    @Basic(optional = false)
    @Column(name = "access_level", nullable = false, length = 200)
    private String accessLevel;
    @Basic(optional = false)
    @Column(name = "document_access_options", nullable = false, length = 36)
    private List<String> documentAccessOptions;
    @Basic(optional = false)
    @Column(name = "document_state", nullable = false, length = 200)
    private String documentState;
    @Basic(optional = false)
    @Column(name = "file_metadata", nullable = false)
    private Map<String,String> fileMetadata;
    @Basic(optional = false)
    @Column(name = "document_type", nullable = false, length = 200)
    private String documentType;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Basic(optional = false)
    @Column(name = "modified_by", nullable = false, length = 250)
    private String modifiedBy;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "access_count", nullable = false)
    private Integer accessCount;
    @Basic(optional = false)
    @Column(name = "total_download_count", nullable = false, length = 200)
    private Integer totalDownloadCount;
    @Basic(optional = false)
    @Column(name = "last_download_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDownloadDate;
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_access_date", nullable = false)
    private Date lastAccessDate;
    @Basic(optional = false)
    @Column(name = "user_ids_who_accessed", nullable = false, length = 36)
    private List<String> userIdsWhoAccessed;
    @Basic(optional = false)
    @Column(name = "user_ids_who_download", nullable = false, length = 36)
    private List<String> userIdsWhoDownload;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public List<String> getDocumentAccessOptions() {
        return documentAccessOptions;
    }

    public void setDocumentAccessOptions(List<String> documentAccessOptions) {
        this.documentAccessOptions = documentAccessOptions;
    }

    public String getDocumentState() {
        return documentState;
    }

    public void setDocumentState(String documentState) {
        this.documentState = documentState;
    }

    public Map<String, String> getFileMetadata() {
        return fileMetadata;
    }

    public void setFileMetadata(Map<String, String> fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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

    public Integer getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Integer accessCount) {
        this.accessCount = accessCount;
    }

    public Integer getTotalDownloadCount() {
        return totalDownloadCount;
    }

    public void setTotalDownloadCount(Integer totalDownloadCount) {
        this.totalDownloadCount = totalDownloadCount;
    }

    public Date getLastDownloadDate() {
        return lastDownloadDate;
    }

    public void setLastDownloadDate(Date lastDownloadDate) {
        this.lastDownloadDate = lastDownloadDate;
    }

    public Date getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public List<String> getUserIdsWhoAccessed() {
        return userIdsWhoAccessed;
    }

    public void setUserIdsWhoAccessed(List<String> userIdsWhoAccessed) {
        this.userIdsWhoAccessed = userIdsWhoAccessed;
    }

    public List<String> getUserIdsWhoDownload() {
        return userIdsWhoDownload;
    }

    public void setUserIdsWhoDownload(List<String> userIdsWhoDownload) {
        this.userIdsWhoDownload = userIdsWhoDownload;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserUploadDocument)) {
            return false;
        }
        UserUploadDocument other = (UserUploadDocument) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.UserUploadDocument[ id=" + id + " ]";
    }
}
