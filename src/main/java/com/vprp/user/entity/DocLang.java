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
@Table(name = "doc_lang", schema = "vprp_master")
public class DocLang implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "doc_id")
	private Long docId;

	@Column(name = "doc_lang_text_name")
	private String docLangTextName;

	@Column(name = "doc_lang_code")
	private String docLangCode;

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

	public DocLang(Long id, Long docId, String docLangTextName, String docLangCode, String createdBy, String updatedBy,
			Date createdDate, Date updateDate, Long revisionNumber) {
		super();
		this.id = id;
		this.docId = docId;
		this.docLangTextName = docLangTextName;
		this.docLangCode = docLangCode;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.revisionNumber = revisionNumber;
	}
	
	

	public DocLang() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getDocLangTextName() {
		return docLangTextName;
	}

	public void setDocLangTextName(String docLangTextName) {
		this.docLangTextName = docLangTextName;
	}

	public String getDocLangCode() {
		return docLangCode;
	}

	public void setDocLangCode(String docLangCode) {
		this.docLangCode = docLangCode;
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

//		    @OneToOne(type => EntDocCheckList)
//		    @JoinColumn({ name: 'doc_id', referencedColumnName: 'id' })
//		    dc: EntDocCheckList;

}
