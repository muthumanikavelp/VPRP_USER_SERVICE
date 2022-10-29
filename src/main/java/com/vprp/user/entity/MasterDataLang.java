package com.vprp.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "master_data_lang", schema = "vprp_master")
public class MasterDataLang {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "mst_id")
	private Long mstId;

	@Column(name = "mst_lang_text_name")
	private String mstLangTextName;

	@Column(name = "mst_lang_code")
	private String mstLangCode;

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

	@Column(name = "mst_state_id")
	private Long stateId;

	public MasterDataLang(String id, Long mstId, String mstLangTextName, String mstLangCode, String createdBy,
			String updatedBy, Date createdDate, Date updateDate, Long revisionNumber, Long stateId) {
		super();
		this.id = id;
		this.mstId = mstId;
		this.mstLangTextName = mstLangTextName;
		this.mstLangCode = mstLangCode;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
		this.stateId = stateId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getMstId() {
		return mstId;
	}

	public void setMstId(Long mstId) {
		this.mstId = mstId;
	}

	public String getMstLangTextName() {
		return mstLangTextName;
	}

	public void setMstLangTextName(String mstLangTextName) {
		this.mstLangTextName = mstLangTextName;
	}

	public String getMstLangCode() {
		return mstLangCode;
	}

	public void setMstLangCode(String mstLangCode) {
		this.mstLangCode = mstLangCode;
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

//	  @OneToOne(type => MasterData)
//	  @JoinColumn({ name: 'mst_id', referencedColumnName: 'id' })
//	  md: MasterData;

}
