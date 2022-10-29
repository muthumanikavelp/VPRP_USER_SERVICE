package com.vprp.user.payload;

import java.util.Date;

public class CreateUserPwdPayload {
	
	private Long Id;
	private Long userId;
	private int passwordSlno;
	private String password;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private Character currentpwdflag;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	@Override
	public String toString() {
		return "CreateUserPwdPayload [userId=" + userId + ", passwordSlno=" + passwordSlno + ", password=" + password
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", currentpwdflag=" + currentpwdflag
				+ "]";
	}
	
	

}
