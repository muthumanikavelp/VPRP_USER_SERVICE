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
public class PanchayatListDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private long panchayatId;
	private String panchayatName;
	
	public PanchayatListDto(long panchayatId, String panchayatName) {
		super();
		this.panchayatId = panchayatId;
		this.panchayatName = panchayatName;
	}

	public long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
}