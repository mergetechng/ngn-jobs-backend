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
@Table(name = "group_has_roles", catalog = "faston", schema = "public")
@NamedQueries({
    @NamedQuery(name = "GroupHasPrivilege.findAll", query = "SELECT g FROM GroupHasPrivilege g"),
    @NamedQuery(name = "GroupHasPrivilege.findByGroupHasPrivilegeId", query = "SELECT g FROM GroupHasPrivilege g WHERE g.GroupHasPrivilegePK.GroupHasPrivilegeId = :GroupHasPrivilegeId"),
    @NamedQuery(name = "GroupHasPrivilege.findByprivilegeId", query = "SELECT g FROM GroupHasPrivilege g WHERE g.GroupHasPrivilegePK.privilegeId = :privilegeId"),
    @NamedQuery(name = "GroupHasPrivilege.findByCreatedBy", query = "SELECT g FROM GroupHasPrivilege g WHERE g.createdBy = :createdBy"),
    @NamedQuery(name = "GroupHasPrivilege.findByDateCreated", query = "SELECT g FROM GroupHasPrivilege g WHERE g.dateCreated = :dateCreated"),
    @NamedQuery(name = "GroupHasPrivilege.findByGroupId", query = "SELECT g FROM GroupHasPrivilege g WHERE g.groupId = :groupId")})
public class GroupHasPrivilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupHasPrivilegePK groupHasPrivilegePK;
    @Basic(optional = false)
    @Column(name = "created_by")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "privilege_id", referencedColumnName = "privilege_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Privilege privilegeId;
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Group1 groupId;

    public GroupHasPrivilege() {
    }

    public GroupHasPrivilege(GroupHasPrivilegePK GroupHasPrivilegePK) {
        this.groupHasPrivilegePK = GroupHasPrivilegePK;
    }

    public GroupHasPrivilege(GroupHasPrivilegePK GroupHasPrivilegePK, String createdBy, Date dateCreated) {
        this.groupHasPrivilegePK = GroupHasPrivilegePK;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
    }

    public GroupHasPrivilege(String GroupHasPrivilegeId, String privilegeId) {
        this.groupHasPrivilegePK = new GroupHasPrivilegePK(GroupHasPrivilegeId, privilegeId);
    }

    public GroupHasPrivilegePK getGroupHasPrivilegePK() {
        return groupHasPrivilegePK;
    }

    public void setGroupHasPrivilegePK(GroupHasPrivilegePK GroupHasPrivilegePK) {
        this.groupHasPrivilegePK = GroupHasPrivilegePK;
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

    public Group1 getGroupId() {
        return groupId;
    }

    public void setGroupId(Group1 groupId) {
        this.groupId = groupId;
    }

    public Privilege getprivilege() {
        return privilegeId;
    }

    public void setprivilege(Privilege privilege) {
        this.privilegeId = privilege;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupHasPrivilegePK != null ? groupHasPrivilegePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupHasPrivilege)) {
            return false;
        }
        GroupHasPrivilege other = (GroupHasPrivilege) object;
        return (this.groupHasPrivilegePK != null || other.groupHasPrivilegePK == null) && (this.groupHasPrivilegePK == null || this.groupHasPrivilegePK.equals(other.groupHasPrivilegePK));
    }

    @Override
    public String toString() {
        return "GroupHasPrivilege[ GroupHasPrivilegePK=" + groupHasPrivilegePK + " ]";
    }
    
}
