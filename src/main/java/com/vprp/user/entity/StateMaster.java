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
@Table(name = "state_master", schema="nrlm_master")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "districtMaster" })
public class StateMaster implements Comparable<StateMaster> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "state_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long stateId;
	
	@Column(name = "state_code")
	private Long stateCode;
	
	
	@Column(name = "state_name_en")
	private String stateNameEn;
	
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
	

	@Getter()
	@OneToMany(targetEntity = DistrictMaster.class, mappedBy = "stateMaster", orphanRemoval = false, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<DistrictMaster> districtMaster;
	
	
//	@JsonManagedReference
//	@OneToMany(targetEntity = BlockMaster.class, mappedBy = "stateMaster", orphanRemoval = false, fetch = FetchType.LAZY)
//	private Set<BlockMaster> blockMaster;
//	
//	@JsonManagedReference
//	@OneToMany(targetEntity = PanchayatMaster.class, mappedBy = "stateMaster", orphanRemoval = false, fetch = FetchType.LAZY)
//	private Set<PanchayatMaster> panchayatMaster;
//	
//	@JsonManagedReference
//	@OneToMany(targetEntity = VillageMaster.class, mappedBy = "stateMaster", orphanRemoval = false, fetch = FetchType.LAZY)
//	private Set<VillageMaster> villageMaster;
	

	public StateMaster() {
	}

	public StateMaster(long stateId, Long stateCode, String stateNameEn, Boolean isActive, Date createdDate,
			Long createdBy, Date updatedDate, Long updatedBy, Set<DistrictMaster> districtMaster) {
		super();
		this.stateId = stateId;
		this.stateCode = stateCode;
		this.stateNameEn = stateNameEn;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.districtMaster = districtMaster;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public Long getStateCode() {
		return stateCode;
	}

	public void setStateCode(Long stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateNameEn() {
		return stateNameEn;
	}

	public void setStateNameEn(String stateNameEn) {
		this.stateNameEn = stateNameEn;
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

	@JsonIgnore
	public Set<DistrictMaster> getDistrictMaster() {
		return districtMaster;
	}

	public void setDistrictMaster(Set<DistrictMaster> districtMaster) {
		this.districtMaster = districtMaster;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int compareTo(StateMaster o) {
		return o.stateNameEn.compareTo(this.stateNameEn);
	}
}
