package com.vprp.user.payload;

import org.hibernate.annotations.Type;

public class UserCBOsPayload {

	
	
	public UserCBOsPayload() {
		super();
	}

	private String cboLevel;
	
	@Type(type = "com.vprp.user.model.CustomIntegerArrayType")
	private Integer[] cboIds;

	public Integer[] getCboIds() {
		return cboIds;
	}

	public void setCboIds(Integer[] cboIds) {
		this.cboIds = cboIds;
	}

	public String getCboLevel() {
		return cboLevel;
	}

	public void setCboLevel(String cboLevel) {
		this.cboLevel = cboLevel;
	}
	

}
