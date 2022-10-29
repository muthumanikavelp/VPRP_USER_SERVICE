package com.vprp.user.dto;

import javax.persistence.*;

public class ApplicationPermissionsDto {

	private long id;
	
	private String permissionName;
	
	private String actionsPermitted;
	
	
	

	public ApplicationPermissionsDto(long id, String permissionName, String actionsPermitted) {
		super();
		this.id = id;
		this.permissionName = permissionName;
		this.actionsPermitted = actionsPermitted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getActionsPermitted() {
		return actionsPermitted;
	}

	public void setActionsPermitted(String actionsPermitted) {
		this.actionsPermitted = actionsPermitted;
	}
	
	
	

	
}
