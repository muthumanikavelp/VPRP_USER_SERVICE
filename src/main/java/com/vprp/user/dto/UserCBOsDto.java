package com.vprp.user.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCBOsDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String userName;
	private String mobileNumPrimay;
	private String mobileNumSecondary;
	private Long state;
	private Long district;
	private Long block;
	private Long gpInfo;
	private Long village;
	private String cbo4;
	private String cbo3;
	private String cbo2;
	private String cbo1;
	private Long cboId;
	private Integer page;
	
	/*PG Application Start*/
	private String loginid;
	/*PG Application End*/
	
	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public AppRoleLoginDto getAppRoleLoginDto() {
		return appRoleLoginDto;
	}

	public void setAppRoleLoginDto(AppRoleLoginDto appRoleLoginDto) {
		this.appRoleLoginDto = appRoleLoginDto;
	}

	private AppRoleLoginDto appRoleLoginDto;
	
	public UserCBOsDto(Long userId, String userName, String mobileNumPrimay, String mobileNumSecondary) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mobileNumPrimay = mobileNumPrimay;
		this.mobileNumSecondary = mobileNumSecondary;
	}
	
	
	public UserCBOsDto(Long userId, String userName, String mobileNumPrimay, String mobileNumSecondary, String loginId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mobileNumPrimay = mobileNumPrimay;
		this.mobileNumSecondary = mobileNumSecondary;
		this.loginid = loginId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumPrimay() {
		return mobileNumPrimay;
	}

	public void setMobileNumPrimay(String mobileNumPrimay) {
		this.mobileNumPrimay = mobileNumPrimay;
	}

	public String getMobileNumSecondary() {
		return mobileNumSecondary;
	}

	public void setMobileNumSecondary(String mobileNumSecondary) {
		this.mobileNumSecondary = mobileNumSecondary;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public Long getBlock() {
		return block;
	}

	public void setBlock(Long block) {
		this.block = block;
	}

	public Long getGpInfo() {
		return gpInfo;
	}

	public void setGpInfo(Long gpInfo) {
		this.gpInfo = gpInfo;
	}

	public Long getVillage() {
		return village;
	}

	public void setVillage(Long village) {
		this.village = village;
	}

	public String getCbo4() {
		return cbo4;
	}

	public void setCbo4(String cbo4) {
		this.cbo4 = cbo4;
	}

	public String getCbo3() {
		return cbo3;
	}

	public void setCbo3(String cbo3) {
		this.cbo3 = cbo3;
	}

	public String getCbo2() {
		return cbo2;
	}

	public void setCbo2(String cbo2) {
		this.cbo2 = cbo2;
	}

	public String getCbo1() {
		return cbo1;
	}

	public void setCbo1(String cbo1) {
		this.cbo1 = cbo1;
	}

	public Long getCboId() {
		return cboId;
	}

	public void setCboId(Long cboId) {
		this.cboId = cboId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}