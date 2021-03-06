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
@Table(name = "privilege", catalog = "faston", schema = "public")
@Document
@NamedQueries({
    @NamedQuery(name = "privilege.findAll", query = "SELECT p FROM privilege p"),
    @NamedQuery(name = "privilege.findById", query = "SELECT p FROM privilege p WHERE p.id = :id"),
    @NamedQuery(name = "privilege.findByName", query = "SELECT p FROM privilege p WHERE p.name = :name"),
    @NamedQuery(name = "privilege.findByDescription", query = "SELECT p FROM privilege p WHERE p.description = :description"),
    @NamedQuery(name = "privilege.findByDateCreated", query = "SELECT p FROM privilege p WHERE p.dateCreated = :dateCreated"),
    @NamedQuery(name = "privilege.findByDateModified", query = "SELECT p FROM privilege p WHERE p.dateModified = :dateModified"),
    @NamedQuery(name = "privilege.findByCreatedBy", query = "SELECT p FROM privilege p WHERE p.createdBy = :createdBy"),
    @NamedQuery(name = "privilege.findByModifedBy", query = "SELECT p FROM privilege p WHERE p.modifedBy = :modifedBy")})
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_modified")
    private Boolean dateModified;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modifed_by")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "privilege", fetch = FetchType.LAZY)
    private List<UserHasPrivilege> UserHasPrivilegeList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "privilege", fetch = FetchType.LAZY)
//    private List<GroupHasPrivilege> groupHasPrivilegeList;
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Role roleId;

    public Privilege() {
    }

    public Privilege(String id) {
        this.id = id;
    }

    public Privilege(String id, Date dateCreated) {
        this.id = id;
        this.dateCreated = dateCreated;
    }

    public String getPrivilegeId() {
        return id;
    }

    public void setPrivilegeId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getDateModified() {
        return dateModified;
    }

    public void setDateModified(Boolean dateModified) {
        this.dateModified = dateModified;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifedBy() {
        return modifedBy;
    }

    public void setModifedBy(Date modifedBy) {
        this.modifedBy = modifedBy;
    }

    public List<UserHasPrivilege> getUserHasPrivilegeList() {
        return UserHasPrivilegeList;
    }

    public void setUserHasPrivilegeList(List<UserHasPrivilege> UserHasPrivilegeList) {
        this.UserHasPrivilegeList = UserHasPrivilegeList;
    }

//    public List<GroupHasPrivilege> getGroupHasPrivilegeList() {
//        return GroupHasPrivilegeList;
//    }

//    public void setGroupHasPrivilegeList(List<GroupHasPrivilege> GroupHasPrivilegeList) {
//        this.GroupHasPrivilegeList = GroupHasPrivilegeList;
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
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.privilege[ id=" + id + " ]";
    }
    
}
