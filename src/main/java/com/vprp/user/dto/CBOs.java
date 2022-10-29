package com.vprp.user.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vprp.user.dto.ApplicationRolesDto;
import com.vprp.user.model.CustomIntegerArrayType;

public class CBOs implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String cboLevel;
	
	private Long userId;
	
	private Integer[] cboId;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Long applicationRoleId;
	
	public CBOs() {
		
	}

	
	public CBOs(Long userId, Integer[] cboId, String cboLevel, Date fromDate, Date toDate,
			Long applicationRoleId) {
		super();
		this.userId = userId;
		this.cboLevel = cboLevel;
		this.cboId = cboId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.applicationRoleId = applicationRoleId;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getApplicationRoleId() {
		return applicationRoleId;
	}

	public void setApplicationRoleId(Long applicationRoleId) {
		this.applicationRoleId = applicationRoleId;
	}

	public String getCboLevel() {
		return cboLevel;
	}

	public void setCboLevel(String cboLevel) {
		this.cboLevel = cboLevel;
	}

	public Integer[] getCboId() {
		return cboId;
	}

	public void setCboId(Integer[] cboId) {
		this.cboId = cboId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "UserCBOs [userId=" + userId + ", cboLevel=" + cboLevel + ", cboId=" + cboId + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ",applicationRoleId=" + applicationRoleId + "]";
	}
	
	
	
}
