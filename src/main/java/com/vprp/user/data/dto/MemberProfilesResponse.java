package com.vprp.user.data.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class MemberProfilesResponse {

	private Integer memberId;

	private String memberCode;

	private Integer cboType;

	private Long cboId;

	private String geoEntityCode;

	private String memberName;

	private String fatherHusband;

	private String relationName;

	private String relationNameLocal;
	
	private Integer gender;

	private Integer maritalStatus;
	
	private Integer religion;

	private Integer socialCategory;

	private Integer tribalCategory;

	private Integer bplNumber;

	private Integer pipCategory;

	private Date pipDate;

	private Integer highestEducationLevel;

	private Date dob;
	
	private Integer age;

	private Date ageAsOn;

	private Integer minority;

	private Integer disabilityDetails;

	private Integer wellBeingCategory;

	private Integer primaryOccupation;

	private Integer secondaryOccupation;

	private Integer tertiaryOccupation;

	private Date joiningDate;

	private Date leavingDate;
	
	private Integer reasonForLeaving;

	private String ifMinorMemeberReplaced;

	private String guardianName;

	private String guardianNameLocal;

	private Integer guardianRelation;

	private Integer designation;

	private Integer status;

	public MemberProfilesResponse(Integer memberId, String memberCode, Integer cboType, Long cboId,
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

}
