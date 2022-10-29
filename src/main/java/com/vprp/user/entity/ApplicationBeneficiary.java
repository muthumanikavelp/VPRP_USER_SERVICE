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
@Table(name = "application_beneficiary", schema = "vprp_master")
public class ApplicationBeneficiary implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "appl_id")
	private Long applId;

	@Column(name = "appl_md_key_id")
	private Long applMdKeyId;

	@Column(name = "appl_md_value")
	private String applMdValue;

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

//	    @ManyToOne(type => EntitlementApplication, appl => appl.metadata)
//	    @JoinColumn({ name: 'appl_id', referencedColumnName: 'id")
//	    application: EntitlementApplication;
//
//	    @OneToOne(type => ApplicationMetadataKey)
//	    @JoinColumn({ name: 'appl_md_key_id', referencedColumnName: 'id")
//	    metadataKey: ApplicationMetadataKey;

	public ApplicationBeneficiary(Long id, Long applId, Long applMdKeyId, String applMdValue, String createdBy,
			String updatedBy, Date createdDate, Date updateDate, Long revisionNumber) {
		super();
		this.id = id;
		this.applId = applId;
		this.applMdKeyId = applMdKeyId;
		this.applMdValue = applMdValue;
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

	public Long getApplId() {
		return applId;
	}

	public void setApplId(Long applId) {
		this.applId = applId;
	}

	public Long getApplMdKeyId() {
		return applMdKeyId;
	}

	public void setApplMdKeyId(Long applMdKeyId) {
		this.applMdKeyId = applMdKeyId;
	}

	public String getApplMdValue() {
		return applMdValue;
	}

	public void setApplMdValue(String applMdValue) {
		this.applMdValue = applMdValue;
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

	@Override
	public String toString() {
		return "ApplicationBeneficiary [id=" + id + ", applId=" + applId + ", applMdKeyId=" + applMdKeyId
				+ ", applMdValue=" + applMdValue + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", createdDate=" + createdDate + ", updateDate=" + updateDate + ", revisionNumber=" + revisionNumber
				+ "]";
	}

}
