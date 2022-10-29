package com.vprp.user.payload;

import java.util.List;

public class RolesPayload {

	private Long applicationRoleId;
	private List<UserRoleGeoGraphicsPayload> userRoleGeographies;
	private List<UserCBOsPayload> userCBOs;

	public Long getApplicationRoleId() {
		return applicationRoleId;
	}

	public void setApplicationRoleId(Long applicationRoleId) {
		this.applicationRoleId = applicationRoleId;
	}

	public List<UserCBOsPayload> getUserCBOs() {
		return userCBOs;
	}

	public void setUserCBOs(List<UserCBOsPayload> userCBOs) {
		this.userCBOs = userCBOs;
	}

	public List<UserRoleGeoGraphicsPayload> getUserRoleGeographies() {
		return userRoleGeographies;
	}

	public void setUserRoleGeographies(List<UserRoleGeoGraphicsPayload> userRoleGeographies) {
		this.userRoleGeographies = userRoleGeographies;
	}

	public RolesPayload() {
		super();
	}
	

}
