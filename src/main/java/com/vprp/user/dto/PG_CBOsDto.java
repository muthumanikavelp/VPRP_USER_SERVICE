package com.vprp.user.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PG_CBOsDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long clfId;
	private String stateId;
	private String districtId;
	private String blockId;
	private String geoEntitycode;
	private String clfCode;
	private String clfName;
	private Long parentCboid;
	private int parentCbotype;
	
	private String pgCode;
	private String pgName;
	private long pgId;
	
	
	
	public long getPgId() {
		return pgId;
	}
	public void setPgId(long pgId) {
		this.pgId = pgId;
	}
	public Long getClfId() {
		return clfId;
	}
	public void setClfId(Long clfId) {
		this.clfId = clfId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getGeoEntitycode() {
		return geoEntitycode;
	}
	public void setGeoEntitycode(String geoEntitycode) {
		this.geoEntitycode = geoEntitycode;
	}
	public String getClfCode() {
		return clfCode;
	}
	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
	}
	public Long getParentCboid() {
		return parentCboid;
	}
	public void setParentCboid(Long parentCboid) {
		this.parentCboid = parentCboid;
	}
	public int getParentCbotype() {
		return parentCbotype;
	}
	public void setParentCbotype(int parentCbotype) {
		this.parentCbotype = parentCbotype;
	}
	public String getPgCode() {
		return pgCode;
	}
	public void setPgCode(String pgCode) {
		this.pgCode = pgCode;
	}
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
	
	
	

}
