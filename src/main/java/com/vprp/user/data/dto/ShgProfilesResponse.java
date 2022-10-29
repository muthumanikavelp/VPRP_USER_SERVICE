package com.vprp.user.data.dto;

import java.util.Date;

public class ShgProfilesResponse {

	private Long id;
	
	private Long shgId;

	private Long stateId;

	private Long districtId;

	private Long blockId;

	private Long panchayatId;

	private Long villageId;

	private String geoEntityCode;

	private String shgCode;

	private String shgName;

	private Long parentCBOId;

	private Integer parentCBOType;

	private String dataSource;

	private String createdBy;

	private String updatedBy;

	private Date createdDate;

	private Date updateDate;

	public ShgProfilesResponse() {

	}

	public ShgProfilesResponse(Long shgId, Long stateId, Long districtId, Long blockId, Long panchayatId,
			Long villageId, String geoEndityCode, String shgCode, String shgName, Long parentCBOId,
			Integer parentCBOType, String dataSource, String createdBy, String updatedBy, Date createdDate,
			Date updateDate) {
		super();
		this.shgId = shgId;
		this.stateId = stateId;
		this.districtId = districtId;
		this.blockId = blockId;
		this.panchayatId = panchayatId;
		this.villageId = villageId;
		this.geoEntityCode = geoEndityCode;
		this.shgCode = shgCode;
		this.shgName = shgName;
		this.parentCBOId = parentCBOId;
		this.parentCBOType = parentCBOType;
		this.dataSource = dataSource;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
	}

	
	public ShgProfilesResponse(Long id, Long shgId, Long stateId, Long districtId, Long blockId, Long panchayatId,
			Long villageId, String geoEntityCode, String shgCode, String shgName, Long parentCBOId,
			Integer parentCBOType, String dataSource, String createdBy, String updatedBy, Date createdDate,
			Date updateDate) {
		super();
		this.id = id;
		this.shgId = shgId;
		this.stateId = stateId;
		this.districtId = districtId;
		this.blockId = blockId;
		this.panchayatId = panchayatId;
		this.villageId = villageId;
		this.geoEntityCode = geoEntityCode;
		this.shgCode = shgCode;
		this.shgName = shgName;
		this.parentCBOId = parentCBOId;
		this.parentCBOType = parentCBOType;
		this.dataSource = dataSource;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGeoEntityCode() {
		return geoEntityCode;
	}

	public void setGeoEntityCode(String geoEntityCode) {
		this.geoEntityCode = geoEntityCode;
	}

	public Long getShgId() {
		return shgId;
	}

	public void setShgId(Long shgId) {
		this.shgId = shgId;
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

	public String getGeoEndityCode() {
		return geoEntityCode;
	}

	public void setGeoEndityCode(String geoEndityCode) {
		this.geoEntityCode = geoEndityCode;
	}

	public String getShgCode() {
		return shgCode;
	}

	public void setShgCode(String shgCode) {
		this.shgCode = shgCode;
	}

	public String getShgName() {
		return shgName;
	}

	public void setShgName(String shgName) {
		this.shgName = shgName;
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

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
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

}
