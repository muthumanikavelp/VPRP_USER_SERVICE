package com.vprp.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application_metadata", schema="vprp_master")
public class ApplicationMetadata {
	
	  @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private String id;

	  @Column(name = "mst_name")
	  private String mstName;

	  @Column(name = "mst_code")
	  private String mstCode;

	  @Column(name = "mst_ext_id")
	  private Long mstExtId;

	  @Column(name = "mst_parent_id")
	  private Long mstParentId;

	  @Column(name = "mst_type")
	  private String mstType;

	  @Column(name = "mst_status")
	  private String mstStatus;

	  @Column(name = "mst_desc")
	  private String mstDesc;

	  @Column(name = "mst_short_name")
	  private String mstShortName;

	  @Column(name = "mst_addl_data")
	  private String mstAddlData;

	  @Column(name = "created_by")
	  private Long createdBy;

	  @Column(name = "updated_by")
	  private String updatedBy;

	  @Column(name = "created_date")
	  private Date createdDate;

	  @Column(name = "updated_date")
	  private Date updateDate;

	  @Column(name = "revision_number")
	  private Long revisionNumber;

	  @Column(name = "mst_state_id")
	  private Long stateId;
	  
	  
	  

	public ApplicationMetadata(String id, String mstName, String mstCode, Long mstExtId, Long mstParentId, String mstType,
			String mstStatus, String mstDesc, String mstShortName, String mstAddlData, Long createdBy, String updatedBy,
			Date createdDate, Date updateDate, Long revisionNumber, Long stateId) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

//	  @OneToMany(type => MasterDataParentMap, parentMap => parentMap.masterData)
//	  private String parentMaps: MasterDataParentMap[];
//
//	  @OneToOne(type => MasterDataLang, mdLang => mdLang.md)
//	  private String mdLang: MasterDataLang;
	  
}
