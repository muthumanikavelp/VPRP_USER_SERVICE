package com.vprp.user.data.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class ShgMembersResponse {
	
	private String id;
	
	private Long memberId;

	private Long memOrgId;

	private String memberName;

	private String memGender;

	private String memReligion;

	private Long socialCategory;

	private Long tribalCategory;

	private String dob;
	
	private Long age;

	private Long disabilityDetails;

	private String hHhead;

	private Long createdBy;

	private String updatedBy;

	private Date createdDate;

	private Date updateDate;

	private Long revisionNumber;

	private Long stateId;

	private Long applRankCount;
	
	private Long applCount;

	public ShgMembersResponse(String id, Long memberId, Long memOrgId, String memberName, String memGender, String memReligion,
			Long socialCategory, Long tribalCategory, String dob, Long age, Long disabilityDetails, String hHhead,
			Long createdBy, String updatedBy, Date createdDate, Date updateDate, Long revisionNumber, Long stateId,
			Long applRankCount, Long applCount) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.memOrgId = memOrgId;
		this.memberName = memberName;
		this.memGender = memGender;
		this.memReligion = memReligion;
		this.socialCategory = socialCategory;
		this.tribalCategory = tribalCategory;
		this.dob = dob;
		this.age = age;
		this.disabilityDetails = disabilityDetails;
		this.hHhead = hHhead;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
		this.stateId = stateId;
		this.applRankCount = applRankCount;
		this.applCount = applCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMemOrgId() {
		return memOrgId;
	}

	public void setMemOrgId(Long memOrgId) {
		this.memOrgId = memOrgId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemGender() {
		return memGender;
	}

	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}

	public String getMemReligion() {
		return memReligion;
	}

	public void setMemReligion(String memReligion) {
		this.memReligion = memReligion;
	}

	public Long getSocialCategory() {
		return socialCategory;
	}

	public void setSocialCategory(Long socialCategory) {
		this.socialCategory = socialCategory;
	}

	public Long getTribalCategory() {
		return tribalCategory;
	}

	public void setTribalCategory(Long tribalCategory) {
		this.tribalCategory = tribalCategory;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Long getDisabilityDetails() {
		return disabilityDetails;
	}

	public void setDisabilityDetails(Long disabilityDetails) {
		this.disabilityDetails = disabilityDetails;
	}

	public String gethHhead() {
		return hHhead;
	}

	public void sethHhead(String hHhead) {
		this.hHhead = hHhead;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getRevisionNumber() {
		return revisionNumber;
	}

	public void setRevisionNumber(Long revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getApplRankCount() {
		return applRankCount;
	}

	public void setApplRankCount(Long applRankCount) {
		this.applRankCount = applRankCount;
	}

	public Long getApplCount() {
		return applCount;
	}

	public void setApplCount(Long applCount) {
		this.applCount = applCount;
	}

}
