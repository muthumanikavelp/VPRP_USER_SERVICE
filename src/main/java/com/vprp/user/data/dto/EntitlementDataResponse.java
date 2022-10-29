package com.vprp.user.data.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class EntitlementDataResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Long id;
	private String entName;

	private String isGroup;


	private String planType;
	
	private String status;

	private String createdBy;

	private String updatedBy;

	private Date createdDate;
	
	private Date updateDate;

	private Long revisionNumber;

	private Long stateId;

	private Long isRankingEnabled;

	public EntitlementDataResponse(Long id, String entName, String isGroup, String planType, String status, String createdBy,
			String updatedBy, Date createdDate, Date updateDate, Long revisionNumber, Long stateId,
			Long isRankingEnabled, Integer order, String rankingType, Long sno, Long applCount, Long rankCount,
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
		this.order = order;
		this.rankingType = rankingType;
		this.sno = sno;
		this.applCount = applCount;
		this.rankCount = rankCount;
		this.voCount = voCount;
		this.gpCount = gpCount;
	}

	private Integer order;

	private String rankingType;

	private Long sno;

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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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

}
