package com.vprp.user.entity;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vprp.user.dto.ApplicationRolesDto;

@Entity
@Table(name = "user_role", schema = "nrlm_security")
public class UserRoles implements Serializable{	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "application_role_id")
	private Long applicationRoleId;
	
	@Column(name = "status")
	private String userStatus;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private User user;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	private ApplicationRolesDto applicationRoleDto;
	
	

	public UserRoles() {

	}
	public UserRoles(Long userId, Long applicationRoleId, String userStatus) {
		this.userId = userId;
		this.applicationRoleId = applicationRoleId;
		this.userStatus = userStatus;
	}

	public long getId() {
		return id;
	}

	public void setApplicationRoleDto(ApplicationRolesDto applicationRoleDto) {
		this.applicationRoleDto = applicationRoleDto;
	}

	public ApplicationRolesDto getApplicationRole() {
		return this.applicationRoleDto;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getApplicationRoleId() {
		return applicationRoleId;
	}

	public void setApplicationRoleId(Long applicationRoleId) {
		this.applicationRoleId = applicationRoleId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserRoles [id=" + id + ", userId=" + userId + ", applicationRoleId=" + applicationRoleId
				+ ", userStatus=" + userStatus + "]";
	}	
	
	
}
