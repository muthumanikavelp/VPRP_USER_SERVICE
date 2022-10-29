package com.vprp.user.data.dto;

import java.io.Serializable;
import java.util.List;

import com.vprp.user.entity.ApplicationMetadataKey;
import com.vprp.user.entity.DocLang;
import com.vprp.user.entity.EntDocCheckList;
import com.vprp.user.entity.EntitlementLang;
import com.vprp.user.entity.MasterData;
import com.vprp.user.entity.MasterDataParentMap;

public class SchemeData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<MasterData> masterList;

	List<EntitlementDataResponse> entitlementList;

	List<MasterDataLangDataResponse> masterDataLang;

	List<MasterDataParentMap> masterDataParentMap;

	List<ApplicationMetadataKey> applicationMetadataKey;

	List<EntitlementLang> entitlementLangList;
	
	List<DocLang> docLangList;
	
	public List<EntDocCheckList> getEntDocCheckListList() {
		return entDocCheckListList;
	}

	public void setEntDocCheckListList(List<EntDocCheckList> entDocCheckListList) {
		this.entDocCheckListList = entDocCheckListList;
	}

	List<EntDocCheckList> entDocCheckListList;
	
	

	// TODO P1: DocheckList is Missing

	public List<DocLang> getDocLangList() {
		return docLangList;
	}

	public void setDocLangList(List<DocLang> docLangList) {
		this.docLangList = docLangList;
	}

	public List<MasterDataParentMap> getMasterDataParentMap() {
		return masterDataParentMap;
	}

	public void setMasterDataParentMap(List<MasterDataParentMap> masterDataParentMap) {
		this.masterDataParentMap = masterDataParentMap;
	}

	public List<MasterData> getMasterList() {
		return masterList;
	}

	public void setMasterList(List<MasterData> masterList) {
		this.masterList = masterList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<EntitlementDataResponse> getEntitlementList() {
		return entitlementList;
	}

	public void setEntitlementList(List<EntitlementDataResponse> entitlementList) {
		this.entitlementList = entitlementList;
	}

	public List<MasterDataLangDataResponse> getMasterDataLang() {
		return masterDataLang;
	}

	public void setMasterDataLang(List<MasterDataLangDataResponse> masterDataLang) {
		this.masterDataLang = masterDataLang;
	}

	public List<ApplicationMetadataKey> getApplicationMetadataKey() {
		return applicationMetadataKey;
	}

	public void setApplicationMetadataKey(List<ApplicationMetadataKey> applicationMetadataKey) {
		this.applicationMetadataKey = applicationMetadataKey;
	}

	public List<EntitlementLang> getEntitlementLangList() {
		return entitlementLangList;
	}

	public void setEntitlementLangList(List<EntitlementLang> entitlementLangList) {
		this.entitlementLangList = entitlementLangList;
	}

}
