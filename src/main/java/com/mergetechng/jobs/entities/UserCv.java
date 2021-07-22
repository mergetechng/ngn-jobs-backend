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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author @keemsisi
 */
@Entity
@Table(name = "user_cv", catalog = "ngn_jobs", schema = "POSTGRES")
@Document
@NamedQueries({
    @NamedQuery(name = "UserCvDto.findAll", query = "SELECT u FROM UserCvDto u"),
    @NamedQuery(name = "UserCvDto.findByUserCvId", query = "SELECT u FROM UserCvDto u WHERE u.id = :id"),
    @NamedQuery(name = "UserCvDto.findByFirstname", query = "SELECT u FROM UserCvDto u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "UserCvDto.findByLastname", query = "SELECT u FROM UserCvDto u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "UserCvDto.findByAboutProfile", query = "SELECT u FROM UserCvDto u WHERE u.aboutProfile = :aboutProfile"),
    @NamedQuery(name = "UserCvDto.findByOrganization", query = "SELECT u FROM UserCvDto u WHERE u.organization = :organization"),
    @NamedQuery(name = "UserCvDto.findByHomeAddress", query = "SELECT u FROM UserCvDto u WHERE u.homeAddress = :homeAddress"),
    @NamedQuery(name = "UserCvDto.findByPhoneNumber", query = "SELECT u FROM UserCvDto u WHERE u.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "UserCvDto.findByDateCreated", query = "SELECT u FROM UserCvDto u WHERE u.dateCreated = :dateCreated"),
    @NamedQuery(name = "UserCvDto.findByCreatedBy", query = "SELECT u FROM UserCvDto u WHERE u.createdBy = :createdBy"),
    @NamedQuery(name = "UserCvDto.findByModifiedBy", query = "SELECT u FROM UserCvDto u WHERE u.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "UserCvDto.findByDateModified", query = "SELECT u FROM UserCvDto u WHERE u.dateModified = :dateModified")})
public class UserCv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic(optional = false)
    @Column(nullable = false, length = 36)
    private String firstname;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastname;
    @Basic(optional = false)
    @Column(name = "about_profile", nullable = false, length = 250)
    private String aboutProfile;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String organization;
    @Lob
    private Object educations;
    @Lob
    private Object certifications;
    @Lob
    private Object references;
    @Column(name = "home_address", length = 250)
    private String homeAddress;
    @Lob
    private Object publications;
    @Basic(optional = false)
    @Column(name = "phone_number", nullable = false, length = 250)
    private String phoneNumber;
    @Lob
    private Object hubbies;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @Column(name = "modified_by", length = 250)
    private String modifiedBy;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Education> educationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCvId1")
    private List<Certification> certificationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Reference> referenceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Organization> organizationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Publication> publicationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Skill> skillList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Refree> refreeList;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<WorkExperience> workExperienceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Hobby> hobbyList;

    public UserCv() {
    }

    public UserCv(String id) {
        this.id = id;
    }

    public UserCv(String id, String firstname, Date lastname, String aboutProfile, String organization, String phoneNumber, Date dateCreated, String createdBy) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.aboutProfile = aboutProfile;
        this.organization = organization;
        this.phoneNumber = phoneNumber;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getUserCvId() {
        return id;
    }

    public void setUserCvId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getLastname() {
        return lastname;
    }

    public void setLastname(Date lastname) {
        this.lastname = lastname;
    }

    public String getAboutProfile() {
        return aboutProfile;
    }

    public void setAboutProfile(String aboutProfile) {
        this.aboutProfile = aboutProfile;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Object getEducations() {
        return educations;
    }

    public void setEducations(Object educations) {
        this.educations = educations;
    }

    public Object getCertifications() {
        return certifications;
    }

    public void setCertifications(Object certifications) {
        this.certifications = certifications;
    }

    public Object getReferences() {
        return references;
    }

    public void setReferences(Object references) {
        this.references = references;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Object getPublications() {
        return publications;
    }

    public void setPublications(Object publications) {
        this.publications = publications;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getHubbies() {
        return hubbies;
    }

    public void setHubbies(Object hubbies) {
        this.hubbies = hubbies;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
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

    public List<Refree> getRefreeList() {
        return refreeList;
    }

    public void setRefreeList(List<Refree> refreeList) {
        this.refreeList = refreeList;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof UserCv)) {
            return false;
        }
        UserCv other = (UserCv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.UserCvDto[ id=" + id + " ]";
    }
    
}
