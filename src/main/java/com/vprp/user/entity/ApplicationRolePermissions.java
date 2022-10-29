package com.vprp.user.entity;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "application_role_permissions", schema = "nrlm_security")
public class ApplicationRolePermissions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "application_role_id")
	private Long applicationRoleId;
	
	@Column(name = "application_permission_id")
	private Long applicationPermissionId;
	
	@Column(name = "actions_permitted")
	private String actionPermitted;
	
	
	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "application_role_id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private ApplicationRoles applicationRolesMaster;



	public ApplicationRolePermissions(long id, Long applicationRoleId, Long applicationPermissionId,
			String actionPermitted, ApplicationRoles applicationRolesMaster) {
		super();
		this.id = id;
		this.applicationRoleId = applicationRoleId;
		this.applicationPermissionId = applicationPermissionId;
		this.actionPermitted = actionPermitted;
		this.applicationRolesMaster = applicationRolesMaster;
	}

	public Long getApplicationRoleId() {
		return applicationRoleId;
	}

	public void setApplicationRoleId(Long applicationRoleId) {
		this.applicationRoleId = applicationRoleId;
	}

	public ApplicationRoles getApplicationRolesMaster() {
		return applicationRolesMaster;
	}

	public void setApplicationRolesMaster(ApplicationRoles applicationRolesMaster) {
		this.applicationRolesMaster = applicationRolesMaster;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Long getApplicationPermissionId() {
		return applicationPermissionId;
	}

	public void setApplicationPermissionId(Long applicationPermissionId) {
		this.applicationPermissionId = applicationPermissionId;
	}

	public String getActionPermitted() {
		return actionPermitted;
	}

	public void setActionPermitted(String actionPermitted) {
		this.actionPermitted = actionPermitted;
	}
	
	
	

	
}
