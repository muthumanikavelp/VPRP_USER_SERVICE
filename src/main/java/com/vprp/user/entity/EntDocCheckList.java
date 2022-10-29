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
@Table(name = "ent_docs_checklist", schema = "vprp_master")
public class EntDocCheckList implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ent_id")
	private Long entId;

	@Column(name = "state_id")
	private Long stateId;

	@Column(name = "ck_doc_ext_id")
	private String ckDocExtId;

	@Column(name = "ck_doc_name")
	private String ckDocName;

	@Column(name = "ck_doc_req")
	private Boolean ckDocReq;

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

	public EntDocCheckList(Long id, Long entId, Long stateId, String ckDocExtId, String ckDocName, Boolean ckDocReq,
			String status, String createdBy, String updatedBy, Date createdDate, Date updateDate, Long revisionNumber) {
		super();
		this.id = id;
		this.entId = entId;
		this.stateId = stateId;
		this.ckDocExtId = ckDocExtId;
		this.ckDocName = ckDocName;
		this.ckDocReq = ckDocReq;
		this.status = status;
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

	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getCkDocExtId() {
		return ckDocExtId;
	}

	public void setCkDocExtId(String ckDocExtId) {
		this.ckDocExtId = ckDocExtId;
	}

	public String getCkDocName() {
		return ckDocName;
	}

	public void setCkDocName(String ckDocName) {
		this.ckDocName = ckDocName;
	}

	public Boolean getCkDocReq() {
		return ckDocReq;
	}

	public void setCkDocReq(Boolean ckDocReq) {
		this.ckDocReq = ckDocReq;
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

	public EntDocCheckList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

//		    @ManyToOne(type => EntitlementApplication, appl => appl.checklist)
//		    @JoinColumn({ name: 'appl_id', referencedColumnName: 'id' })
//		    application: EntitlementApplication;

}
