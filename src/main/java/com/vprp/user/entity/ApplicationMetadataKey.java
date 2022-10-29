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
@Table(name = "application_metadata_key", schema = "vprp_master")
public class ApplicationMetadataKey implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "md_key_name")
	private String mdKeyName;

	@Column(name = "md_key_description")
	private String mdKeyDescription;

	@Column(name = "ent_id")
	private Long entId;

	@Column(name = "md_key_status")
	private Long mdKeyStatus;

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

	public ApplicationMetadataKey(Long id, String mdKeyName, String mdKeyDescription, Long entId, Long mdKeyStatus,
			String createdBy, String updatedBy, Date createdDate, Date updateDate, Long revisionNumber) {
		super();
		this.id = id;
		this.mdKeyName = mdKeyName;
		this.mdKeyDescription = mdKeyDescription;
		this.entId = entId;
		this.mdKeyStatus = mdKeyStatus;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
	}

	
	
	public ApplicationMetadataKey() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMdKeyName() {
		return mdKeyName;
	}

	public void setMdKeyName(String mdKeyName) {
		this.mdKeyName = mdKeyName;
	}

	public String getMdKeyDescription() {
		return mdKeyDescription;
	}

	public void setMdKeyDescription(String mdKeyDescription) {
		this.mdKeyDescription = mdKeyDescription;
	}

	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	public Long getMdKeyStatus() {
		return mdKeyStatus;
	}

	public void setMdKeyStatus(Long mdKeyStatus) {
		this.mdKeyStatus = mdKeyStatus;
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
