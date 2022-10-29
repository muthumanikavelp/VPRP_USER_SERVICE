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
public class StateListDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private long stateId;
	private String stateName;
	
	public StateListDto(long stateId, String stateName) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
	}
	
	
	
	
}