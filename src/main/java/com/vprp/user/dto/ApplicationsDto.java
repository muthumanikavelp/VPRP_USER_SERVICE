package com.vprp.user.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;


public class ApplicationsDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String applicationCode;	
	private String applicationName;
	private String applicationDescription;
	private String status;
	private List<ApplicationRolesDto> roles;
	
	
	
	
	public ApplicationsDto() {
		super();
	}
	public ApplicationsDto(Long id, String applicationCode, String applicationName, String applicationDescription,
			String status) {
		this.id = id;
		this.applicationCode = applicationCode;
		this.applicationName = applicationName;
		this.applicationDescription = applicationDescription;
		this.status = status;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationDescription() {
		return applicationDescription;
	}

	public void setApplicationDescription(String applicationDescription) {
		this.applicationDescription = applicationDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public List<ApplicationRolesDto> getRoles() {
		return roles;
	}
	public void setRoles(List<ApplicationRolesDto> roles) {
		this.roles = roles;
	}
	
	
}
