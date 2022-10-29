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
@Table(name = "application_status", schema = "vprp_master")
public class ApplicationStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "appl_sts_name")
	private String applStsName;

	@Column(name = "appl_sts_desc")
	private String applStsDesc;

	@Column(name = "appl_sts_visibility")
	private Long applStsVisibility;

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

	public ApplicationStatus(Long id, String applStsName, String applStsDesc, Long applStsVisibility, String createdBy,
			String updatedBy, Date createdDate, Date updateDate, Long revisionNumber) {
		super();
		this.id = id;
		this.applStsName = applStsName;
		this.applStsDesc = applStsDesc;
		this.applStsVisibility = applStsVisibility;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplStsName() {
		return applStsName;
	}

	public void setApplStsName(String applStsName) {
		this.applStsName = applStsName;
	}

	public String getApplStsDesc() {
		return applStsDesc;
	}

	public void setApplStsDesc(String applStsDesc) {
		this.applStsDesc = applStsDesc;
	}

	public Long getApplStsVisibility() {
		return applStsVisibility;
	}

	public void setApplStsVisibility(Long applStsVisibility) {
		this.applStsVisibility = applStsVisibility;
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

}
