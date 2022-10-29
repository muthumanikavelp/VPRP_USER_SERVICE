package com.vprp.user.data.dto;

import java.util.Date;
public class ShgApplicationsResponse {


	private Long id;

	private Long shgId;

	private Long parentCBOId;

	private Integer parentCBOType;

	private Long stateId;

	private Long districtId;

	private Long blockId;

	private Long panchayatId;

	private Long villageId;

	private Integer surveyYear;

	private String applicationDataJson;

	private Integer shgSurveyStatus;

	private Long createdBy;

	private Long updatedBy;

	private Date createdDate;

	private Date updateDate;
	
	private Long revisionNumber;

	public ShgApplicationsResponse(Long shgId, Long parentCBOId, Integer parentCBOType, Long stateId, Long districtId,
			Long blockId, Long panchayatId, Long villageId, Integer surveyYear, String applicationDataJson,
			Integer shgSurveyStatus, Long createdBy, Long updatedBy, Date createdDate, Date updateDate,
			Long revisionNumber) {
		super();
		this.shgId = shgId;
		this.parentCBOId = parentCBOId;
		this.parentCBOType = parentCBOType;
		this.stateId = stateId;
		this.districtId = districtId;
		this.blockId = blockId;
		this.panchayatId = panchayatId;
		this.villageId = villageId;
		this.surveyYear = surveyYear;
		this.applicationDataJson = applicationDataJson;
		this.shgSurveyStatus = shgSurveyStatus;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShgId() {
		return shgId;
	}

	public void setShgId(Long shgId) {
		this.shgId = shgId;
	}

	public Long getParentCBOId() {
		return parentCBOId;
	}

	public void setParentCBOId(Long parentCBOId) {
		this.parentCBOId = parentCBOId;
	}

	public Integer getParentCBOType() {
		return parentCBOType;
	}

	public void setParentCBOType(Integer parentCBOType) {
		this.parentCBOType = parentCBOType;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

	public String getApplicationDataJson() {
		return applicationDataJson;
	}

	public void setApplicationDataJson(String applicationDataJson) {
		this.applicationDataJson = applicationDataJson;
	}

	public Integer getShgSurveyStatus() {
		return shgSurveyStatus;
	}

	public void setShgSurveyStatus(Integer shgSurveyStatus) {
		this.shgSurveyStatus = shgSurveyStatus;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
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

}
