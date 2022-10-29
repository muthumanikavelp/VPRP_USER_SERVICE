package com.vprp.user.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gp_applications", schema = "vprp_transaction", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "gp_id", "survey_year" }) })
public class GpApplications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "gp_id")
	private Long gpId;

	@Column(name = "parent_cbo_id")
	private Long parentCBOId;

	@Column(name = "parent_cbo_type")
	private Integer parentCBOType;

	@Column(name = "state_id")
	private Long stateId;

	@Column(name = "district_id")
	private Long districtId;

	@Column(name = "block_id")
	private Long blockId;

	@Column(name = "survey_year")
	private Integer surveyYear;

	@Column(name = "application_data_json")
	private String applicationDataJson;

	@Column(name = "shg_survey_status")
	private Integer shgSurveyStatus;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updateDate;

	@Column(name = "revision_number")
	private Long revisionNumber;

	@Column(name = "addl_data")
	private String additionalData;

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}
	public GpApplications(Long id, Long gpId, Long parentCBOId, Integer parentCBOType, Long stateId, Long districtId,
						  Long blockId, Integer surveyYear, String applicationDataJson, Integer shgSurveyStatus, Long createdBy,
						  Long updatedBy, Date createdDate, Date updateDate, Long revisionNumber, String additionalData) {
		this.id = id;
		this.gpId = gpId;
		this.parentCBOId = parentCBOId;
		this.parentCBOType = parentCBOType;
		this.stateId = stateId;
		this.districtId = districtId;
		this.blockId = blockId;
		this.surveyYear = surveyYear;
		this.applicationDataJson = applicationDataJson;
		this.shgSurveyStatus = shgSurveyStatus;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
		this.additionalData = additionalData;
	}


	public GpApplications() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGpId() {
		return gpId;
	}

	public void setGpId(Long gpId) {
		this.gpId = gpId;
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
