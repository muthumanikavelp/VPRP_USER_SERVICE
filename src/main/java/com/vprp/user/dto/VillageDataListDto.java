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
public class VillageDataListDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private long villagetId;
	private String villageName;
	
	
	
	public VillageDataListDto(long villagetId, String villageName) {
		super();
		this.villagetId = villagetId;
		this.villageName = villageName;
	}
	public long getVillagetId() {
		return villagetId;
	}
	public void setVillagetId(long villagetId) {
		this.villagetId = villagetId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}