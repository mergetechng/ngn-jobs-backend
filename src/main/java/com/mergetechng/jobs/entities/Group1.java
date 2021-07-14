/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author keemsisi
 */
@Entity
@Table(name = "group", catalog = "faston", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Group1.findAll", query = "SELECT g FROM Group1 g"),
    @NamedQuery(name = "Group1.findByGroupId", query = "SELECT g FROM Group1 g WHERE g.groupId = :groupId"),
    @NamedQuery(name = "Group1.findByGroupName", query = "SELECT g FROM Group1 g WHERE g.groupName = :groupName"),
    @NamedQuery(name = "Group1.findByCreatedBy", query = "SELECT g FROM Group1 g WHERE g.createdBy = :createdBy"),
    @NamedQuery(name = "Group1.findByDateCreated", query = "SELECT g FROM Group1 g WHERE g.dateCreated = :dateCreated"),
    @NamedQuery(name = "Group1.findByDateModified", query = "SELECT g FROM Group1 g WHERE g.dateModified = :dateModified"),
    @NamedQuery(name = "Group1.findByModifiedBy", query = "SELECT g FROM Group1 g WHERE g.modifiedBy = :modifiedBy")})
public class Group1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "group_id")
    private String groupId;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "created_by")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Column(name = "modified_by")
    private String modifiedBy;
    @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL , mappedBy = "groupId")
    private List<Role> roleId;


    public Group1() {
    }

    public Group1(String groupId) {
        this.groupId = groupId;
    }

    public Group1(String groupId, Date dateCreated, Date dateModified) {
        this.groupId = groupId;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

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


    public List<Role> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<Role> roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Group1)) {
            return false;
        }
        Group1 other = (Group1) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Group1[ groupId=" + groupId + " ]";
    }
    
}
