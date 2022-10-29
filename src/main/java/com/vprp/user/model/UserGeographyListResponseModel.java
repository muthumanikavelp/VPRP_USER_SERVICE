package com.vprp.user.model;

import java.util.List;
import com.vprp.user.entity.UserCBOs;
import com.vprp.user.entity.UserRoleGeography;

import lombok.Getter;
import lombok.Setter;

public class UserGeographyListResponseModel extends CommonResponse{
	
	private List<UserRoleGeography> data;

	public List<UserRoleGeography> getData() {
		return data;
	}

	public void setData(List<UserRoleGeography> data) {
		this.data = data;
	}

}
