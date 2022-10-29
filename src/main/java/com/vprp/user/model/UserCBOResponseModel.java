package com.vprp.user.model;

import java.util.List;


import com.vprp.user.entity.UserCBOs;

import lombok.Getter;
import lombok.Setter;

public class UserCBOResponseModel extends CommonResponse{
	private List<UserCBOs> data;

	public List<UserCBOs> getData() {
		return data;
	}

	public void setData(List<UserCBOs> data) {
		this.data = data;
	}

	private UserCBOs cboData;

	public UserCBOs getCboData() {
		return cboData;
	}

	public void setCboData(UserCBOs cboData) {
		this.cboData = cboData;
	}

}
