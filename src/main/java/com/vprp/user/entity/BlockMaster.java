package com.vprp.user.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "block_master",  schema="nrlm_master")
public class BlockMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "block_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long blockId;
	
	@Column(name = "block_code")
	private String blockCode;	
	
	@Column(name = "block_name_en")
	private String blockNameEn;
	
	@Column(name = "state_id")
	private long stateId;
	
	@Column(name = "district_id")
	private long district_id;
	
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
	
	@JsonIgnore
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false, referencedColumnName="state_id")
	@Fetch(FetchMode.JOIN)
	private StateMaster stateMaster;
	
	@JsonIgnore
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false, referencedColumnName="district_id")
	@Fetch(FetchMode.JOIN)
	private DistrictMaster districtMaster;
	
	@JsonIgnore
	@JsonManagedReference
	@OneToMany(targetEntity = PanchayatMaster.class, mappedBy = "blockMaster", orphanRemoval = false, fetch = FetchType.LAZY)
	private Set<PanchayatMaster> panchayatMaster;
	

	public BlockMaster() {
		super();
	}

	public BlockMaster(long blockId, String blockCode, String blockNameEn, long stateId, long district_id,
			Boolean isActive, Date createdDate, Long createdBy, Date updatedDate, Long updatedBy,
			StateMaster stateMaster, DistrictMaster districtMaster) {
		super();
		this.blockId = blockId;
		this.blockCode = blockCode;
		this.blockNameEn = blockNameEn;
		this.stateId = stateId;
		this.district_id = district_id;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.stateMaster = stateMaster;
		this.districtMaster = districtMaster;
	}
	
	public BlockMaster(Long blockId, String blockNameEn, String blockCode, Long stateId, Long district_id){
		this.blockId = blockId;
		this.blockNameEn = blockNameEn;
		this.blockCode = blockCode;
		this.stateId = stateId;
		this.district_id = district_id;
	}

	public long getBlockId() {
		return blockId;
	}

	public void setBlockId(long blockId) {
		this.blockId = blockId;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getBlockNameEn() {
		return blockNameEn;
	}

	public void setBlockNameEn(String blockNameEn) {
		this.blockNameEn = blockNameEn;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public long getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(long district_id) {
		this.district_id = district_id;
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
	
	
	
	
	
}
