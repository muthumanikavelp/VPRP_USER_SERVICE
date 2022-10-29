package com.vprp.user.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vprp.user.entity.DistrictMaster;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DistrictListDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private long districtId;
	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	private String districtName;
	private long stateId;
	
	public DistrictListDto(long districtId, String districtName, long stateId) {
		super();
		this.districtId = districtId;
		this.districtName = districtName;
		this.stateId = stateId;
	}
	
}