package com.vprp.user.dto;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import com.vprp.user.model.CustomIntegerArrayType;


public class UserRoleGeographicDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long userId;
	
	private Long state;
	
	private Long district;
	
	private Long block;
	
	private Integer[] grampPanchayat;
	
	private Integer[] village;
	
	private Boolean nationalLevel;

	public UserRoleGeographicDto() {
		
	}

	public UserRoleGeographicDto(Long userId, Long state, Long district, Long block, Integer[] grampPanchayat,
			Integer[] village,
			Boolean nationalLevel) {

		this.state = state;
		this.district = district;
		this.block = block;
		this.grampPanchayat = grampPanchayat;
		this.village = village;
		this.nationalLevel = nationalLevel;
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public Boolean getNationalLevel() {
		return nationalLevel;
	}

	public void setNationalLevel(Boolean nationalLevel) {
		this.nationalLevel = nationalLevel;
	}

	public Integer[] getGrampPanchayat() {
		return grampPanchayat;
	}

	public void setGrampPanchayat(Integer[] grampPanchayat) {
		this.grampPanchayat = grampPanchayat;
	}

	public Integer[] getVillage() {
		return village;
	}

	public void setVillage(Integer[] village) {
		this.village = village;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", state=" + state
				+ ", district=" + district + ", block=" + block + ", grampPanchayat=" + grampPanchayat + ", village="
				+ village + "]";
	}
	
	
	
	
}
