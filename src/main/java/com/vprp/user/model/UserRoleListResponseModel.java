package com.vprp.user.model;

import java.util.List;
import com.vprp.user.entity.UserCBOs;
import com.vprp.user.entity.UserRoleGeography;
import com.vprp.user.entity.UserRoles;

import lombok.Getter;
import lombok.Setter;

public class UserRoleListResponseModel extends CommonResponse{
	
	private UserRoles data;

	public UserRoles getData() {
		return data;
	}

	public void setData(UserRoles data) {
		this.data = data;
	}

}
