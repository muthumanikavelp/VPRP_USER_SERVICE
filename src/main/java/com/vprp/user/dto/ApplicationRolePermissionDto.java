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
public class ApplicationRolePermissionDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String permissions;
	
	

	public ApplicationRolePermissionDto(String permissions) {
		super();
		this.permissions = permissions;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}