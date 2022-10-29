package com.vprp.user.dto;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.vprp.user.dto.ApplicationsDto;
import com.vprp.user.entity.Applications;





public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private String loginId;
	
	private String userName;
	
	private String mobNumPrimary;
	
	private String status;
	
	private String password;
	
	private List<ApplicationsDto> applications;
	
	
	

	public UserDto(long id, String loginId, String userName, String mobNumPrimary, String status, String password) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.userName = userName;
		this.mobNumPrimary = mobNumPrimary;
		this.status = status;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobNumPrimary() {
		return mobNumPrimary;
	}

	public void setMobNumPrimary(String mobNumPrimary) {
		this.mobNumPrimary = mobNumPrimary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ApplicationsDto> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationsDto> applications) {
		this.applications = applications;
	}

	
	
	
	
}


