package com.vprp.user.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vprp.user.dto.ApplicationRolesDto;

@Entity
@Table(name = "user_cbos", schema = "nrlm_security")
public class UserCBOs implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cbo_level")
	private String cboLevel;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "cbo_id", columnDefinition = "int[]")
	@Type(type = "com.vprp.user.model.CustomIntegerArrayType")
	private Integer[] cboId;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "application_role_id")
	private Long applicationRoleId;

	@Column(name = "status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private ApplicationRolesDto applicationRolesDto;

//	@JsonProperty("applicationRoles")
//	@XmlElement(required = true)
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "application_role_id", insertable = false, updatable = false)
//	@Fetch(FetchMode.JOIN)
//	private ApplicationRoles applicationRole;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private User user;

	public ApplicationRolesDto getApplicationRolesDto() {
		return applicationRolesDto;
	}

	public void setApplicationRolesDto(ApplicationRolesDto applicationRolesDto) {
		this.applicationRolesDto = applicationRolesDto;
	}

	public UserCBOs() {
	}

	public UserCBOs(Long userId, Integer[] cboId, String cboLevel, Date fromDate, Date toDate, Long applicationRoleId) {
		super();
		this.userId = userId;
		this.cboLevel = cboLevel;
		this.cboId = cboId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.applicationRoleId = applicationRoleId;
	}

	public UserCBOs(Long userId, String cboLevel, Integer[] cboId, Date fromDate, Date toDate, Long applicationRoleId,
			String status) {
		super();
		this.cboLevel = cboLevel;
		this.userId = userId;
		this.cboId = cboId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.applicationRoleId = applicationRoleId;
		this.status = status;
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

	public String getCboLevel() {
		return cboLevel;
	}

	public void setCboLevel(String cboLevel) {
		this.cboLevel = cboLevel;
	}

	public Integer[] getCboId() {
		return cboId;
	}

	public void setCboId(Integer[] cboId) {
		this.cboId = cboId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public ApplicationRoles getApplicationRoles() {
//		return applicationRoles;
//	}
//
//
//	public void setApplicationRoles(ApplicationRoles applicationRoles) {
//		this.applicationRoles = applicationRoles;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserCBOs [userId=" + userId + ", cboLevel=" + cboLevel + ", cboId=" + cboId + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ",applicationRoleId=" + applicationRoleId + "]";
	}

}
