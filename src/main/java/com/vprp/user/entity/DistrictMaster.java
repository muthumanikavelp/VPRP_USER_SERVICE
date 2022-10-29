package com.vprp.user.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "district_master", schema="nrlm_master")
public class DistrictMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "district_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long districtId;
	
	@Column(name = "state_id")
	private long stateId;
	
	@Column(name = "district_code")
	private Long districtCode;
	
	@Column(name = "district_name_en")
	private String districtNameEn;
	
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "created_date")
	private Date createdDate;
	
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "created_by")
	private Long createdBy;
	
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@JsonBackReference
	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false, referencedColumnName="state_id")
	@Fetch(FetchMode.JOIN)
	private StateMaster stateMaster;
	
	@JsonIgnore
	@JsonManagedReference
	@OneToMany(targetEntity = BlockMaster.class, mappedBy = "districtMaster", orphanRemoval = false, fetch = FetchType.LAZY)
	private Set<BlockMaster> blockMaster;
	
//	@JsonBackReference
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "block_id", insertable = false, updatable = false, referencedColumnName="block_id")
//	@Fetch(FetchMode.JOIN)
//	private BlockMaster blockMaster;
	
	
	


	public DistrictMaster() {
		
	}

	public DistrictMaster(Long districtId, Long districtCode, long stateId, String districtNameEn, Boolean isActive,
			Date createdDate, Long createdBy, Date updatedDate, Long updatedBy, StateMaster stateMaster) {
		super();
		this.districtId = districtId;
		this.districtCode = districtCode;
		this.districtNameEn = districtNameEn;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.stateMaster = stateMaster;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictNameEn() {
		return districtNameEn;
	}

	public void setDistrictNameEn(String districtNameEn) {
		this.districtNameEn = districtNameEn;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
//	public StateMaster getStateMaster() {
//		return stateMaster;
//	}
//
//	public void setStateMaster(StateMaster stateMaster) {
//		this.stateMaster = stateMaster;
//	}
	
}
