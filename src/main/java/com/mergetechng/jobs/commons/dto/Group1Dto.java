package com.mergetechng.jobs.commons.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class Group1Dto {
    private String groupId;
    @NotNull
    private String groupName;
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    private String modifiedBy;

    public Group1Dto(String groupId, @NotNull String groupName, String createdBy, Date dateCreated, Date dateModified, String modifiedBy, List<RoleDto> roleId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.modifiedBy = modifiedBy;
    }

    public Group1Dto() {
    }

    ;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
}
