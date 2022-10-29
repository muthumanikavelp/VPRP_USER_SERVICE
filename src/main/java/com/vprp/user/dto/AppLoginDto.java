package com.vprp.user.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vprp.user.entity.DistrictMaster;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppLoginDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long appId;
	private String appCode;
	private String appName;
	private String appDesc;
	private String appStatus;
	
	
	
	public AppLoginDto() {
		super();
	}
	public AppLoginDto(Long appId, String appCode, String appName, String appDesc, String appStatus) {
		super();
		this.appId = appId;
		this.appCode = appCode;
		this.appName = appName;
		this.appDesc = appDesc;
		this.appStatus = appStatus;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
		
}