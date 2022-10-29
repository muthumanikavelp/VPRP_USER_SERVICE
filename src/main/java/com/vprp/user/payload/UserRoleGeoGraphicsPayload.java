package com.vprp.user.payload;

import org.hibernate.annotations.Type;

public class UserRoleGeoGraphicsPayload {
	private Boolean nationalLevel;
	private Long state;
	private Long district;
	private Long block;
	@Type(type = "com.vprp.user.model.CustomIntegerArrayType")
	private Integer[] grampPanchayat;
	@Type(type = "com.vprp.user.model.CustomIntegerArrayType")
	private Integer[] village;
	
	
	
	

	public UserRoleGeoGraphicsPayload() {
		super();
	}



	public UserRoleGeoGraphicsPayload(Long state, Long district, Long block, Integer[] grampPanchayat) {
		super();
		this.state = state;
		this.district = district;
		this.block = block;
		this.grampPanchayat = grampPanchayat;
	}
	
	

	public UserRoleGeoGraphicsPayload(Boolean nationalLevel, Long state, Long district, Long block,
			Integer[] grampPanchayat, Integer[] village) {
		super();
		this.nationalLevel = nationalLevel;
		this.state = state;
		this.district = district;
		this.block = block;
		this.grampPanchayat = grampPanchayat;
		this.village = village;
	}


	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public Long getBlock() {
		return block;
	}

	public void setBlock(Long block) {
		this.block = block;
	}

	public Integer[] getGrampPanchayat() {
		return grampPanchayat;
	}

	public void setGrampPanchayat(Integer[] grampPanchayat) {
		this.grampPanchayat = grampPanchayat;
	}

	public Boolean getNationalLevel() {
		return nationalLevel;
	}

	public void setNationalLevel(Boolean nationalLevel) {
		this.nationalLevel = nationalLevel;
	}

	public Integer[] getVillage() {
		return village;
	}

	public void setVillage(Integer[] village) {
		this.village = village;
	}

}
