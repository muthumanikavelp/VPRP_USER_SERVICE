package com.vprp.user.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;



@Entity
@Table(name = "shg_master", schema="nrlm_master")
public class ShgMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "shg_code")
	private String shgCode;
	
	
	@Column(name = "group_name")
	private String shgName;
	
	
	@Column(name = "shg_type")
	private String shgType;
	

	
	public ShgMaster() {
		super();
	}

	public ShgMaster(String shgCode, String shgName, String shgType) {
		this.shgCode = shgCode;
		this.shgName = shgName;
		this.shgType = shgType;
	}

	@JsonProperty("shgCode")
	public String getShgCode() {
		return shgCode;
	}

	@JsonProperty("shgName")
	public String getShgName() {
		return shgName;
	}

	@JsonProperty("shgType")
	public String getShgType() {
		return shgType;
	}

	
}
