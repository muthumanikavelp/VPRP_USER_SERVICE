package com.vprp.user.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "application", schema = "nrlm_security")
public class Applications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "application_code")
	private String applicationCode;
	
	@Column(name = "application_name")
	private String applicationName;
	
	@Column(name = "application_description")
	private String applicationDescription;
	
	@Column(name = "status")
	private String status;
	
	@JsonManagedReference
	@OneToMany(targetEntity = ApplicationRoles.class, mappedBy = "applicationMaster", orphanRemoval = false, fetch = FetchType.LAZY)
	private Set<ApplicationRoles> applicationRole;

	public Applications() {
		
	}
	
	

	public Set<ApplicationRoles> getApplicationRole() {
		return applicationRole;
	}



	public void setApplicationRole(Set<ApplicationRoles> applicationRole) {
		this.applicationRole = applicationRole;
	}



	public Applications(long id, String applicationCode, String applicationName, String applicationDescription,
			String status) {
		this.id = id;
		this.applicationCode = applicationCode;
		this.applicationName = applicationName;
		this.applicationDescription = applicationDescription;
		this.status = status;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
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


	
}
