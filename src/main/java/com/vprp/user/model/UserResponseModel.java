package com.vprp.user.model;

import java.util.List;

import com.vprp.user.dto.AppRoleLoginDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.User;
import com.vprp.user.entity.UserCBOs;
import com.vprp.user.payload.CreateUserPayload;

import lombok.Getter;
import lombok.Setter;

public class UserResponseModel extends CommonResponse{
	private User data;
	private CreateUserPayload cratedUser;
	private UserCBOsDto userInfo;
	private String token;
	private AppRoleLoginDto application;

	public CreateUserPayload getCratedUser() {
		return cratedUser;
	}

	public void setCratedUser(CreateUserPayload cratedUser) {
		this.cratedUser = cratedUser;
	}

	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}



	public UserCBOsDto getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserCBOsDto userInfo) {
		this.userInfo = userInfo;
	}

	public String getToken() {
		return token;
	}

	public AppRoleLoginDto getApplication() {
		return application;
	}

	public void setApplication(AppRoleLoginDto application) {
		this.application = application;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
