/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author keemsisi
 */
@Entity
@Table(name = "role", catalog = "faston", schema = "public")
@Document
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id"),
    @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name"),
    @NamedQuery(name = "Role.findByDateCreated", query = "SELECT r FROM Role r WHERE r.dateCreated = :dateCreated"),
    @NamedQuery(name = "Role.findByDateModified", query = "SELECT r FROM Role r WHERE r.dateModified = :dateModified"),
    @NamedQuery(name = "Role.findByModifiedBy", query = "SELECT r FROM Role r WHERE r.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Role.findByDescription", query = "SELECT r FROM Role r WHERE r.description = :description")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id" ,fetch = FetchType.LAZY)
    private List<Privilege> privilegeId;
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY )
//    private Group1 groupId;

    public Role() {
    }

    public Role(String id) {
        this.id = id;
    }

    public Role(String id, Date dateCreated) {
        this.id = id;
        this.dateCreated = dateCreated;
    }

    public String getRoleId() {
        return id;
    }

    public void setRoleId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Privilege> getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(List<Privilege> privilegeId) {
        this.privilegeId = privilegeId;
    }

//    public Group1 getGroupId() {
//        return groupId;
//    }
//
//    public void setGroupId(Group1 groupId) {
//        this.groupId = groupId;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Role[ id=" + id + " ]";
    }
    
}
