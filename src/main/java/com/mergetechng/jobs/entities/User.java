/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Integral
 */
@Entity
@Table(catalog = "ngn_jobs", schema = "POSTGRES", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@NamedQueries({
    @NamedQuery(name = "UserDto.findAll", query = "SELECT u FROM UserDto u"),
    @NamedQuery(name = "UserDto.findByUserId", query = "SELECT u FROM UserDto u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserDto.findByUsername", query = "SELECT u FROM UserDto u WHERE u.username = :username"),
    @NamedQuery(name = "UserDto.findByPassword", query = "SELECT u FROM UserDto u WHERE u.password = :password"),
    @NamedQuery(name = "UserDto.findByDateRegistered", query = "SELECT u FROM UserDto u WHERE u.dateRegistered = :dateRegistered"),
    @NamedQuery(name = "UserDto.findByLastLogin", query = "SELECT u FROM UserDto u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "UserDto.findByLastLoginInfo", query = "SELECT u FROM UserDto u WHERE u.lastLoginInfo = :lastLoginInfo"),
    @NamedQuery(name = "UserDto.findByGender", query = "SELECT u FROM UserDto u WHERE u.gender = :gender"),
    @NamedQuery(name = "UserDto.findByDateCreated", query = "SELECT u FROM UserDto u WHERE u.dateCreated = :dateCreated"),
    @NamedQuery(name = "UserDto.findByDateModified", query = "SELECT u FROM UserDto u WHERE u.dateModified = :dateModified"),
    @NamedQuery(name = "UserDto.findByModifiedBy", query = "SELECT u FROM UserDto u WHERE u.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "UserDto.findByCreatedBy", query = "SELECT u FROM UserDto u WHERE u.createdBy = :createdBy"),
    @NamedQuery(name = "UserDto.findByUserImageUrl", query = "SELECT u FROM UserDto u WHERE u.userImageUrl = :userImageUrl"),
    @NamedQuery(name = "UserDto.findByEmail", query = "SELECT u FROM UserDto u WHERE u.email = :email")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;
    @Basic(optional = false)
    @Column(nullable = false, length = 36)
    private String username;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date password;
    @Basic(optional = false)
    @Column(name = "date_registered", nullable = false, length = 250)
    private String dateRegistered;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Basic(optional = false)
    @Column(name = "last_login_info", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginInfo;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String gender;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Column(name = "modified_by", length = 250)
    private String modifiedBy;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @Column(name = "user_image_url", length = 2147483647)
    private String userImageUrl;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<JobApplicant> jobApplicantCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Education> educationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<JobAlertSubcription> jobAlertSubcriptionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<AccountSettings> accountSettingsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<ActivityLog> activityLogCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCvId")
    private Collection<Certification> certificationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Reference> referenceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UserLocationInterest> userLocationInterestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Organization> organizationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Publication> publicationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Skill> skillCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UserCv> userCvCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<WorkExperience> workExperienceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Hobby> hobbyCollection;

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String username, Date password, String dateRegistered, Date lastLoginInfo, String gender, Date dateCreated, String createdBy, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.dateRegistered = dateRegistered;
        this.lastLoginInfo = lastLoginInfo;
        this.gender = gender;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getPassword() {
        return password;
    }

    public void setPassword(Date password) {
        this.password = password;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Collection<JobApplicant> getJobApplicantCollection() {
        return jobApplicantCollection;
    }

    public void setJobApplicantCollection(Collection<JobApplicant> jobApplicantCollection) {
        this.jobApplicantCollection = jobApplicantCollection;
    }

    public Collection<Education> getEducationCollection() {
        return educationCollection;
    }

    public void setEducationCollection(Collection<Education> educationCollection) {
        this.educationCollection = educationCollection;
    }

    public Collection<JobAlertSubcription> getJobAlertSubcriptionCollection() {
        return jobAlertSubcriptionCollection;
    }

    public void setJobAlertSubcriptionCollection(Collection<JobAlertSubcription> jobAlertSubcriptionCollection) {
        this.jobAlertSubcriptionCollection = jobAlertSubcriptionCollection;
    }

    public Collection<AccountSettings> getAccountSettingsCollection() {
        return accountSettingsCollection;
    }

    public void setAccountSettingsCollection(Collection<AccountSettings> accountSettingsCollection) {
        this.accountSettingsCollection = accountSettingsCollection;
    }

    public Collection<ActivityLog> getActivityLogCollection() {
        return activityLogCollection;
    }

    public void setActivityLogCollection(Collection<ActivityLog> activityLogCollection) {
        this.activityLogCollection = activityLogCollection;
    }

    public Collection<Certification> getCertificationCollection() {
        return certificationCollection;
    }

    public void setCertificationCollection(Collection<Certification> certificationCollection) {
        this.certificationCollection = certificationCollection;
    }

    public Collection<Reference> getReferenceCollection() {
        return referenceCollection;
    }

    public void setReferenceCollection(Collection<Reference> referenceCollection) {
        this.referenceCollection = referenceCollection;
    }

    public Collection<UserLocationInterest> getUserLocationInterestCollection() {
        return userLocationInterestCollection;
    }

    public void setUserLocationInterestCollection(Collection<UserLocationInterest> userLocationInterestCollection) {
        this.userLocationInterestCollection = userLocationInterestCollection;
    }

    public Collection<Organization> getOrganizationCollection() {
        return organizationCollection;
    }

    public void setOrganizationCollection(Collection<Organization> organizationCollection) {
        this.organizationCollection = organizationCollection;
    }

    public Collection<Publication> getPublicationCollection() {
        return publicationCollection;
    }

    public void setPublicationCollection(Collection<Publication> publicationCollection) {
        this.publicationCollection = publicationCollection;
    }

    public Collection<Skill> getSkillCollection() {
        return skillCollection;
    }

    public void setSkillCollection(Collection<Skill> skillCollection) {
        this.skillCollection = skillCollection;
    }

    public Collection<UserCv> getUserCvCollection() {
        return userCvCollection;
    }

    public void setUserCvCollection(Collection<UserCv> userCvCollection) {
        this.userCvCollection = userCvCollection;
    }

    public Collection<WorkExperience> getWorkExperienceCollection() {
        return workExperienceCollection;
    }

    public void setWorkExperienceCollection(Collection<WorkExperience> workExperienceCollection) {
        this.workExperienceCollection = workExperienceCollection;
    }

    public Collection<Hobby> getHobbyCollection() {
        return hobbyCollection;
    }

    public void setHobbyCollection(Collection<Hobby> hobbyCollection) {
        this.hobbyCollection = hobbyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.UserDto[ userId=" + userId + " ]";
    }
    
}