/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author keemsisi
 */
@Embeddable
@Document
public class GroupHasPrivilegePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "group_has_privilege_id")
    private String groupHasPrivilegeId;
    @Basic(optional = false)
    @Column(name = "privilege_id")
    private String privilegeId;

    public GroupHasPrivilegePK() {
    }

    public GroupHasPrivilegePK(String GroupHasPrivilegeId, String privilegeId) {
        this.groupHasPrivilegeId = GroupHasPrivilegeId;
        this.privilegeId = privilegeId;
    }

    public String getGroupHasPrivilegeId() {
        return groupHasPrivilegeId;
    }

    public void setGroupHasPrivilegeId(String GroupHasPrivilegeId) {
        this.groupHasPrivilegeId = GroupHasPrivilegeId;
    }

    public String getprivilegeId() {
        return privilegeId;
    }

    public void setprivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupHasPrivilegeId != null ? groupHasPrivilegeId.hashCode() : 0);
        hash += (privilegeId != null ? privilegeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupHasPrivilegePK)) {
            return false;
        }
        GroupHasPrivilegePK other = (GroupHasPrivilegePK) object;
        if ((this.groupHasPrivilegeId == null && other.groupHasPrivilegeId != null) || (this.groupHasPrivilegeId != null && !this.groupHasPrivilegeId.equals(other.groupHasPrivilegeId))) {
            return false;
        }
        if ((this.privilegeId == null && other.privilegeId != null) || (this.privilegeId != null && !this.privilegeId.equals(other.privilegeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.GroupHasPrivilegePK[ GroupHasPrivilegeId=" + groupHasPrivilegeId + ", privilegeId=" + privilegeId + " ]";
    }
    
}
