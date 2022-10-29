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
@Table(name = "ent_appl_doc_checklist", schema = "vprp_master")
public class EntApplDocCheckList implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ck_id")
	private Long ckId;

	@Column(name = "appl_id")
	private Long applId;

	@Column(name = "ck_check_status")
	private Long ckCheckStatus;

	@Column(name = "ck_isVerified")
	private Boolean ckIsVerified;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCkId() {
		return ckId;
	}

	public void setCkId(Long ckId) {
		this.ckId = ckId;
	}

	public Long getApplId() {
		return applId;
	}

	public void setApplId(Long applId) {
		this.applId = applId;
	}

	public Long getCkCheckStatus() {
		return ckCheckStatus;
	}

	public void setCkCheckStatus(Long ckCheckStatus) {
		this.ckCheckStatus = ckCheckStatus;
	}

	public Boolean getCkIsVerified() {
		return ckIsVerified;
	}

	public void setCkIsVerified(Boolean ckIsVerified) {
		this.ckIsVerified = ckIsVerified;
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

	public EntApplDocCheckList(Long id, Long ckId, Long applId, Long ckCheckStatus, Boolean ckIsVerified, String status,
			String createdBy, String updatedBy, Date createdDate, Date updateDate, Long revisionNumber) {
		super();
		this.id = id;
		this.ckId = ckId;
		this.applId = applId;
		this.ckCheckStatus = ckCheckStatus;
		this.ckIsVerified = ckIsVerified;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
	}

//		    @ManyToOne(type => EntitlementApplication, appl => appl.checklist)
//		    @JoinColumn({ name: 'appl_id', referencedColumnName: 'id' })
//		    application: EntitlementApplication;

}
