package com.vprp.user.data.dto;

import com.vprp.user.dto.PanchayatListDto;
import com.vprp.user.entity.*;
import com.vprp.user.model.CommonResponse;

import java.io.Serializable;
import java.util.List;

public class VprpCRPMobileUserGPWorkPacket extends CommonResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<FederationProfiles> voList;
    private List<PanchayatListDto> gpList;
    private List<MasterData> masterData;
    private List<MasterDataParentMap> masterDataParentMap;
    private List<MessageBundleResponse> messageBundle;
    private List<EntitlementDataResponse> entitlement;
    private List<ApplicationMetadataKey> applicationMetadataKey;
    private List<EntDocCheckList> entDocCheckList;
    private List<MasterDataLangDataResponse> masterDataLang;
    private List<EntitlementLang> entitlementLang;

    private List<DocLang> docLang;


    public List<PanchayatListDto> getGpList() {
        return gpList;
    }

    public void setGpList(List<PanchayatListDto> gpList) {
        this.gpList = gpList;
    }

    public List<DocLang> getDocLang() {
		return docLang;
	}

	public void setDocLang(List<DocLang> docLang) {
		this.docLang = docLang;
	}

    public VprpCRPMobileUserGPWorkPacket(List<FederationProfiles> voList, List<MasterData> masterData, List<MasterDataParentMap> masterDataParentMap, List<MessageBundleResponse> messageBundle, List<EntitlementDataResponse> entitlement,
                                         List<ApplicationMetadataKey> applicationMetadataKey, List<EntDocCheckList> entDocCheckList, List<MasterDataLangDataResponse> masterDataLang, List<EntitlementLang> entitlementLang, List<DocLang> docLang) {
        this.voList = voList;
        this.masterData = masterData;
        this.masterDataParentMap = masterDataParentMap;
        this.messageBundle = messageBundle;
        this.entitlement = entitlement;
        this.applicationMetadataKey = applicationMetadataKey;
        this.entDocCheckList = entDocCheckList;
        this.masterDataLang = masterDataLang;
        this.entitlementLang = entitlementLang;
        this.docLang = docLang;
    }

    public VprpCRPMobileUserGPWorkPacket() {
        // TODO Auto-generated constructor stub
    }

    public List<FederationProfiles> getVoList() {
        return voList;
    }

    public void setVoList(List<FederationProfiles> voList) {
        this.voList = voList;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public List<MasterData> getMasterData() {
        return masterData;
    }

    public void setMasterData(List<MasterData> masterData) {
        this.masterData = masterData;
    }

    public List<MasterDataParentMap> getMasterDataParentMap() {
        return masterDataParentMap;
    }

    public void setMasterDataParentMap(List<MasterDataParentMap> masterDataParentMap) {
        this.masterDataParentMap = masterDataParentMap;
    }

    public List<MessageBundleResponse> getMessageBundle() {
        return messageBundle;
    }

    public void setMessageBundle(List<MessageBundleResponse> messageBundle) {
        this.messageBundle = messageBundle;
    }

    public List<EntitlementDataResponse> getEntitlement() {
        return entitlement;
    }

    public void setEntitlement(List<EntitlementDataResponse> entitlement) {
        this.entitlement = entitlement;
    }

    public List<ApplicationMetadataKey> getApplicationMetadataKey() {
        return applicationMetadataKey;
    }

    public void setApplicationMetadataKey(List<ApplicationMetadataKey> applicationMetadataKey) {
        this.applicationMetadataKey = applicationMetadataKey;
    }

    public List<EntDocCheckList> getEntDocCheckList() {
        return entDocCheckList;
    }

    public void setEntDocCheckList(List<EntDocCheckList> entDocCheckList) {
        this.entDocCheckList = entDocCheckList;
    }

    public List<MasterDataLangDataResponse> getMasterDataLang() {
        return masterDataLang;
    }

    public void setMasterDataLang(List<MasterDataLangDataResponse> masterDataLang) {
        this.masterDataLang = masterDataLang;
    }

    public List<EntitlementLang> getEntitlementLang() {
        return entitlementLang;
    }

    public void setEntitlementLang(List<EntitlementLang> entitlementLang) {
        this.entitlementLang = entitlementLang;
    }

}
