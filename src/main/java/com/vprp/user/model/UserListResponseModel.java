package com.vprp.user.model;
import java.util.List;

import com.vprp.user.entity.User;

import lombok.Getter;
import lombok.Setter;

public class UserListResponseModel extends CommonResponse{
	private List<User> data;

	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}
	
}
