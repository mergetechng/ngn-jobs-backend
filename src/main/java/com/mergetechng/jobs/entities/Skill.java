/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mergetechng.jobs.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Integral
 */
@Entity
@Table(catalog = "ngn_jobs", schema = "POSTGRES")
@NamedQueries({
    @NamedQuery(name = "SkillDto.findAll", query = "SELECT s FROM SkillDto s"),
    @NamedQuery(name = "SkillDto.findBySkillId", query = "SELECT s FROM SkillDto s WHERE s.skillId = :skillId"),
    @NamedQuery(name = "SkillDto.findBySkillArea", query = "SELECT s FROM SkillDto s WHERE s.skillArea = :skillArea"),
    @NamedQuery(name = "SkillDto.findBySkillDescription", query = "SELECT s FROM SkillDto s WHERE s.skillDescription = :skillDescription"),
    @NamedQuery(name = "SkillDto.findBySkillName", query = "SELECT s FROM SkillDto s WHERE s.skillName = :skillName"),
    @NamedQuery(name = "SkillDto.findByCreatedDate", query = "SELECT s FROM SkillDto s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "SkillDto.findByDateCreated", query = "SELECT s FROM SkillDto s WHERE s.dateCreated = :dateCreated"),
    @NamedQuery(name = "SkillDto.findByModifiedBy", query = "SELECT s FROM SkillDto s WHERE s.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "SkillDto.findByCreatedBy", query = "SELECT s FROM SkillDto s WHERE s.createdBy = :createdBy")})
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "skill_id", nullable = false, length = 36)
    private String skillId;
    @Basic(optional = false)
    @Column(name = "skill_area", nullable = false, length = 36)
    private String skillArea;
    @Column(name = "skill_description", length = 2147483647)
    private String skillDescription;
    @Basic(optional = false)
    @Column(name = "skill_name", nullable = false, length = 250)
    private String skillName;
    @Basic(optional = false)
    @Column(name = "created_date", nullable = false, length = 250)
    private String createdDate;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "modified_by", length = 250)
    private String modifiedBy;
    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 250)
    private String createdBy;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "user_cv_id", referencedColumnName = "user_cv_id", nullable = false)
    @ManyToOne(optional = false)
    private UserCv userCvId;

    public Skill() {
    }

    public Skill(String skillId) {
        this.skillId = skillId;
    }

    public Skill(String skillId, String skillArea, String skillName, String createdDate, String createdBy) {
        this.skillId = skillId;
        this.skillArea = skillArea;
        this.skillName = skillName;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getSkillArea() {
        return skillArea;
    }

    public void setSkillArea(String skillArea) {
        this.skillArea = skillArea;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public UserCv getUserCvId() {
        return userCvId;
    }

    public void setUserCvId(UserCv userCvId) {
        this.userCvId = userCvId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillId != null ? skillId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skill)) {
            return false;
        }
        Skill other = (Skill) object;
        if ((this.skillId == null && other.skillId != null) || (this.skillId != null && !this.skillId.equals(other.skillId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mergetechng.jobs.entities.SkillDto[ skillId=" + skillId + " ]";
    }
    
}
