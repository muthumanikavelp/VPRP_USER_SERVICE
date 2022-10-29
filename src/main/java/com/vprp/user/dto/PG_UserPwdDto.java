package com.vprp.user.dto;

import java.io.Serializable;

public class PG_UserPwdDto implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String password;
	
	private String passwordSlno;
	
	private long userId;
	
	
	public PG_UserPwdDto(long id, String password, String passwordSlno, long userId) {
		super();
		this.id = id;
		this.password = password;
		this.passwordSlno = passwordSlno;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSlno() {
		return passwordSlno;
	}

	public void setPasswordSlno(String passwordSlno) {
		this.passwordSlno = passwordSlno;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
    
}
