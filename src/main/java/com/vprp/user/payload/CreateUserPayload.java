package com.vprp.user.payload;

import java.util.Date;
import java.util.List;

public class CreateUserPayload {

	private Long Id;
	private String designation;
	private String userName;
	private String loginId;
	private String email;
	private String mobNumPrimary;
	private String mobNumSecondary;
	private String password;
	private String status;
	private Character pwdresetflag;
	private Character pwdexpireflag;
	private List<RolesPayload> roles;
	
	

	public CreateUserPayload() {
		super();
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobNumPrimary() {
		return mobNumPrimary;
	}

	public void setMobNumPrimary(String mobNumPrimary) {
		this.mobNumPrimary = mobNumPrimary;
	}

	public String getMobNumSecondary() {
		return mobNumSecondary;
	}

	public void setMobNumSecondary(String mobNumSecondary) {
		this.mobNumSecondary = mobNumSecondary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RolesPayload> getRoles() {
		return roles;
	}

	public void setRoles(List<RolesPayload> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emaill) {
		this.email = emaill;
	}
	
	

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public Character getPwdresetflag() {
		return pwdresetflag;
	}

	public void setPwdresetflag(Character pwdresetflag) {
		this.pwdresetflag = pwdresetflag;
	}

	@Override
	public String toString() {
		return "CreateUserPayload [designation=" + designation + ", userName=" + userName + ", loginId=" + loginId
				+ ", emaill=" + email + ", mobNumPrimary=" + mobNumPrimary + ", mobNumSecondary=" + mobNumSecondary
				+ ", password=" + password + ", roles=" + roles + "]";
	}
	
	
	//private Long Id;
	private Long userId;
	private int passwordSlno;
	//private String password;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private Character currentpwdflag;



	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getPasswordSlno() {
		return passwordSlno;
	}

	public void setPasswordSlno(int passwordSlno) {
		this.passwordSlno = passwordSlno;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Character getCurrentpwdflag() {
		return currentpwdflag;
	}

	public void setCurrentpwdflag(Character currentpwdflag) {
		this.currentpwdflag = currentpwdflag;
	}

	public Character getPwdexpireflag() {
		return pwdexpireflag;
	}

	public void setPwdexpireflag(Character pwdexpireflag) {
		this.pwdexpireflag = pwdexpireflag;
	}
	
	
	
	
//	@Override
//	public String toString() {
//		return "CreateUserPwdPayload [userId=" + userId + ", passwordSlno=" + passwordSlno + ", password=" + password
//				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", currentpwdflag=" + currentpwdflag
//				+ "]";
//	}
}
