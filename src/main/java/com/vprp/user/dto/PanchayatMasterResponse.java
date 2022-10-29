package com.vprp.user.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class PanchayatMasterResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private long panchayatId;

	private Long panchayatCode;

	private String panchayatNameEn;

	private long stateId;

	private long districtId;

	private long blockId;

	public PanchayatMasterResponse(long panchayatId, Long panchayatCode, String panchayatNameEn, long stateId,
			long districtId, long blockId) {
		super();
		this.panchayatId = panchayatId;
		this.panchayatCode = panchayatCode;
		this.panchayatNameEn = panchayatNameEn;
		this.stateId = stateId;
		this.districtId = districtId;
		this.blockId = blockId;
	}

	public long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public Long getPanchayatCode() {
		return panchayatCode;
	}

	public void setPanchayatCode(Long panchayatCode) {
		this.panchayatCode = panchayatCode;
	}

	public String getPanchayatNameEn() {
		return panchayatNameEn;
	}

	public void setPanchayatNameEn(String panchayatNameEn) {
		this.panchayatNameEn = panchayatNameEn;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public long getBlockId() {
		return blockId;
	}

	public void setBlockId(long blockId) {
		this.blockId = blockId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
