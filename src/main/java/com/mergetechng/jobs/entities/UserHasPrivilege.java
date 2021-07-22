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

/**
 *
 * @author keemsisi
 */
@Entity
@Table(name = "user_has_privilege", catalog = "faston", schema = "public")
@Document
@NamedQueries({ @NamedQuery(name = "UserHasPrivilege.findAll", query = "SELECT u FROM UserHasPrivilege u"),
        @NamedQuery(name = "UserHasPrivilege.findById", query = "SELECT u FROM UserHasPrivilege u WHERE u.UserHasPrivilegePK.userId = :userId"),
        @NamedQuery(name = "UserHasPrivilege.findById", query = "SELECT u FROM UserHasPrivilege u WHERE u.UserHasPrivilegePK.id = :id"),
        @NamedQuery(name = "UserHasPrivilege.findByDateCreated", query = "SELECT u FROM UserHasPrivilege u WHERE u.dateCreated = :dateCreated") })
public class UserHasPrivilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserHasPrivilegePK UserHasPrivilegePK;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "privilege_id", referencedColumnName = "privilege_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Privilege privilege;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    public UserHasPrivilege() {
    }

    public UserHasPrivilege(UserHasPrivilegePK UserHasPrivilegePK) {
        this.UserHasPrivilegePK = UserHasPrivilegePK;
    }

    public UserHasPrivilege(UserHasPrivilegePK UserHasPrivilegePK, Date dateCreated) {
        this.UserHasPrivilegePK = UserHasPrivilegePK;
        this.dateCreated = dateCreated;
    }

    public UserHasPrivilege(String userId, String privilegeId) {
        this.UserHasPrivilegePK = new UserHasPrivilegePK(userId, privilegeId);
    }

    public UserHasPrivilegePK getUserHasPrivilegePK() {
        return UserHasPrivilegePK;
    }

    public void setUserHasPrivilegePK(UserHasPrivilegePK UserHasPrivilegePK) {
        this.UserHasPrivilegePK = UserHasPrivilegePK;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Privilege getprivilege() {
        return privilege;
    }

    public void setprivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (UserHasPrivilegePK != null ? UserHasPrivilegePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasPrivilege)) {
            return false;
        }
        UserHasPrivilege other = (UserHasPrivilege) object;
        if ((this.UserHasPrivilegePK == null && other.UserHasPrivilegePK != null)
                || (this.UserHasPrivilegePK != null && !this.UserHasPrivilegePK.equals(other.UserHasPrivilegePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.UserHasPrivilege[ UserHasPrivilegePK=" + UserHasPrivilegePK
                + " ]";
    }

}
