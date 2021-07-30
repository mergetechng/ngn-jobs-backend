package com.mergetechng.jobs.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mergetechng.jobs.entities.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

public class UserDto {
    private String userId;
    private String username;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String nationality;
    private String location;
    private String formerJobRole;
    private String dateRegistered;
    private Date lastLogin;
    private Date lastLoginInfo;
    private Date dateCreated;
    private Date dateModified;
    private String modifiedBy;
    private String createdBy;
    private String userImageUrl;
    private String email;
    private boolean online;
    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean getCredentialNonExpired;
    private boolean accountNonLocked;
    private List<Group1> groupId;
    private List<Privilege> privilegeId;
    private List<Role> roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Group1> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<Group1> groupId) {
        this.groupId = groupId;
    }

    public List<Privilege> getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(List<Privilege> privilegeId) {
        this.privilegeId = privilegeId;
    }

    public List<Role> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<Role> roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFormerJobRole() {
        return formerJobRole;
    }

    public void setFormerJobRole(String formerJobRole) {
        this.formerJobRole = formerJobRole;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastLoginInfo() {
        return lastLoginInfo;
    }

    public void setLastLoginInfo(Date lastLoginInfo) {
        this.lastLoginInfo = lastLoginInfo;
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

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isGetCredentialNonExpired() {
        return getCredentialNonExpired;
    }

    public void setGetCredentialNonExpired(boolean getCredentialNonExpired) {
        this.getCredentialNonExpired = getCredentialNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
}
