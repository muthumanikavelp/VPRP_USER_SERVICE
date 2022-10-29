package com.vprp.user.entity;

import javax.persistence.*;

@Entity
@Table(name = "application_permissions", schema = "nrlm_security")
public class ApplicationPermissions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "application_id")
	private Long applicationId;
	
	@Column(name = "permission_name")
	private String permissionName;

	public ApplicationPermissions() {
		
	}

	public ApplicationPermissions(Long applicationId, String permissionName) {
		this.applicationId = applicationId;
		this.permissionName = permissionName;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@Override
	public String toString() {
		return "ApplicationPermissions [id=" + id + ", applicationId=" + applicationId + ", permissionName="
				+ permissionName + "]";
	}



	
}
