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
@Table(name = "entitlement", schema = "vprp_master")
public class Entitlement implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ent_name")
	private String entName;

	@Column(name = "ent_is_group")
	private String isGroup;

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

	@Column(name = "ent_plan_type")
	private String planType;

	@Column(name = "order")
	private Integer order;

	@Column(name = "ranking_type")
	private String rankingType;

	@Column(name = "status")
	private String status;

	@Column(name = "sno")
	private Long sno;

	@Column(name = "ent_state_id")
	private Long stateId;

	@Column(name = "ent_is_ranking_enabled")
	private Long isRankingEnabled;

	public Entitlement(Long id, String entName, String isGroup, String planType, String status, String createdBy,
			String updatedBy, Date createdDate, Date updateDate, Long revisionNumber, Long stateId,
			Long isRankingEnabled, Long order, String rankingType, Long sno, Long applCount, Long rankCount,
			Long voCount, Long gpCount) {
		super();
		this.id = id;
		this.entName = entName;
		this.isGroup = isGroup;
		this.planType = planType;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
		this.stateId = stateId;
		this.isRankingEnabled = isRankingEnabled;
		this.rankingType = rankingType;
		this.sno = sno;
		this.applCount = applCount;
		this.rankCount = rankCount;
		this.voCount = voCount;
		this.gpCount = gpCount;
	}

	private Long applCount;
	private Long rankCount;
	private Long voCount;
	private Long gpCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(String isGroup) {
		this.isGroup = isGroup;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
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

	public Long getIsRankingEnabled() {
		return isRankingEnabled;
	}

	public void setIsRankingEnabled(Long isRankingEnabled) {
		this.isRankingEnabled = isRankingEnabled;
	}

	public String getRankingType() {
		return rankingType;
	}

	public void setRankingType(String rankingType) {
		this.rankingType = rankingType;
	}

	public Long getSno() {
		return sno;
	}

	public void setSno(Long sno) {
		this.sno = sno;
	}

	public Long getApplCount() {
		return applCount;
	}

	public void setApplCount(Long applCount) {
		this.applCount = applCount;
	}

	public Long getRankCount() {
		return rankCount;
	}

	public void setRankCount(Long rankCount) {
		this.rankCount = rankCount;
	}

	public Long getVoCount() {
		return voCount;
	}

	public void setVoCount(Long voCount) {
		this.voCount = voCount;
	}

	public Long getGpCount() {
		return gpCount;
	}

	public void setGpCount(Long gpCount) {
		this.gpCount = gpCount;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

//		    @OneToOne(type => EntitlementLang, entLang => entLang.ent)
//		    entLang: EntitlementLang;

}
