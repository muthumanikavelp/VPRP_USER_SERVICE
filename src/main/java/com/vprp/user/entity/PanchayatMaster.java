package com.vprp.user.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "panchayat_master", schema="nrlm_master")
public class PanchayatMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "panchayat_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long panchayatId;
	
	@Column(name = "panchayat_code")
	private Long panchayatCode;	
	
	@Column(name = "panchayat_name_en")
	private String panchayatNameEn;
	
	@Column(name = "state_id")
	private long stateId;
	
	@Column(name = "district_id")
	private long districtId;
	
	@Column(name = "block_id")
	private long blockId;	
	
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false, referencedColumnName="state_id")
	@Fetch(FetchMode.JOIN)
	private StateMaster stateMaster;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false, referencedColumnName="district_id")
	@Fetch(FetchMode.JOIN)
	private DistrictMaster districtMaster;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "block_id", insertable = false, updatable = false, referencedColumnName="block_id")
	@Fetch(FetchMode.JOIN)
	private BlockMaster blockMaster;
	
	@JsonManagedReference
	@OneToMany(targetEntity = VillageMaster.class, mappedBy = "panchayatMaster", orphanRemoval = false, fetch = FetchType.LAZY)
	private Set<VillageMaster> villageMaster;

	
	
	public PanchayatMaster() {
		super();
	}



	public PanchayatMaster(long panchayatId, Long panchayatCode, String panchayatNameEn, long stateId, long districtId,
			long blockId, Boolean isActive, Date createdDate, Long createdBy, Date updatedDate, Long updatedBy,
			StateMaster stateMaster, DistrictMaster districtMaster, BlockMaster blockMaster,
			Set<VillageMaster> villageMaster) {
		super();
		this.panchayatId = panchayatId;
		this.panchayatCode = panchayatCode;
		this.panchayatNameEn = panchayatNameEn;
		this.stateId = stateId;
		this.districtId = districtId;
		this.blockId = blockId;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.stateMaster = stateMaster;
		this.districtMaster = districtMaster;
		this.blockMaster = blockMaster;
		this.villageMaster = villageMaster;
	}



	public long getPanchayatId() {
		return panchayatId;
	}



	public void setPanchayatId(long panchayatId) {
		this.panchayatId = panchayatId;
	}



	public Long getPanchayatCode() {
		return panchayatCode;
	}



	public void setPanchayatCode(Long panchayatCode) {
		this.panchayatCode = panchayatCode;
	}



	public String getPanchayatNameEn() {
		return panchayatNameEn;
	}



	public void setPanchayatNameEn(String panchayatNameEn) {
		this.panchayatNameEn = panchayatNameEn;
	}

	public long getBlockId() {
		return blockId;
	}

	public void setBlockId(long blockId) {
		this.blockId = blockId;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
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

	public StateMaster getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	public DistrictMaster getDistrictMaster() {
		return districtMaster;
	}

	public void setDistrictMaster(DistrictMaster districtMaster) {
		this.districtMaster = districtMaster;
	}

	public BlockMaster getBlockMaster() {
		return blockMaster;
	}

	public void setBlockMaster(BlockMaster blockMaster) {
		this.blockMaster = blockMaster;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
