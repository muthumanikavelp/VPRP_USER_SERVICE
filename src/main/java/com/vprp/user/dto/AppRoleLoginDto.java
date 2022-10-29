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
public class AppRoleLoginDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long applicationid;
	private Long appRoleId;
	private String rolename;
	private String actionspermitted;
	
	private List<String> roles;
	
	

	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public AppRoleLoginDto(Long applicationid, Long appRoleId, String roleDescription, String actionspermitted) {
		super();
		this.applicationid = applicationid;
		this.appRoleId = appRoleId;
		this.roleDescription = roleDescription;
		this.actionspermitted = actionspermitted;
	}
	public Long getAppRoleId() {
		return appRoleId;
	}
	public void setAppRoleId(Long appRoleId) {
		this.appRoleId = appRoleId;
	}
	public Long getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(Long applicationid) {
		this.applicationid = applicationid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getActionspermitted() {
		return actionspermitted;
	}
	public void setActionspermitted(String actionspermitted) {
		this.actionspermitted = actionspermitted;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	/*PG Application Start*/
	private String roleDescription;
	public String getRoledesc() {
		return roleDescription;
	}
	public void setRoledesc(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	public AppRoleLoginDto(Long applicationid, Long appRoleId, String rolename, String roleDescription, String actionspermitted) {
		super();
		this.applicationid = applicationid;
		this.appRoleId = appRoleId;
		this.rolename = rolename;
		this.roleDescription = roleDescription;
		this.actionspermitted = actionspermitted;
	}
	
	/*PG Application End*/
	
	
	
}