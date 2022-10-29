package com.vprp.user.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vprp.user.entity.DistrictMaster;
import com.vprp.user.entity.UserRoleGeography;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationRolesDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long applicationId;
	private long roleId;
	private String roleName;
	private String geoLevelPermitted;
	private Integer[] cbosLevelsPermitted;
	private Boolean mapToGeographics;
	private Boolean mapToCbos;
	private String status;
	private UserRoleGeography roleGeos;
	public UserRoleGeography getRoleGeos() {
		return roleGeos;
	}


	public void setRoleGeos(UserRoleGeography roleGeos) {
		this.roleGeos = roleGeos;
	}
	private List<CBOs> rolesCBO;
	private List<ApplicationPermissionsDto>  permissions;
	
	public List<ApplicationPermissionsDto> getPermissions() {
		return permissions;
	}
	
	
	public ApplicationRolesDto() {
		super();
	}


	public void setPermissions(List<ApplicationPermissionsDto> permissions) {
		this.permissions = permissions;
	}
	public ApplicationRolesDto(long applicationId, String roleName) {
		super();
		this.applicationId = applicationId;
		this.roleName = roleName;
	}
	
	public ApplicationRolesDto(long applicationId, String roleName, String roleDescription, String geoLevelPermitted, Boolean mapToGeographics) {
		super();
		this.applicationId = applicationId;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.geoLevelPermitted = geoLevelPermitted;
		this.mapToGeographics = mapToGeographics;
	}
	public ApplicationRolesDto(long applicationId, long roleId, String roleName, String geoLevelPermitted,
			String status) {
		super();
		this.applicationId = applicationId;
		this.roleId = roleId;
		this.roleName = roleName;
		this.geoLevelPermitted = geoLevelPermitted;
		this.status = status;
	}
	
	public ApplicationRolesDto(long applicationId, long roleId, String roleName, String status) {
		super();
		this.applicationId = applicationId;
		this.roleId = roleId;
		this.roleName = roleName;
		this.status = status;
	}
	public ApplicationRolesDto(long applicationId, long roleId, String roleName,String roleDescription, String geoLevelPermitted,
							   Integer[] cbosLevelsPermitted,boolean mapToCbos,boolean mapToGeographics,  String status) {
		super();
		this.applicationId = applicationId;
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.geoLevelPermitted = geoLevelPermitted;
		this.cbosLevelsPermitted = cbosLevelsPermitted;
		this.mapToCbos = mapToCbos;
		this.mapToGeographics = mapToGeographics;
		this.status = status;
	}
	
	public ApplicationRolesDto(long applicationId, long roleId) {
		super();
		this.applicationId = applicationId;
		this.roleId = roleId;
	}
	public Boolean getMapToGeographics() {
		return mapToGeographics;
	}
	public void setMapToGeographics(Boolean mapToGeographics) {
		this.mapToGeographics = mapToGeographics;
	}
	public Boolean getMapToCbos() {
		return mapToCbos;
	}
	public void setMapToCbos(Boolean mapToCbos) {
		this.mapToCbos = mapToCbos;
	}


	public long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getGeoLevelPermittd() {
		return geoLevelPermitted;
	}
	public void setGeoLevelPermittd(String geoLevelPermittd) {
		this.geoLevelPermitted = geoLevelPermittd;
	}
	public Integer[] getCbosLevelsPermitted(){
		return cbosLevelsPermitted;
	}
	public void setCbosLevelsPermitted(Integer[] cbosLevelsPermitted){
		this.cbosLevelsPermitted = cbosLevelsPermitted;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<CBOs> getRolesCBO() {
		return rolesCBO;
	}
	public void setRolesCBO(List<CBOs> rolesCBO) {
		this.rolesCBO = rolesCBO;
	}
	public String getGeoLevelPermitted() {
		return geoLevelPermitted;
	}
	public void setGeoLevelPermitted(String geoLevelPermitted) {
		this.geoLevelPermitted = geoLevelPermitted;
	}
	
	/*PG Application Start*/
	public String roleDescription;
	public String getRoleDescription() {
		return roleDescription;
	}


	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	/*PG Application End*/
	

}