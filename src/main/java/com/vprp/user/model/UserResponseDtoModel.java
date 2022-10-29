package com.vprp.user.model;

import java.io.Serializable;
import java.util.List;

import com.vprp.user.dto.AppRoleLoginDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.dto.UserDto;
import com.vprp.user.entity.User;
import com.vprp.user.entity.UserCBOs;

import lombok.Getter;
import lombok.Setter;

public class UserResponseDtoModel extends CommonResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3148481189208967832L;
	private UserDto data;
	private String token;
	private User userInfo;
	
	

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDto getData() {
		return data;
	}

	public UserResponseDtoModel(UserDto data, String token) {
		super();
		this.data = data;
		this.token = token;
	}

	public UserResponseDtoModel() {
		// TODO Auto-generated constructor stub
	}

	public void setData(UserDto data) {
		this.data = data;
	}
	
	

}
