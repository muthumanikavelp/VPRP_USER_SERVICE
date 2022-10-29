package com.vprp.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entitlement_application", schema = "vprp_master")
public class EntitlementApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ent_id")
	private Long entId;

	@Column(name = "org_id")
	private Long orgId;

	@Column(name = "ent_appl_status")
	private Long entApplStatus;

	@Column(name = "ent_appl_referred_mem_id")
	private Long entApplReferredMemId;

	@Column(name = "ent_appl_multi_benificiary")
	private Boolean entApplMultiBenificiary;

	@Column(name = "ent_appl_fy")
	private String entApplyFy;

	@Column(name = "ent_appl_rank")
	private Long entApplRank;

	@Column(name = "ent_appl_vo_rank")
	private Long entApplVoRank;

	@Column(name = "ent_appl_reason")
	private String entApplRankReason;

	@Column(name = "status")
	private String status;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updateDate;

	@Column(name = "revision_number")
	private Long revisionNumber;

	@Column(name = "ent_state_id")
	private Long stateId;
//
//	@OneToMany(type => ApplicationBeneficiary, benificiary => benificiary.application)
//	  benificiaries: ApplicationBeneficiary[];
//
//	  @OneToMany(type => EntApplDocCheckList, ck => ck.application)
//	  checklist: EntApplDocCheckList[];
//
//	  @OneToMany(type => ApplicationMetadata, metadata => metadata.application)
//	  metadata: ApplicationMetadata[];
//
//	  @ManyToOne(type => Entitlement, ent => ent.applications)
//	  @JoinColumn({ name: 'ent_id', referencedColumnName: 'id' })
//	  entitlement: Entitlement;

	private Long memId;
	private Long count;
	private Long rankingCount;
	private Long shgName;

	public EntitlementApplication(Long id, Long entId, Long orgId, Long entApplStatus, Long entApplReferredMemId,
			Boolean entApplMultiBenificiary, String entApplyFy, Long entApplRank, Long entApplVoRank,
			String entApplRankReason, String status, String createdBy, String updatedBy, Date createdDate,
			Date updateDate, Long revisionNumber, Long stateId, Long memId, Long count, Long rankingCount,
			Long shgName) {
		super();
		this.id = id;
		this.entId = entId;
		this.orgId = orgId;
		this.entApplStatus = entApplStatus;
		this.entApplReferredMemId = entApplReferredMemId;
		this.entApplMultiBenificiary = entApplMultiBenificiary;
		this.entApplyFy = entApplyFy;
		this.entApplRank = entApplRank;
		this.entApplVoRank = entApplVoRank;
		this.entApplRankReason = entApplRankReason;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
		this.stateId = stateId;
		this.memId = memId;
		this.count = count;
		this.rankingCount = rankingCount;
		this.shgName = shgName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getEntApplStatus() {
		return entApplStatus;
	}

	public void setEntApplStatus(Long entApplStatus) {
		this.entApplStatus = entApplStatus;
	}

	public Long getEntApplReferredMemId() {
		return entApplReferredMemId;
	}

	public void setEntApplReferredMemId(Long entApplReferredMemId) {
		this.entApplReferredMemId = entApplReferredMemId;
	}

	public Boolean getEntApplMultiBenificiary() {
		return entApplMultiBenificiary;
	}

	public void setEntApplMultiBenificiary(Boolean entApplMultiBenificiary) {
		this.entApplMultiBenificiary = entApplMultiBenificiary;
	}

	public String getEntApplyFy() {
		return entApplyFy;
	}

	public void setEntApplyFy(String entApplyFy) {
		this.entApplyFy = entApplyFy;
	}

	public Long getEntApplRank() {
		return entApplRank;
	}

	public void setEntApplRank(Long entApplRank) {
		this.entApplRank = entApplRank;
	}

	public Long getEntApplVoRank() {
		return entApplVoRank;
	}

	public void setEntApplVoRank(Long entApplVoRank) {
		this.entApplVoRank = entApplVoRank;
	}

	public String getEntApplRankReason() {
		return entApplRankReason;
	}

	public void setEntApplRankReason(String entApplRankReason) {
		this.entApplRankReason = entApplRankReason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getRevisionNumber() {
		return revisionNumber;
	}

	public void setRevisionNumber(Long revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getMemId() {
		return memId;
	}

	public void setMemId(Long memId) {
		this.memId = memId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getRankingCount() {
		return rankingCount;
	}

	public void setRankingCount(Long rankingCount) {
		this.rankingCount = rankingCount;
	}

	public Long getShgName() {
		return shgName;
	}

	public void setShgName(Long shgName) {
		this.shgName = shgName;
	}

}
