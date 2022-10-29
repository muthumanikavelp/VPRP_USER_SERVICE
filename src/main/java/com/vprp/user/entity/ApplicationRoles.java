package com.vprp.user.entity;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "application_roles", schema = "nrlm_security")
public class ApplicationRoles implements Serializable{	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "application_id")
	private Long applicationId;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "role_description")
	private String roleDescription;
	
	@Column(name = "map_to_geographies")
	private Boolean mapToGeographics;
	
	@Column(name = "geography_levels_permitted")
	private String geographyLevelsPermitted;
	
	@Column(name = "map_to_cbos")
	private Boolean mapToCbos;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "cbo_levels_permitted", columnDefinition = "Integer[]")
	@Type(type = "com.vprp.user.model.CustomIntegerArrayType")
	private Integer[] cbosLevelsPermitted;

	@Column(name = "cbo_assignment_rule")
	private Integer cboAssignmentRule;

	public Integer getCboAssignmentRule() {
		return cboAssignmentRule;
	}


	public void setCboAssignmentRule(Integer cboAssignmentRule) {
		this.cboAssignmentRule = cboAssignmentRule;
	}


	public Set<ApplicationRolePermissions> getApplicationRolesPermission() {
		return applicationRolesPermission;
	}


	public void setApplicationRolesPermission(Set<ApplicationRolePermissions> applicationRolesPermission) {
		this.applicationRolesPermission = applicationRolesPermission;
	}

	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "application_id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Applications applicationMaster;
	
	@JsonManagedReference
	@OneToMany(targetEntity = ApplicationRolePermissions.class, mappedBy = "applicationRolesMaster", orphanRemoval = false, fetch = FetchType.LAZY)
	private Set<ApplicationRolePermissions> applicationRolesPermission;

//	@OneToOne(mappedBy = "applicationRole", cascade = CascadeType.ALL)
//	@PrimaryKeyJoinColumn
//	private UserCBOs userCBOs;



	public ApplicationRoles(long id, Long applicationId, String roleName,
			Boolean mapToGeographics, String geographyLevelsPermitted, Boolean mapToCbos, String status,
			Integer[] cbosLevelsPermitted) {
		super();
		this.id = id;
		this.applicationId = applicationId;
		this.roleName = roleName;
		//this.roleDescription = roleDescription;
		this.mapToGeographics = mapToGeographics;
		this.geographyLevelsPermitted = geographyLevelsPermitted;
		this.mapToCbos = mapToCbos;
		this.status = status;
		this.cbosLevelsPermitted = cbosLevelsPermitted;
	}

	
	public ApplicationRoles() {
		super();
	}


	public Applications getApplicationMaster() {
		return applicationMaster;
	}


	public void setApplicationMaster(Applications applicationMaster) {
		this.applicationMaster = applicationMaster;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Boolean getMapToGeographics() {
		return mapToGeographics;
	}

	public void setMapToGeographics(Boolean mapToGeographics) {
		this.mapToGeographics = mapToGeographics;
	}

	public String getGeographyLevelsPermitted() {
		return geographyLevelsPermitted;
	}

	public void setGeographyLevelsPermitted(String geographyLevelsPermitted) {
		this.geographyLevelsPermitted = geographyLevelsPermitted;
	}

	public Boolean getMapToCbos() {
		return mapToCbos;
	}

	public void setMapToCbos(Boolean mapToCbos) {
		this.mapToCbos = mapToCbos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer[] getCbosLevelsPermitted() {
		return cbosLevelsPermitted;
	}

	public void setCbosLevelsPermitted(Integer[] cbosLevelsPermitted) {
		this.cbosLevelsPermitted = cbosLevelsPermitted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
