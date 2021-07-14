/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.*;

/**
 * @author @keemsisi
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
    @Column(nullable = false, name = "password")
    private String password;
    @Column(nullable = false, name = "firstname")
    private String firstName;
    @Column(nullable = false, name = "lastname")
    private String lastName;
    @Column(nullable = false, name = "gender")
    private String gender;
    @Column(nullable = false, name = "nationality")
    private String nationality;
    @Column(nullable = false, name = "location")
    private String location;
    @Column(nullable = false, name = "former_job_role")
    private String formerJobRole;
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
    private List<JobApplicant> jobApplicantList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Education> educationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<JobAlertSubcription> jobAlertSubcriptionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<AccountSettings> accountSettingsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<ActivityLog> activityLogList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCvId")
    private List<Certification> certificationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Reference> referenceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<UserLocationInterest> userLocationInterestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Organization> organizationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Publication> publicationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Skill> skillList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<UserCv> userCvList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<WorkExperience> workExperienceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Hobby> hobbyList;
    @Column(name = "user_id", nullable = false)
    private boolean online;
    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired;
    @Column(name = "user_id", nullable = false)
    private boolean isEnabled;
    @Column(name = "credential_non_expired", nullable = false)
    private boolean getCredentialNonExpired;
    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Group1> groupId;


    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String username, String password, String firstName, String lastName, String gender, String nationality, String location, String formerJobRole, String dateRegistered, Date lastLogin, Date lastLoginInfo, Date dateCreated, Date dateModified, String modifiedBy, String createdBy, String userImageUrl, String email, boolean online, boolean accountNonExpired, boolean isEnabled, boolean getCredentialNonExpired, boolean accountNonLocked) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        this.location = location;
        this.formerJobRole = formerJobRole;
        this.dateRegistered = dateRegistered;
        this.lastLogin = lastLogin;
        this.lastLoginInfo = lastLoginInfo;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.modifiedBy = modifiedBy;
        this.createdBy = createdBy;
        this.userImageUrl = userImageUrl;
        this.email = email;
        this.online = online;
        this.accountNonExpired = accountNonExpired;
        this.isEnabled = isEnabled;
        this.getCredentialNonExpired = getCredentialNonExpired;
        this.accountNonLocked = accountNonLocked;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    public List<JobApplicant> getJobApplicantList() {
        return jobApplicantList;
    }

    public void setJobApplicantList(List<JobApplicant> jobApplicantList) {
        this.jobApplicantList = jobApplicantList;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public List<JobAlertSubcription> getJobAlertSubcriptionList() {
        return jobAlertSubcriptionList;
    }

    public void setJobAlertSubcriptionList(List<JobAlertSubcription> jobAlertSubcriptionList) {
        this.jobAlertSubcriptionList = jobAlertSubcriptionList;
    }

    public List<AccountSettings> getAccountSettingsList() {
        return accountSettingsList;
    }

    public void setAccountSettingsList(List<AccountSettings> accountSettingsList) {
        this.accountSettingsList = accountSettingsList;
    }

    public List<ActivityLog> getActivityLogList() {
        return activityLogList;
    }

    public void setActivityLogList(List<ActivityLog> activityLogList) {
        this.activityLogList = activityLogList;
    }

    public List<Certification> getCertificationList() {
        return certificationList;
    }

    public void setCertificationList(List<Certification> certificationList) {
        this.certificationList = certificationList;
    }

    public List<Reference> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(List<Reference> referenceList) {
        this.referenceList = referenceList;
    }

    public List<UserLocationInterest> getUserLocationInterestList() {
        return userLocationInterestList;
    }

    public void setUserLocationInterestList(List<UserLocationInterest> userLocationInterestList) {
        this.userLocationInterestList = userLocationInterestList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public List<Publication> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public List<UserCv> getUserCvList() {
        return userCvList;
    }

    public void setUserCvList(List<UserCv> userCvList) {
        this.userCvList = userCvList;
    }

    public List<WorkExperience> getWorkExperienceList() {
        return workExperienceList;
    }

    public void setWorkExperienceList(List<WorkExperience> workExperienceList) {
        this.workExperienceList = workExperienceList;
    }

    public List<Hobby> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(List<Hobby> hobbyList) {
        this.hobbyList = hobbyList;
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

    public boolean isOnline() {
        return online;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean getIsGetCredentialNonExpired() {
        return getCredentialNonExpired;
    }

    public void setGetCredentialNonExpired(boolean getCredentialNonExpired) {
        this.getCredentialNonExpired = getCredentialNonExpired;
    }

    public boolean getIsAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setOnline(boolean b) {
        this.online = b;
    }

    public void setIsEnabled(boolean b) {
        this.isEnabled = b;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isGetCredentialNonExpired() {
        return getCredentialNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public List<Group1> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<Group1> groupId) {
        this.groupId = groupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
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

    public void setJobRole(String jobRole) {
        this.formerJobRole = jobRole;
    }
}
