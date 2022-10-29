package com.vprp.user.model;

import java.util.List;
import com.vprp.user.entity.UserCBOs;

import lombok.Getter;
import lombok.Setter;

public class UserCBOListResponseModel extends CommonResponse{
	
	private List<UserCBOs> data;

	public List<UserCBOs> getData() {
		return data;
	}

	public void setData(List<UserCBOs> data) {
		this.data = data;
	}

}
