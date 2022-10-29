package com.vprp.user.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;



@Entity
@Table(name = "vo_profile", schema="nrlm_master")
public class VoMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "vo_code_no")
	private Long voCodeNo;

	@Column(name = "vo_code")
	private String voCode;

	@Column(name = "gp_code")
	private String gpCode;

	@Column(name = "vo_name")
	private String voName;



	public VoMaster() {
	}

	public VoMaster(Long voCodeNo, String voCode, String gpCode, String voName) {
		super();
		this.voCodeNo = voCodeNo;
		this.voCode = voCode;
		this.gpCode = gpCode;
		this.voName = voName;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getVoName() {
		return voName;
	}

	public void setVoName(String voName) {
		this.voName = voName;
	}

	public Long getVoCodeNo() {
		return voCodeNo;
	}

	public void setVoCodeNo(Long voCodeNo) {
		this.voCodeNo = voCodeNo;
	}

	public String getVoCode() {
		return voCode;
	}

	public void setVoCode(String voCode) {
		this.voCode = voCode;
	}

	public String getGpCode() {
		return gpCode;
	}

	public void setGpCode(String gpCode) {
		this.gpCode = gpCode;
	}


	
}
