/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.*;

/**
 * @author @keemsisi
 */
@Entity
@Document
@Table(catalog = "ngn_jobs", schema = "POSTGRES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})})
@NamedQueries({
        @NamedQuery(name = "UserDto.findAll", query = "SELECT u FROM UserDto u"),
        @NamedQuery(name = "UserDto.findById", query = "SELECT u FROM UserDto u WHERE u.id = :id"),
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
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @Column(nullable = false, length = 36 , name = "username")
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
    private String lastLoginInfo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<JobApplicant> jobApplicantList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Education> educationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<JobAlertSubcription> jobAlertSubcriptionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<AccountSettings> accountSettingsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<ActivityLog> activityLogList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCvId")
    private List<Certification> certificationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Reference> referenceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<UserLocationInterest> userLocationInterestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Organization> organizationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Publication> publicationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Skill> skillList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<UserCv> userCvList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<WorkExperience> workExperienceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Hobby> hobbyList;
    @Column(name = "online", nullable = false)
    private boolean online;
    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired;
    @Column(name = "id", nullable = false)
    private boolean isEnabled;
    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified;
    @Column(name = "credential_non_expired", nullable = false)
    private boolean getCredentialNonExpired;
    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id", fetch = FetchType.LAZY)
    private List<Group1> groupId;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String accountType; // Job Seeker | JobEmployer or Admin
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String registrationMode;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String status;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String phone;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String companyName; // for job employer
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String companyAddress; // for job employer
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String companyIndustry; // for job employer
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String companyCategory; // for job employer


    public User(String id) {
        this.id = id;
    }

    public User(){}

    public User(String id, String username, String password, String firstName, String lastName, String gender, String nationality, String location, String formerJobRole, String dateRegistered, Date lastLogin, String lastLoginInfo, Date dateCreated, Date dateModified, String modifiedBy, String createdBy, String userImageUrl, String email, boolean online, boolean accountNonExpired, boolean isEnabled, boolean getCredentialNonExpired, boolean accountNonLocked) {
        this.id = id;
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
        return id;
    }

    public void setUserId(String id) {
        this.id = id;
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

    public String getLastLoginInfo() {
        return lastLoginInfo;
    }

    public void setLastLoginInfo(String lastLoginInfo) {
        this.lastLoginInfo = lastLoginInfo;
    }

    public void setGetCredentialNonExpired(boolean getCredentialNonExpired) {
        this.getCredentialNonExpired = getCredentialNonExpired;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.UserDto[ id=" + id + " ]";
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

    public void setCredentialNonExpired(boolean getCredentialNonExpired) {
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

    public String getRegistrationMode() {
        return registrationMode;
    }

    public void setRegistrationMode(String registrationMode) {
        this.registrationMode = registrationMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public void setFormerJobRole(String formerJobRole) {
        this.formerJobRole = formerJobRole;
    }

    public boolean getIsEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyIndustry() {
        return this.companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }
}
