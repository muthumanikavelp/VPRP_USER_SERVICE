package com.vprp.user.data.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class MasterDataResponse {

	private Long id;
	
	private String mstName;

	private String mstCode;

	private Long mstExtId;

	private Long mstParentId;

	private String mstType;

	private String mstStatus;

	private String mstDesc;

	private String mstShortName;

	private String mstAddlData;

	private String createdBy;

	private String updatedBy;
	
	private Date createdDate;
	
	private Date updateDate;
	
	private Long revisionNumber;
	
	private Integer stateId;
	
	public MasterDataResponse(Long id, String mstName, String mstCode, Long mstExtId, Long mstParentId,
			String mstType, String mstStatus, String mstDesc, String mstShortName, String mstAddlData, String createdBy,
			String updatedBy, Date createdDate, Date updateDate, Long revisionNumber, Integer stateId) {
		super();
		this.id = id;
		this.mstName = mstName;
		this.mstCode = mstCode;
		this.mstExtId = mstExtId;
		this.mstParentId = mstParentId;
		this.mstType = mstType;
		this.mstStatus = mstStatus;
		this.mstDesc = mstDesc;
		this.mstShortName = mstShortName;
		this.mstAddlData = mstAddlData;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
		this.stateId = stateId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMstName() {
		return mstName;
	}

	public void setMstName(String mstName) {
		this.mstName = mstName;
	}

	public String getMstCode() {
		return mstCode;
	}

	public void setMstCode(String mstCode) {
		this.mstCode = mstCode;
	}

	public Long getMstExtId() {
		return mstExtId;
	}

	public void setMstExtId(Long mstExtId) {
		this.mstExtId = mstExtId;
	}

	public Long getMstParentId() {
		return mstParentId;
	}

	public void setMstParentId(Long mstParentId) {
		this.mstParentId = mstParentId;
	}

	public String getMstType() {
		return mstType;
	}

	public void setMstType(String mstType) {
		this.mstType = mstType;
	}

	public String getMstStatus() {
		return mstStatus;
	}

	public void setMstStatus(String mstStatus) {
		this.mstStatus = mstStatus;
	}

	public String getMstDesc() {
		return mstDesc;
	}

	public void setMstDesc(String mstDesc) {
		this.mstDesc = mstDesc;
	}

	public String getMstShortName() {
		return mstShortName;
	}

	public void setMstShortName(String mstShortName) {
		this.mstShortName = mstShortName;
	}

	public String getMstAddlData() {
		return mstAddlData;
	}

	public void setMstAddlData(String mstAddlData) {
		this.mstAddlData = mstAddlData;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
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

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

//	  @OneToMany(type => MasterDataParentMap, parentMap => parentMap.masterData)
//	  private String parentMaps: MasterDataParentMap[];
//
//	  @OneToOne(type => MasterDataLang, mdLang => mdLang.md)
//	  private String mdLang: MasterDataLang;

}
