package com.vprp.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_profile", schema = "nrlm_master")
public class MemberProfiles {

	public MemberProfiles() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Integer memberId;

	@Column(name = "member_code")
	private String memberCode;

	@Column(name = "cbo_type")
	private Integer cboType;

	@Column(name = "cbo_id")
	private Long cboId;

	@Column(name = "geo_entity_code")
	private String geoEntityCode;

	@Column(name = "member_name")
	private String memberName;

	@Column(name = "father_husband")
	private String fatherHusband;

	@Column(name = "relation_name")
	private String relationName;

	@Column(name = "relation_name_local")
	private String relationNameLocal;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "marital_status")
	private Integer maritalStatus;

	@Column(name = "religion")
	private Integer religion;

	@Column(name = "social_category")
	private Integer socialCategory;

	@Column(name = "tribal_category")
	private Integer tribalCategory;

	@Column(name = "bpl_number")
	private Integer bplNumber;

	@Column(name = "pip_category")
	private Integer pipCategory;

	@Column(name = "pip_date")
	private Date pipDate;

	@Column(name = "highest_education_level")
	private Integer highestEducationLevel;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "age")
	private Integer age;

	@Column(name = "age_as_on")
	private Date ageAsOn;

	@Column(name = "minority")
	private Integer minority;

	@Column(name = "disability_details")
	private Integer disabilityDetails;

	@Column(name = "wellbeing_category")
	private Integer wellBeingCategory;

	@Column(name = "primary_occupation")
	private Integer primaryOccupation;

	@Column(name = "secondary_occupation")
	private Integer secondaryOccupation;

	@Column(name = "tertiary_occupation")
	private Integer tertiaryOccupation;

	@Column(name = "joining_date")
	private Date joiningDate;

	@Column(name = "leaving_date")
	private Date leavingDate;

	@Column(name = "reason_for_leaving")
	private Integer reasonForLeaving;

	@Column(name = "if_minor_member_replaced")
	private String ifMinorMemeberReplaced;

	@Column(name = "guardian_name")
	private String guardianName;

	@Column(name = "guardian_name_local")
	private String guardianNameLocal;

	@Column(name = "guardian_relation")
	private Integer guardianRelation;

	@Column(name = "designation")
	private Integer designation;

	@Column(name = "status")
	private Integer status;
	
	@Column(name = "head_house_hold")
	private Boolean headHouseHold;
	
	@Column(name = "house_hold_code")
	private Integer houseHoldCode;
	

	public MemberProfiles(Integer memberId, String memberCode, Integer cboType, Long cboId,
			String geoEntityCode, String memberName, String fatherHusband, String relationName,
			String relationNameLocal, Integer gender, Integer maritalStatus, Integer religion, Integer socialCategory,
			Integer tribalCategory, Integer bplNumber, Integer pipCategory, Date pipDate, Integer highestEducationLevel,
			Date dob, Integer age, Date ageAsOn, Integer minority, Integer disabilityDetails, Integer wellBeingCategory,
			Integer primaryOccupation, Integer secondaryOccupation, Integer tertiaryOccupation, Date joiningDate,
			Date leavingDate, Integer reasonForLeaving, String ifMinorMemeberReplaced, String guardianName,
			String guardianNameLocal, Integer guardianRelation, Integer designation, Integer status) {
		super();
		this.memberId = memberId;
		this.memberCode = memberCode;
		this.cboType = cboType;
		this.cboId = cboId;
		this.geoEntityCode = geoEntityCode;
		this.memberName = memberName;
		this.fatherHusband = fatherHusband;
		this.relationName = relationName;
		this.relationNameLocal = relationNameLocal;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.religion = religion;
		this.socialCategory = socialCategory;
		this.tribalCategory = tribalCategory;
		this.bplNumber = bplNumber;
		this.pipCategory = pipCategory;
		this.pipDate = pipDate;
		this.highestEducationLevel = highestEducationLevel;
		this.dob = dob;
		this.age = age;
		this.ageAsOn = ageAsOn;
		this.minority = minority;
		this.disabilityDetails = disabilityDetails;
		this.wellBeingCategory = wellBeingCategory;
		this.primaryOccupation = primaryOccupation;
		this.secondaryOccupation = secondaryOccupation;
		this.tertiaryOccupation = tertiaryOccupation;
		this.joiningDate = joiningDate;
		this.leavingDate = leavingDate;
		this.reasonForLeaving = reasonForLeaving;
		this.ifMinorMemeberReplaced = ifMinorMemeberReplaced;
		this.guardianName = guardianName;
		this.guardianNameLocal = guardianNameLocal;
		this.guardianRelation = guardianRelation;
		this.designation = designation;
		this.status = status;
	}


	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public Integer getCboType() {
		return cboType;
	}

	public void setCboType(Integer cboType) {
		this.cboType = cboType;
	}

	public Long getCboId() {
		return cboId;
	}

	public void setCboId(Long cboId) {
		this.cboId = cboId;
	}

	public String getGeoEntityCode() {
		return geoEntityCode;
	}

	public void setGeoEntityCode(String geoEntityCode) {
		this.geoEntityCode = geoEntityCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getFatherHusband() {
		return fatherHusband;
	}

	public void setFatherHusband(String fatherHusband) {
		this.fatherHusband = fatherHusband;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getRelationNameLocal() {
		return relationNameLocal;
	}

	public void setRelationNameLocal(String relationNameLocal) {
		this.relationNameLocal = relationNameLocal;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getReligion() {
		return religion;
	}

	public void setReligion(Integer religion) {
		this.religion = religion;
	}

	public Integer getSocialCategory() {
		return socialCategory;
	}

	public void setSocialCategory(Integer socialCategory) {
		this.socialCategory = socialCategory;
	}

	public Integer getTribalCategory() {
		return tribalCategory;
	}

	public void setTribalCategory(Integer tribalCategory) {
		this.tribalCategory = tribalCategory;
	}

	public Integer getBplNumber() {
		return bplNumber;
	}

	public void setBplNumber(Integer bplNumber) {
		this.bplNumber = bplNumber;
	}

	public Integer getPipCategory() {
		return pipCategory;
	}

	public void setPipCategory(Integer pipCategory) {
		this.pipCategory = pipCategory;
	}

	public Date getPipDate() {
		return pipDate;
	}

	public void setPipDate(Date pipDate) {
		this.pipDate = pipDate;
	}

	public Integer getHighestEducationLevel() {
		return highestEducationLevel;
	}

	public void setHighestEducationLevel(Integer highestEducationLevel) {
		this.highestEducationLevel = highestEducationLevel;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getAgeAsOn() {
		return ageAsOn;
	}

	public void setAgeAsOn(Date ageAsOn) {
		this.ageAsOn = ageAsOn;
	}

	public Integer getMinority() {
		return minority;
	}

	public void setMinority(Integer minority) {
		this.minority = minority;
	}

	public Integer getDisabilityDetails() {
		return disabilityDetails;
	}

	public void setDisabilityDetails(Integer disabilityDetails) {
		this.disabilityDetails = disabilityDetails;
	}

	public Integer getWellBeingCategory() {
		return wellBeingCategory;
	}

	public void setWellBeingCategory(Integer wellBeingCategory) {
		this.wellBeingCategory = wellBeingCategory;
	}

	public Integer getPrimaryOccupation() {
		return primaryOccupation;
	}

	public void setPrimaryOccupation(Integer primaryOccupation) {
		this.primaryOccupation = primaryOccupation;
	}

	public Integer getSecondaryOccupation() {
		return secondaryOccupation;
	}

	public void setSecondaryOccupation(Integer secondaryOccupation) {
		this.secondaryOccupation = secondaryOccupation;
	}

	public Integer getTertiaryOccupation() {
		return tertiaryOccupation;
	}

	public void setTertiaryOccupation(Integer tertiaryOccupation) {
		this.tertiaryOccupation = tertiaryOccupation;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	public Integer getReasonForLeaving() {
		return reasonForLeaving;
	}

	public void setReasonForLeaving(Integer reasonForLeaving) {
		this.reasonForLeaving = reasonForLeaving;
	}

	public String getIfMinorMemeberReplaced() {
		return ifMinorMemeberReplaced;
	}

	public void setIfMinorMemeberReplaced(String ifMinorMemeberReplaced) {
		this.ifMinorMemeberReplaced = ifMinorMemeberReplaced;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianNameLocal() {
		return guardianNameLocal;
	}

	public void setGuardianNameLocal(String guardianNameLocal) {
		this.guardianNameLocal = guardianNameLocal;
	}

	public Integer getGuardianRelation() {
		return guardianRelation;
	}

	public void setGuardianRelation(Integer guardianRelation) {
		this.guardianRelation = guardianRelation;
	}

	public Integer getDesignation() {
		return designation;
	}

	public void setDesignation(Integer designation) {
		this.designation = designation;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public Boolean getHeadHouseHold() {
		return headHouseHold;
	}


	public void setHeadHouseHold(Boolean headHouseHold) {
		this.headHouseHold = headHouseHold;
	}


	public Integer getHouseHoldCode() {
		return houseHoldCode;
	}


	public void setHouseHoldCode(Integer houseHoldCode) {
		this.houseHoldCode = houseHoldCode;
	}

	
}
