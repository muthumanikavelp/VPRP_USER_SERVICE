package com.vprp.user.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "village_master", schema="nrlm_master")
public class VillageMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "village_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long villageId;
	
	@Column(name = "village_code")
	private Long villageCode;	
	
	@Column(name = "block_id")
	private Long blockId;	
	
	@Column(name = "village_name_en")
	private String villageNameEn;
	
	@Column(name = "state_id")
	private long stateId;
	
	@Column(name = "district_id")
	private long districtId;
	
	@Column(name = "panchayat_id")
	private long panchayatId;
	
	public long getPanchayatId() {
		return panchayatId;
	}




	public void setPanchayatId(long panchayatId) {
		this.panchayatId = panchayatId;
	}


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
	

	@JsonIgnore
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id", insertable = false, updatable = false, referencedColumnName="panchayat_id")
	@Fetch(FetchMode.JOIN)
	private PanchayatMaster panchayatMaster;

	

	public VillageMaster() {
		super();
	}




	public VillageMaster(long villageId, Long villageCode, Long blockId, String villageNameEn, long stateId,
			long districtId, long panchayatId, Boolean isActive, Date createdDate, Long createdBy, Date updatedDate,
			Long updatedBy, StateMaster stateMaster, DistrictMaster districtMaster, BlockMaster blockMaster,
			PanchayatMaster panchayatMaster) {
		super();
		this.villageId = villageId;
		this.villageCode = villageCode;
		this.blockId = blockId;
		this.villageNameEn = villageNameEn;
		this.stateId = stateId;
		this.districtId = districtId;
		this.panchayatId = panchayatId;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.stateMaster = stateMaster;
		this.districtMaster = districtMaster;
		this.blockMaster = blockMaster;
		this.panchayatMaster = panchayatMaster;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public long getVillageId() {
		return villageId;
	}

	public void setVillageId(long villageId) {
		this.villageId = villageId;
	}

	public Long getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(Long villageCode) {
		this.villageCode = villageCode;
	}

	public String getVillageNameEn() {
		return villageNameEn;
	}

	public void setVillageNameEn(String villageNameEn) {
		this.villageNameEn = villageNameEn;
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
