package com.vprp.user.services;

import java.util.*;
import java.util.stream.Collectors;

import com.vprp.user.data.dto.*;
import com.vprp.user.data.repository.*;
import com.vprp.user.dto.*;
import com.vprp.user.entity.*;
import com.vprp.user.utils.AppConstant;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vprp.user.data.dto.EntitlementDataResponse;
import com.vprp.user.data.dto.MasterDataLangDataResponse;
import com.vprp.user.data.dto.MessageBundleResponse;
import com.vprp.user.data.dto.SchemeData;
import com.vprp.user.data.dto.ShgApplicationsResponse;
import com.vprp.user.data.dto.ShgVprpCRPWorkPacket;
import com.vprp.user.data.dto.UploadListResponse;
import com.vprp.user.data.dto.ShgProfilesResponse;
import com.vprp.user.enumurator.SHGSurveyStatus;
import com.vprp.user.payload.UploadRequest;
import com.vprp.user.repository.ApplicationRepository;
import com.vprp.user.repository.ApplicationRolesRepository;
import com.vprp.user.repository.DataRepository;
import com.vprp.user.repository.PanchayatDataRepository;
import com.vprp.user.repository.UserCBORepository;
import com.vprp.user.repository.UserRoleGeographyRepository;

@Service
public class DataService {

    @Autowired
    DataRepository dataRepository;

    @Autowired
    EntilementsRepository entilementsRepository;

    @Autowired
    MasterDataLangDataRepository masterDataLangDataResponse;

    @Autowired
    MasterDataParentMapRepository masterDataParentMapResponse;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ApplicationRolesRepository applicationRolesRepository;

    @Autowired
    UserCBORepository userCBORepository;

    @Autowired
    ShgProfilesRepository shgRepository;

    @Autowired
    ShgApplicationsRepository shgApplicationsRepository;

    @Autowired
    MemberProfilesRepository membersProfilRepository;

    @Autowired
    MessageBundleRepository messageBundleRepository;

    @Autowired
    PanchayatDataRepository panchayatDataRepository;

    @Autowired
    UserRoleGeographyRepository userRoleGeoGraphicsRepository;

    @Autowired
    ApplicationMetadataKeyRepository applicationMetadataKeyRepository;

    @Autowired
    EntilementsLangRepository entitlementRepository;

    @Autowired
    EntDocCheckListRepository entDocCheckListRepository;

    @Autowired
    VoRepository voRepository;

    @Autowired
    DocLangRepository docLangRepository;

    @Autowired
    VoApplicationsRepository voApplicationsRepository;

    @Autowired
    GpApplicationsRepository gpApplicationsRepository;

    @Autowired
    UserCBOService userCBOService;

    public SchemeData getSchemeDataForOffline(Long stateId, List<String> langCodes) {

        SchemeData offlineSchemeDataResponse = new SchemeData();
        List<MasterData> masterData = dataRepository.getMasterData(stateId.intValue());
        offlineSchemeDataResponse.setMasterList(masterData);

        List<Long> mstIds = new ArrayList<>();
        for (MasterData m : masterData)
            mstIds.add(m.getId());

        List<MasterDataLangDataResponse> masterDataLangData;
        if(langCodes != null) {
            // step one : converting comma separate String to array of
            String [] elements = langCodes.get(0).split(",");
            // step two : convert String array to list of String
            List<String> uppercase = Arrays.asList(elements);
            List<String> lowercaseLangCode = langCodes.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            masterDataLangData = masterDataLangDataResponse.getMasterDataLang(mstIds, lowercaseLangCode);
        }else {
            masterDataLangData = masterDataLangDataResponse.getMasterDataLang(mstIds);
        }
        offlineSchemeDataResponse.setMasterDataLang(masterDataLangData);
        List<MasterDataParentMap> masterDataParentMap = masterDataParentMapResponse.getMasterDataParentMapByMst(mstIds);
        offlineSchemeDataResponse.setMasterDataParentMap(masterDataParentMap);

        List<EntitlementDataResponse> entitlementsData = entilementsRepository.getEntitlementsData(stateId);

        offlineSchemeDataResponse.setEntitlementList(entitlementsData);

        if (!entitlementsData.isEmpty()) {
            // gettting All entitlement ids
            List<Long> entitlementIds = new ArrayList<>();
            for (EntitlementDataResponse e : entitlementsData)
                entitlementIds.add(e.getId());
            if (!entitlementIds.isEmpty()) {
                List<EntitlementLang> entitlementLang = entitlementRepository.getEntitlementsLangData(entitlementIds);
                offlineSchemeDataResponse.setEntitlementLangList(entitlementLang);

                List<ApplicationMetadataKey> applicationMetadataKey = applicationMetadataKeyRepository
                        .getApplicationMetadataKey(entitlementIds);
                offlineSchemeDataResponse.setApplicationMetadataKey(applicationMetadataKey);
            }

        }
        List<EntDocCheckList> entDocCheckListData = entDocCheckListRepository.getEntDocCheckListData(stateId);
        offlineSchemeDataResponse.setEntDocCheckListList(entDocCheckListData);
        if (!entDocCheckListData.isEmpty()) {
            Set<Long> entDocIds = new HashSet<>();
            for (EntDocCheckList e : entDocCheckListData)
                entDocIds.add(e.getId());
            if (!entDocIds.isEmpty()) {
                List<DocLang> docLangData = docLangRepository.findDocLangById(entDocIds);
                offlineSchemeDataResponse.setDocLangList(docLangData);
            }

        }

        return offlineSchemeDataResponse;
    }

    public List<SchemeData> getSchemeDataForOffline(List<Long> stateIds,List<String> langCodes ) {
        // TODO Boundary Checking stateId,

        List<SchemeData> schemeDataList = new ArrayList<>();
        for (Long stateId : stateIds) {
            SchemeData schemedata = getSchemeDataForOffline(stateId,langCodes);
            schemeDataList.add(schemedata);

        }
        if (!schemeDataList.isEmpty())
            return schemeDataList;
        else
            return Collections.emptyList();

    }

    public FederationProfiles getVoProfile(String roleCode, Long userId, Long voId) {
        FederationProfiles vo = null;
        try {
            vo = voRepository.findByIdWithType(voId);
            for (ShgProfiles sp : vo.getShgProfilesList()) {
                List<ShgApplications> filteredApps = shgApplicationsRepository
                        .getAppByParentId(Calendar.getInstance().get(Calendar.YEAR), sp.getShgId());
                sp.setShgApplications(filteredApps);

            }

        } catch (Exception e) {
           e.printStackTrace();
        }
        return vo;
    }

    public List<FederationProfiles> getVoProfiles(List<Long> voId) {
        return voRepository.findFederationByCBOIds(voId);
    }


    public ShgVprpCRPWorkPacket getAllShgs(String roleCode, Long userId) {
        ShgVprpCRPWorkPacket shgVprpWorkPacket = new ShgVprpCRPWorkPacket();
        List<Long> assignedSHGs;
        List<Long> otherRequiredSHGs = new ArrayList<Long>();
        List<ShgProfilesResponse> allSHGs;
        List<ShgProfilesResponse> assignedSHGsList;
        List<MemberProfiles> allMembers;
        List<PanchayatMasterResponse> gps;

        try {
            // TODO make it as constant
            ApplicationsDto application = applicationRepository.findApplicationByName(AppConstant.APP_VPRP);

            if (application != null) {
                Long appId = application.getId();
                ApplicationRolesDto appRole = applicationRolesRepository.findApplicationByIdName(appId, roleCode);

                if (appRole != null) {
                    Long appRoleId = appRole.getRoleId();
                    List<CBOs> userCBODtos = userCBORepository.getAllCBOsByRole(appRoleId, userId, "0");
                    List<Long> cboIds = new ArrayList<Long>();

                    for (CBOs cbo : userCBODtos) {
                        // TODO data type needs to change Long to Integer DB
                        for (Integer id : cbo.getCboId()) {
                            cboIds.add(Long.valueOf(id));
                        }

                    }
                    if (!cboIds.isEmpty()) {
                        assignedSHGs = cboIds;

                        assignedSHGsList = shgRepository.getAllShgByCbos(cboIds);
                        Set<Long> panchayatIds = new HashSet<Long>();

                        for (ShgProfilesResponse pro : assignedSHGsList) {
                            panchayatIds.add(pro.getPanchayatId());
                        }
                        List<Long> allSHGIds = new ArrayList<>();
                        allSHGs = shgRepository.findShgByGP(panchayatIds);
                        for (ShgProfilesResponse shg : allSHGs) {
                            allSHGIds.add(shg.getShgId());
                            if (!assignedSHGs.contains(shg.getShgId())) {
                                otherRequiredSHGs.add(shg.getShgId());
                            }
                        }

                        allMembers = membersProfilRepository.getAllMemberProfiles(allSHGIds);

                        gps = panchayatDataRepository.getPanchayatByPanchayatId(panchayatIds);
                        shgVprpWorkPacket.setAllSHGs(allSHGs);
                        shgVprpWorkPacket.setAllMembers(allMembers);
                        shgVprpWorkPacket.setOtherRequiredSHGs(otherRequiredSHGs);
                        shgVprpWorkPacket.setAssignedSHGs(assignedSHGs);
                        shgVprpWorkPacket.setGps(gps);


                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shgVprpWorkPacket;
    }

    public VoVprpCRPWorkPacket getAllVos(String roleCode, Long userId, Boolean includeSurveyData, Integer surveyYear, Set<Long> shgIdsToDownload) {
        VoVprpCRPWorkPacket voVprpWorkPacket = new VoVprpCRPWorkPacket();
        List<FederationProfiles> assignedVOs;
        List<FederationProfiles> assignedVOsList;
        List<PanchayatMasterResponse> gps;
        try {
            // TODO make it as constant
            ApplicationsDto application = applicationRepository.findApplicationByName(AppConstant.APP_VPRP);

            if (application != null) {
                Long appId = application.getId();
                ApplicationRolesDto appRole = applicationRolesRepository.findApplicationByIdName(appId, roleCode);

                if (appRole != null) {
                    Long appRoleId = appRole.getRoleId();
                    List<CBOs> userCBODtos = userCBORepository.getAllCBOsByRole(appRoleId, userId, "1");
                    List<Long> cboIds = new ArrayList<Long>();

                    for (CBOs cbo : userCBODtos) {
                        // TODO data type needs to change Long to Integer DB
                        for (Integer id : cbo.getCboId()) {
                            cboIds.add(Long.valueOf(id));
                        }

                    }
                    if (!cboIds.isEmpty()) {
                        assignedVOsList = voRepository.findFederationByCBOIds(cboIds);

                        // TODO P1: pull shgprofile parent parent_cbo id // cbo level(parent type) =1
                        // TODO P1: download data
                        Set<Long> panchayatIds = new HashSet<Long>();

                        for (FederationProfiles fed : assignedVOsList) {
                            panchayatIds.add(fed.getPanchayatId());

                        }
                        assignedVOs = assignedVOsList;
                        gps = panchayatDataRepository.getPanchayatByPanchayatId(panchayatIds);
                        for (FederationProfiles av : assignedVOs) {
                            for (ShgProfiles sp : av.getShgProfilesList()) {
                                if (!includeSurveyData) {
                                    sp.setShgApplications(null);
                                }
                                // need to write logic for upload data with survey year (shgApplications)
                                else {
                                    if (shgIdsToDownload == null || shgIdsToDownload.contains(sp.getShgId())) {
                                        List<ShgApplications> filteredApps = shgApplicationsRepository
                                                .getAppByParentId(surveyYear, sp.getShgId());
                                        sp.setShgApplications(filteredApps);
                                    }
                                }
                            }
                        }
                        voVprpWorkPacket.setAssignedVOs(assignedVOs);
                        voVprpWorkPacket.setGps(gps);
                    }
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }

        return voVprpWorkPacket;
    }

    public List<FederationProfiles> getAllVosByGP(String roleCode, Long userId, Boolean includeSurveyData, Integer surveyYear, List<Long> gpIds) {
        List<FederationProfiles> fp = null;
        try {
            fp = voRepository.getAllVOsByGP(gpIds);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fp;
    }

    public MessageBundleResponse getAllMsgByCodeBundle(char[] langCode) {
        MessageBundleResponse messageBundle = null;

        try {
            if (langCode == null || langCode.length != 2) {
                char[] engLang = {'E', 'N'};
                messageBundle = messageBundleRepository.findByLangCode(engLang);
            } else {
                messageBundle = messageBundleRepository.findByLangCode(langCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messageBundle;
    }

    public UploadListResponse uploadData(UploadRequest uploadRequest, String roleCode, Boolean isOverried, Long userId) {
        ShgApplications shgApp = null;
        ShgApplicationsResponse shgAppRes = null;
        VoApplications voApp = null;
        GpApplications gpApp = null;
        Optional<FederationProfiles> federationProfiles = null;
        Optional<PanchayatMaster> gpMaster;
        UploadListResponse uploadRes = null;
        try {
            ShgProfilesResponse sh;
            Date date = new Date();

            if (isOverried) {

                if (roleCode.equals(AppConstant.ROLE_VPRP_SHG)) {
                    Set<Long> userCBOS = userCBOService.getAllCBOIdUserId(userId);
                    Long requestCBOs = uploadRequest.getShgId();
                    if(!userCBOS.contains(requestCBOs)){
                        uploadRes = new UploadListResponse(0L,  "FAILED", "Invalid SHG Access");
                        uploadRes.setStatusCode(HttpStatus.FORBIDDEN.value());
                        uploadRes.setStatus("Upload access is prohibited");
                        return uploadRes;
                    }
                    sh = shgRepository.findShgByShgId(uploadRequest.getShgId());
                    shgApp = new ShgApplications(sh.getId(), sh.getShgId(), sh.getParentCBOId(), sh.getParentCBOType(),
                            sh.getStateId(), sh.getDistrictId(), sh.getBlockId(), sh.getPanchayatId(),
                            sh.getVillageId(), uploadRequest.getSurveyYear(), uploadRequest.getJsonString(),
                            SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(), uploadRequest.getUserId(),
                            uploadRequest.getUserId(), date, date, 0L);
                    shgApplicationsRepository.deleteByShgId(shgApp.getShgId());
                    shgApplicationsRepository.save(shgApp);

                    shgAppRes = shgApplicationsRepository.findShgByShgId(uploadRequest.getShgId());
                    if (shgAppRes != null) {
                        uploadRes = new UploadListResponse(sh.getShgId(), "SUCCESS", "Data replaced successfully");
                    } else {
                        uploadRes = new UploadListResponse(sh.getShgId(), "SUCCESS", "Data Uploaded successfully");
                    }
                } else if (roleCode.equals(AppConstant.ROLE_VPRP_VO)) {
                    federationProfiles = voRepository.findById(uploadRequest.getVoId());
                    if (federationProfiles.isPresent()) {
                        FederationProfiles fp = federationProfiles.get();
                        voApp = new VoApplications(fp.getFederationId(), fp.getFederationId(), 0L, 0,
                                fp.getStateId(), fp.getDistrictId(), fp.getBlockId(), fp.getPanchayatId(),
                                uploadRequest.getSurveyYear(), uploadRequest.getJsonString(),
                                SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(), uploadRequest.getUserId(),
                                uploadRequest.getUserId(), date, date, 0L);
                        voApplicationsRepository.deleteByVoId(voApp.getVoId());
                        voApplicationsRepository.save(voApp);

                        Optional<FederationProfiles> fpPro = voRepository.findById(uploadRequest.getVoId());
                        if (fpPro.isPresent()) {
                            uploadRes = new UploadListResponse(0L,voApp.getVoId(), "SUCCESS", "Data replaced successfully");
                        } else {
                            uploadRes = new UploadListResponse(0L,voApp.getVoId(), "SUCCESS", "Data Uploaded successfully");
                        }
                    }
                } else if (roleCode.equals(AppConstant.ROLE_VPRP_GP)) {
                    gpMaster = panchayatDataRepository.findById(uploadRequest.getGpId());
                    if (gpMaster.isPresent()) {
                        PanchayatMaster pm = gpMaster.get();
                        gpApp = new GpApplications(pm.getPanchayatId(), pm.getPanchayatId(), 0L, 0,
                                pm.getStateId(), pm.getDistrictId(), pm.getBlockId(),
                                uploadRequest.getSurveyYear(), uploadRequest.getJsonString(),
                                SHGSurveyStatus.SUBMITTED_TO_GP.ordinal(), uploadRequest.getUserId(),
                                uploadRequest.getUserId(), date, date, 0L, uploadRequest.getAdditionalData());

                        Optional<GpApplications> gpPresent = gpApplicationsRepository.findGpByGpId(uploadRequest.getGpId());
                        gpApplicationsRepository.deleteByGpId(gpApp.getGpId());
                        gpApplicationsRepository.save(gpApp);
                        if (gpPresent.isPresent()) {
                            uploadRes = new UploadListResponse(0L,0L,gpApp.getGpId(), "SUCCESS", "Data replaced successfully");
                        } else {
                            uploadRes = new UploadListResponse(0L,0L,gpApp.getGpId(), "SUCCESS", "Data Uploaded successfully");
                        }
                    }
                }

            } else {

                if (roleCode.equals(AppConstant.ROLE_VPRP_SHG)) {
                    shgAppRes = shgApplicationsRepository.findShgByShgId(uploadRequest.getShgId());

                    if (shgAppRes == null) {
                        sh = shgRepository.findShgByShgId(uploadRequest.getShgId());
                        shgApp = new ShgApplications(sh.getShgId(), sh.getParentCBOId(), sh.getParentCBOType(),
                                sh.getStateId(), sh.getDistrictId(), sh.getBlockId(), sh.getPanchayatId(),
                                sh.getVillageId(), uploadRequest.getSurveyYear(), uploadRequest.getJsonString(),
                                SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(), uploadRequest.getUserId(),
                                uploadRequest.getUserId(), date, date, 0L, uploadRequest.getAdditionalData());
                        uploadRes = new UploadListResponse(sh.getShgId(), "SUCCESS", "Data Uploaded successfully");
                        shgApplicationsRepository.save(shgApp);
                    } else {
                        uploadRes = new UploadListResponse(uploadRequest.getShgId(), "FAILED", "Data Already Present");
                    }
                } else if (roleCode.equals(AppConstant.ROLE_VPRP_VO)) {
                    voApp = voApplicationsRepository.findVoByVoId(uploadRequest.getVoId());
                    if (voApp == null) {
                        federationProfiles = voRepository.findById(uploadRequest.getVoId());
                        FederationProfiles fp = federationProfiles.get();
                        voApp = new VoApplications(fp.getFederationId(), fp.getFederationId(), 0L, 0,
                                fp.getStateId(), fp.getDistrictId(), fp.getBlockId(), fp.getPanchayatId(),
                                uploadRequest.getSurveyYear(), uploadRequest.getJsonString(),
                                SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(), uploadRequest.getUserId(),
                                uploadRequest.getUserId(), date, date, 0L);
                        voApplicationsRepository.save(voApp);
                        uploadRes = new UploadListResponse(0L,voApp.getVoId(), "SUCCESS", "Data Uploaded successfully");
                    } else {
                        uploadRes = new UploadListResponse(0L,voApp.getVoId(), "FAILED", "Data Already Present");
                    }
                } else if (roleCode.equals(AppConstant.ROLE_VPRP_GP)) {
                    Optional<GpApplications> gpApplications = gpApplicationsRepository.findGpByGpId(uploadRequest.getGpId());
                    if (!gpApplications.isPresent()) {
                        gpMaster = panchayatDataRepository.findById(uploadRequest.getGpId());
                        PanchayatMaster pm = gpMaster.get();
                        gpApp = new GpApplications(pm.getPanchayatId(), pm.getPanchayatId(), 0L, 0,
                                pm.getStateId(), pm.getDistrictId(), pm.getBlockId(),
                                uploadRequest.getSurveyYear(), uploadRequest.getJsonString(),
                                SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(), uploadRequest.getUserId(),
                                uploadRequest.getUserId(), date, date, 0L, uploadRequest.getAdditionalData());
                        gpApplicationsRepository.save(gpApp);
                        uploadRes = new UploadListResponse(0L,0L,gpApp.getGpId(), "SUCCESS", "Data Uploaded successfully");
                    } else {
                        uploadRes = new UploadListResponse(0L,0L,gpApp.getGpId(), "FAILED", "Data Already Present");
                    }
                }
            }
        } catch (JSONException err) {
            err.printStackTrace();
        }
        return uploadRes;
    }

    public List<UploadListResponse> uploadDataAll(List<UploadRequest> uploadRequestList, String roleCode,
                                                  Boolean isOverride, Long userId) {
        List<ShgApplications> shgApplicationsList = new ArrayList<>();
        ShgApplicationsResponse shgApp;
        ShgApplications shgApplications;
        VoApplications voApp;
        GpApplications gpApp;
        UploadListResponse uploadRes = null;
        List<UploadListResponse> response = new ArrayList<>();
        try {
            if (roleCode.equals(AppConstant.ROLE_VPRP_SHG)) {
                ShgProfilesResponse sh;
                Date date = new Date();
                Set<Long> userCBOS = userCBOService.getAllCBOIdUserId(userId);
                List<Long> requestCBOs = uploadRequestList.stream().map(UploadRequest::getShgId).collect(Collectors.toList());
                if(!userCBOS.containsAll(requestCBOs)){
                    uploadRes = new UploadListResponse(0L,  "FAILED", "Invalid SHG Access");
                    uploadRes.setStatusCode(HttpStatus.FORBIDDEN.value());
                    uploadRes.setStatus("Upload access is prohibited");
                    response.add(uploadRes);
                    return response;
                }

                for (UploadRequest uploadReq : uploadRequestList) {

                    // verify user Id with shg ids

                    if (isOverride) {
                        sh = shgRepository.findShgByShgId(uploadReq.getShgId());
                        shgApp = shgApplicationsRepository.findShgByShgId(uploadReq.getShgId());
                        shgApplications = new ShgApplications(sh.getId(), sh.getShgId(), sh.getParentCBOId(),
                                sh.getParentCBOType(), sh.getStateId(), sh.getDistrictId(), sh.getBlockId(),
                                sh.getPanchayatId(), sh.getVillageId(), uploadReq.getSurveyYear(),
                                uploadReq.getJsonString(), SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(),
                                uploadReq.getUserId(), uploadReq.getUserId(), date, date, 0L);
                        if (shgApp != null) {
                            shgApplicationsRepository.deleteByShgId(shgApp.getShgId());
                            uploadRes = new UploadListResponse(sh.getShgId(), "SUCCESS", "Data replaced successfully");
                        } else {
                            uploadRes = new UploadListResponse(sh.getShgId(), "SUCCESS", "Data Uploaded successfully");
                        }
                        shgApplicationsRepository.save(shgApplications);
                        response.add(uploadRes);
                    } else {
                        shgApp = shgApplicationsRepository.findShgByShgId(uploadReq.getShgId());
                        if (shgApp == null) {
                            sh = shgRepository.findShgByShgId(uploadReq.getShgId());
                            uploadRes = new UploadListResponse(sh.getShgId(), "SUCCESS", "Data Uploaded successfully");
                            shgApplications = new ShgApplications(sh.getShgId(), sh.getParentCBOId(),
                                    sh.getParentCBOType(), sh.getStateId(), sh.getDistrictId(), sh.getBlockId(),
                                    sh.getPanchayatId(), sh.getVillageId(), uploadReq.getSurveyYear(),
                                    uploadReq.getJsonString(), SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(),
                                    uploadReq.getUserId(), uploadReq.getUserId(), date, date, 0L, uploadReq.getAdditionalData());
                            shgApplicationsList.add(shgApplications);
                            response.add(uploadRes);
                        } else {
                            uploadRes = new UploadListResponse(uploadReq.getShgId(), "FAILED", "Data Already Present");
                            response.add(uploadRes);
                        }
                    }

                }
                shgApplicationsRepository.saveAll(shgApplicationsList);

            } else if (roleCode.equals(AppConstant.ROLE_VPRP_VO)) {
                Optional<FederationProfiles> federationProfiles = null;
                Date date = new Date();

                for (UploadRequest uploadRequest : uploadRequestList) {

                    if (isOverride) {
                        federationProfiles = voRepository.findById(uploadRequest.getVoId());
                        if (federationProfiles.isPresent()) {
                            FederationProfiles fp = federationProfiles.get();
                            voApp = new VoApplications(fp.getFederationId(), fp.getFederationId(), 0L, 0,
                                    fp.getStateId(), fp.getDistrictId(), fp.getBlockId(), fp.getPanchayatId(),
                                    uploadRequest.getSurveyYear(), uploadRequest.getJsonString(),
                                    SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(), uploadRequest.getUserId(),
                                    uploadRequest.getUserId(), date, date, 0L);
                            VoApplications fpPro = voApplicationsRepository.findVoByVoId(uploadRequest.getShgId());
                            voApplicationsRepository.deleteByVoId(voApp.getVoId());
                            voApplicationsRepository.save(voApp);

                            if (fpPro != null) {
                                uploadRes = new UploadListResponse(0L, fp.getFederationId(), "SUCCESS", "Data Uploaded successfully");
                            } else {
                                uploadRes = new UploadListResponse(0L, fp.getFederationId(), "SUCCESS", "Data replaced successfully");
                            }
                        }
                        response.add(uploadRes);
                    } else {
                        voApp = voApplicationsRepository.findVoByVoId(uploadRequest.getVoId());
                        if (voApp == null) {
                            Optional<FederationProfiles> fpTemp = voRepository.findById(uploadRequest.getVoId());
                            FederationProfiles fp = fpTemp.get();
                            voApp = new VoApplications(fp.getFederationId(), fp.getFederationId(), 0L, 0,
                                    fp.getStateId(), fp.getDistrictId(), fp.getBlockId(), fp.getPanchayatId(),
                                    uploadRequest.getSurveyYear(), uploadRequest.getJsonString(),
                                    SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(), uploadRequest.getUserId(),
                                    uploadRequest.getUserId(), date, date, 0L);
                            voApplicationsRepository.save(voApp);
                            uploadRes = new UploadListResponse(0L, fp.getFederationId(), "SUCCESS", "Data Uploaded successfully");
                        } else {
                            uploadRes = new UploadListResponse(0L, uploadRequest.getVoId(), "FAILED", "Data Already Present");
                        }
                        response.add(uploadRes);
                    }

                }

            } else if (roleCode.equals(AppConstant.ROLE_VPRP_GP)) {
                Optional<PanchayatMaster> panchayatMaster = null;
                Date date = new Date();

                for (UploadRequest uploadRequest : uploadRequestList) {

                    if (isOverride) {
                        panchayatMaster = panchayatDataRepository.findById(uploadRequest.getGpId());
                        if (panchayatMaster.isPresent()) {
                            PanchayatMaster pm = panchayatMaster.get();
                            gpApp = new GpApplications(pm.getPanchayatId(), pm.getPanchayatId(), 0L, 0,
                                    pm.getStateId(), pm.getDistrictId(), pm.getBlockId(),
                                    uploadRequest.getSurveyYear(), uploadRequest.getJsonString(),
                                    SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(), uploadRequest.getUserId(),
                                    uploadRequest.getUserId(), date, date, 0L, uploadRequest.getAdditionalData());
                            Optional<GpApplications> gpCheck = gpApplicationsRepository.findGpByGpId(uploadRequest.getGpId());
                            gpApplicationsRepository.deleteByGpId(gpApp.getGpId());
                            gpApplicationsRepository.save(gpApp);

                            if (gpCheck.isPresent()) {
                                uploadRes = new UploadListResponse(0L,0L, pm.getPanchayatId(), "SUCCESS", "Data replaced successfully");
                            } else {
                                uploadRes = new UploadListResponse(0L,0L, pm.getPanchayatId(), "SUCCESS", "Data Uploaded successfully");
                            }
                        }
                        response.add(uploadRes);
                    } else {
                        Optional<GpApplications> gpCheck = gpApplicationsRepository.findGpByGpId(uploadRequest.getGpId());
                        if (!gpCheck.isPresent()) {
                            Optional<PanchayatMaster> pmData = panchayatDataRepository.findById(uploadRequest.getGpId());
                            PanchayatMaster pm = pmData.get();
                            gpApp = new GpApplications(pm.getPanchayatId(), pm.getPanchayatId(), 0L, 0,
                                    pm.getStateId(), pm.getDistrictId(), pm.getBlockId(),
                                    uploadRequest.getSurveyYear(), uploadRequest.getJsonString(),
                                    SHGSurveyStatus.SUBMITTED_TO_VO.ordinal(), uploadRequest.getUserId(),
                                    uploadRequest.getUserId(), date, date, 0L, uploadRequest.getAdditionalData());
                            gpApplicationsRepository.save(gpApp);
                            uploadRes = new UploadListResponse(0L, 0L,pm.getPanchayatId(), "SUCCESS", "Data Uploaded successfully");
                        } else {
                            uploadRes = new UploadListResponse(0L, 0L,uploadRequest.getVoId(), "FAILED", "Data Already Present");
                        }
                        response.add(uploadRes);
                    }

                }

            }

        } catch (JSONException err) {
            err.printStackTrace();
        }
        return response;
    }

    public VprpCRPMobileUserWorkPacket shgDownload(Long userId, String roleCode, List<String> langCodes,
                                                   List<String> dataset) {

        VprpCRPMobileUserWorkPacket shgVprpCRPMobileUserWorkPacket = new VprpCRPMobileUserWorkPacket();
        ApplicationsDto application = applicationRepository.findApplicationByName(AppConstant.APP_VPRP);

        if (application != null) {
            Long appId = application.getId();
            ApplicationRolesDto appRole = applicationRolesRepository.findApplicationByIdName(appId, roleCode);

            if (appRole != null) {


                if (dataset.contains("SHG_VPRP_CRP_WORK_PACKET")) {
                    shgVprpCRPMobileUserWorkPacket.setVoVprpCRPWorkPacket(null);
                    shgVprpCRPMobileUserWorkPacket.setShgVprpCRPWorkPacket(this.getAllShgs(roleCode, userId));
                }
                if (dataset.contains("MASTER_DATA")) {
                    List<Long> stateIds = new ArrayList<>();
                    UserRoleGeography userRoleGeography = userRoleGeoGraphicsRepository.getAllGeos(userId, appRole.getRoleId());
                    stateIds.add(userRoleGeography.getState());

                    this.getAllMasterData(userId, stateIds, shgVprpCRPMobileUserWorkPacket, langCodes);


                }
                if (dataset.contains("MESSAGE_BUDBLE")) {
                    List<MessageBundleResponse> msgBundleDataList = getMessageBundle(langCodes);
                    shgVprpCRPMobileUserWorkPacket.setMessageBundle(msgBundleDataList);
                }
            }
        }
        return shgVprpCRPMobileUserWorkPacket;
    }

    private VprpCRPMobileUserWorkPacket getAllMasterData(Long userId, List<Long> stateIds, VprpCRPMobileUserWorkPacket shgVprpCRPMobileUserWorkPacket, List<String> langCodes) {
        if (!stateIds.isEmpty()) {
            List<SchemeData> schemaData = this.getSchemeDataForOffline(stateIds,langCodes);
            // step one : converting comma separate String to array of
            String [] elements = langCodes.get(0).split(",");
            // step two : convert String array to list of String
            List<String> uppercaseLang = Arrays.asList(elements);
            if (!schemaData.isEmpty()) {
                for (SchemeData sch : schemaData) {
                    shgVprpCRPMobileUserWorkPacket.setMasterData(sch.getMasterList());
                    List<MasterDataLangDataResponse> mstLangList = sch.getMasterDataLang().stream().filter(mstLang -> {
                        if(mstLang!= null){
                            if(mstLang.getMstLangCode()!= null && !mstLang.getMstLangCode().isEmpty()){
                                return langCodes.contains(mstLang.getMstLangCode().toUpperCase(Locale.ROOT));
                            }else{
                                return false;
                            }
                        }else{
                            return false;
                        }



                    }).collect(Collectors.toList());
                    shgVprpCRPMobileUserWorkPacket.setMasterDataLang(mstLangList);
                    shgVprpCRPMobileUserWorkPacket.setMasterDataParentMap(sch.getMasterDataParentMap());
                    shgVprpCRPMobileUserWorkPacket.setEntitlement(sch.getEntitlementList());
                    List<EntitlementLang> entLangList = sch.getEntitlementLangList().stream().filter(entLang -> {
                        if(entLang!=null){
                            if(entLang.getEntLangCode()!=null && !entLang.getEntLangCode().isEmpty()){
                                return langCodes.contains(entLang.getEntLangCode().toUpperCase(Locale.ROOT));
                            }else{
                                return false;
                            }
                        }else{
                            return false;
                        }


                    }).collect(Collectors.toList());
                    shgVprpCRPMobileUserWorkPacket.setEntitlementLang(entLangList);
                    shgVprpCRPMobileUserWorkPacket.setEntDocCheckList(sch.getEntDocCheckListList());
                    shgVprpCRPMobileUserWorkPacket.setApplicationMetadataKey(sch.getApplicationMetadataKey());
                    shgVprpCRPMobileUserWorkPacket.setDocLang(sch.getDocLangList());

                }
            }

        }
        return shgVprpCRPMobileUserWorkPacket;
    }

    private VprpCRPMobileUserGPWorkPacket getAllMasterData(Long userId, List<Long> stateIds, VprpCRPMobileUserGPWorkPacket workPacket, List<String> langCodes) {
        if (!stateIds.isEmpty()) {
            List<SchemeData> schemaData = this.getSchemeDataForOffline(stateIds,langCodes);
            if (!schemaData.isEmpty()) {
                for (SchemeData sch : schemaData) {
                    workPacket.setMasterData(sch.getMasterList());
                    List<MasterDataLangDataResponse> mstLangList = sch.getMasterDataLang().stream().filter(mstLang -> {
                        return langCodes.contains(mstLang.getMstLangCode().toUpperCase(Locale.ROOT));

                    }).collect(Collectors.toList());
                    workPacket.setMasterDataLang(mstLangList);
                    workPacket.setMasterDataParentMap(sch.getMasterDataParentMap());
                    workPacket.setEntitlement(sch.getEntitlementList());
                    List<EntitlementLang> entLangList = sch.getEntitlementLangList().stream().filter(entLang -> {
                        return langCodes.contains(entLang.getEntLangCode().toUpperCase(Locale.ROOT));
                    }).collect(Collectors.toList());
                    workPacket.setEntitlementLang(entLangList);
                    workPacket.setEntDocCheckList(sch.getEntDocCheckListList());
                    workPacket.setApplicationMetadataKey(sch.getApplicationMetadataKey());
                    workPacket.setDocLang(sch.getDocLangList());

                }
            }

        }
        return workPacket;
    }

    public VprpCRPMobileUserWorkPacket voDownload(Long userId, String roleCode, List<String> langCodes,
                                                  List<String> dataset, Boolean includeSurveyData, Integer surveyYaer, Set<Long> shgIdsToDownload) {


        ApplicationsDto application = applicationRepository.findApplicationByName(AppConstant.APP_VPRP);
        VprpCRPMobileUserWorkPacket voVprpCRPMobileUserWorkPacket = new VprpCRPMobileUserWorkPacket();

        if (application != null) {
            Long appId = application.getId();
            ApplicationRolesDto appRole = applicationRolesRepository.findApplicationByIdName(appId, roleCode);

            if (appRole != null) {

                if (dataset.contains("VO_VPRP_CRP_WORK_PACKET")) {
                    voVprpCRPMobileUserWorkPacket.setShgVprpCRPWorkPacket(null);
                    voVprpCRPMobileUserWorkPacket
                            .setVoVprpCRPWorkPacket(this.getAllVos(roleCode, userId, includeSurveyData, surveyYaer, shgIdsToDownload));
                }
                if (dataset.contains("MASTER_DATA")) {
                    List<Long> stateIds = new ArrayList<>();
                    UserRoleGeography userRoleGeography = userRoleGeoGraphicsRepository.getAllGeos(userId, appRole.getRoleId());
                    stateIds.add(userRoleGeography.getState());
                    this.getAllMasterData(userId, stateIds, voVprpCRPMobileUserWorkPacket, langCodes);

                }
                if (dataset.contains("MESSAGE_BUDBLE")) {
                    List<MessageBundleResponse> msgBundleDataList = getMessageBundle(langCodes);
                    voVprpCRPMobileUserWorkPacket.setMessageBundle(msgBundleDataList);
                }
            }
        }
        return voVprpCRPMobileUserWorkPacket;
    }


    public VprpCRPMobileUserGPWorkPacket gpDownload(Long userId, String roleCode, List<String> langCodes,
                                                    List<String> dataset, Boolean includeSurveyData, Integer surveyYear, List<Long> gpIds) {


        ApplicationsDto application = applicationRepository.findApplicationByName(AppConstant.APP_VPRP);
        VprpCRPMobileUserGPWorkPacket gpWorkPacket = new VprpCRPMobileUserGPWorkPacket();

        if (application != null) {
            Long appId = application.getId();
            ApplicationRolesDto appRole = applicationRolesRepository.findApplicationByIdName(appId, roleCode);

            if (appRole != null) {

                if (dataset.contains("GP_VPRP_CRP_WORK_PACKET")) {
                    gpWorkPacket.setVoList(this.getAllVosByGP(roleCode, userId, includeSurveyData, surveyYear, gpIds));
                    gpWorkPacket.setGpList(panchayatDataRepository.getPanchayatByIds(gpIds));
                }
                if (dataset.contains("MASTER_DATA")) {
                    List<Long> stateIds;
                    stateIds = panchayatDataRepository.getStateIdsByPanchayat(gpIds);
                    this.getAllMasterData(userId, stateIds, gpWorkPacket, langCodes);

                }
                if (dataset.contains("MESSAGE_BUDBLE")) {
                    List<MessageBundleResponse> msgBundleDataList = getMessageBundle(langCodes);
                    gpWorkPacket.setMessageBundle(msgBundleDataList);
                }
            }
        }
        return gpWorkPacket;
    }

    private List<MessageBundleResponse> getMessageBundle(List<String> langCodes) {

        List<MessageBundleResponse> msgBundleDataList = new ArrayList<>();
        MessageBundleResponse msgBundleData = null;
        if (!langCodes.isEmpty()) {
            for (String lang : langCodes) {
                msgBundleData = this.getAllMsgByCodeBundle(lang.toCharArray());
                msgBundleDataList.add(msgBundleData);
            }

        } else {
            char[] engLang = {'E', 'N'};
            msgBundleData = this.getAllMsgByCodeBundle(engLang);
            msgBundleDataList.add(msgBundleData);
        }

        return msgBundleDataList;
    }

    public List<VoApplications> downloadAllVOs(Long gpId) {

        List<FederationProfiles> fpProfiles = voRepository.getAllVOsByGP(gpId);
        Set<Long> voIds = fpProfiles.stream().map(vo -> vo.getFederationId()).collect(Collectors.toSet());
        List<VoApplications> voApplications = voApplicationsRepository.findVoByVoId(voIds);
        return voApplications;

    }

    public List<FederationProfiles> gpDetails(Long gpId) {
        return voRepository.getAllVOsByGP(gpId);

    }

    public Map<Long, List<ShgStatus>> shgSurveyStatus(Long voIds) {
        Map<Long, List<ShgStatus>> shgStatusMap = new HashMap<>();
        List<ShgProfiles> shgProfilesList = shgRepository.findShgByParentCBOs(voIds);
        Set<Long> shgIds = shgProfilesList.stream().map(ShgProfiles::getShgId).collect(Collectors.toSet());
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        List<ShgApplications> shgApps = shgApplicationsRepository.getAppByParentId(year, shgIds);
        List<ShgStatus> shgStatusList = new ArrayList<>();
        if (!shgProfilesList.isEmpty()) {
            shgProfilesList.forEach(shgProfile -> {
                shgStatusList.add(new ShgStatus(shgProfile.getShgId(), -1));
                shgStatusMap.put(voIds, shgStatusList); //-1 means UNKNOWN or NOT SET to be moved to constants
            });
            List<Long> shgAppId = shgApps.stream().map(ShgApplications::getShgId).collect(Collectors.toList());
            List<ShgStatus> shgStatusLis = shgStatusMap.get(voIds).stream().map(changeStatus -> {
                ShgStatus s = new ShgStatus(changeStatus);
                if (shgAppId.contains(changeStatus.getShgId())) {
                    s.setShgId(changeStatus.getShgId());
                    s.setShgSurveyStatus(1);
                } else {
                    s.setShgId(changeStatus.getShgId());
                    s.setShgSurveyStatus(-1);
                }
                return s;

            }).collect(Collectors.toList());
            shgStatusMap.put(voIds, shgStatusLis);

        }

        return shgStatusMap;

    }

    public Map<Long, ShgStatus> shgSurveyStatusByToken(List<Long> shgIds, Integer surveyYear) {
        Map<Long, ShgStatus> shgStatusMap = new HashMap<>();
        List<ShgProfiles> shgProfilesList = shgRepository.findShgByShgIds(shgIds);

        List<ShgApplications> shgApps = shgApplicationsRepository.getShgAppByIds(surveyYear, shgIds);

        List<Long> shgAppList = shgApps.stream().map(ShgApplications::getShgId).collect(Collectors.toList());

        if (!shgProfilesList.isEmpty()) {
            shgProfilesList.forEach(shgProfile -> {
                final ShgStatus shgStatusList = new ShgStatus(shgProfile.getShgId(), -1);
                shgStatusMap.put(shgProfile.getShgId(), shgStatusList); //-1 means UNKNOWN or NOT SET to be moved to constants
            });
        }
        for (Map.Entry<Long, ShgStatus> shgStatusEntry : shgStatusMap.entrySet()) {
            if (shgAppList.contains(shgStatusEntry.getKey())) {
                ShgStatus temp = shgStatusEntry.getValue();
                temp.setShgSurveyStatus(1);
            }
        }
        return shgStatusMap;

    }
    public Map<Long, VoStatus> voSurveyStatusByToken(List<Long> voIds, Integer surveyYear) {
        Map<Long, VoStatus> voStatusMap = new HashMap<>();
        List<FederationProfiles> voProfilesList = voRepository.findShgByShgIds(voIds);

        List<VoApplications> shgApps = voApplicationsRepository.getVoAppByIds(surveyYear, voIds);

        List<Long> voAppList = shgApps.stream().map(VoApplications::getVoId).collect(Collectors.toList());

        if (!voProfilesList.isEmpty()) {
            voProfilesList.forEach(voProfile -> {
                final VoStatus voStatusList = new VoStatus(voProfile.getFederationId(), -1);
                voStatusMap.put(voProfile.getFederationId(), voStatusList); //-1 means UNKNOWN or NOT SET to be moved to constants
            });
        }
        for (Map.Entry<Long, VoStatus> voStatusEntry : voStatusMap.entrySet()) {
            if (voAppList.contains(voStatusEntry.getKey())) {
                VoStatus temp = voStatusEntry.getValue();
                temp.setVoSurveyStatus(1);
            }
        }
        return voStatusMap;

    }

    public Map<Long, GpStatus> gpSurveyStatusByToken(Set<Long> gpIds, Integer surveyYear) {
        Map<Long, GpStatus> gpStatusMap = new HashMap<>();
        List<PanchayatMaster> gpData = panchayatDataRepository.findAllById(gpIds);
        List<GpApplications> gpApps = gpApplicationsRepository.findAllGPbyId(gpIds, surveyYear);
        List<Long> gpAppIds = gpApps.stream().map(GpApplications::getGpId).collect(Collectors.toList());

        if (gpData.size() > 0) {
            gpData.forEach(gp -> {
                final GpStatus gpStatusList = new GpStatus(gp.getPanchayatId(), -1);
                gpStatusMap.put(gp.getPanchayatId(), gpStatusList); //-1 means UNKNOWN or NOT SET to be moved to constants
            });
        }
        for (Map.Entry<Long, GpStatus> gpStatusEntry : gpStatusMap.entrySet()) {
            if (gpAppIds.contains(gpStatusEntry.getKey())) {
                GpStatus temp = gpStatusEntry.getValue();
                temp.setGpSurveyStatus(1);
            }
        }
        return gpStatusMap;

    }

    public Map<Long, List<VoStatus>> voSurveyStatus(Long gpId) {
        Map<Long, List<VoStatus>> voStatusMap = new HashMap<>();
        List<FederationProfiles> voProfilesList = voRepository.getAllVOsByGP(gpId);
        Set<Long> voIds = voProfilesList.stream().map(FederationProfiles::getFederationId).collect(Collectors.toSet());
        List<VoApplications> voApps = voApplicationsRepository.findVoByVoId(voIds);
        List<VoStatus> voStatusList = new ArrayList<VoStatus>();
        if (!voProfilesList.isEmpty()) {
            voProfilesList.forEach(voProfile -> {
                voStatusList.add(new VoStatus(voProfile.getFederationId(), -1));
                voStatusMap.put(gpId, voStatusList); //-1 means UNKNOWN or NOT SET to be moved to constants
            });
            List<Long> voAppId = voApps.stream().map(VoApplications::getVoId).collect(Collectors.toList());
            if (!voAppId.isEmpty()) {
                List<VoStatus> voStatusLis = voStatusMap.get(gpId).stream().map(changeStatus -> {
                    VoStatus s = new VoStatus(changeStatus);
                    if (voAppId.contains(changeStatus.getVoId())) {
                        s.setVoId(changeStatus.getVoId());
                        s.setVoSurveyStatus(1);
                    } else {
                        s.setVoId(changeStatus.getVoId());
                        s.setVoSurveyStatus(-1);
                    }
                    return s;

                }).collect(Collectors.toList());
                voStatusMap.put(gpId, voStatusLis);
            }
        }
        return voStatusMap;

    }

    public ShgProfiles getShgProfile(Long shgId) {
        Optional<ShgProfiles> shgPro = shgRepository.findById(shgId);
        if (!shgPro.isPresent()) {
            throw new IllegalArgumentException("No shg Info Found");
        }
        return shgPro.get();
    }

    public PanchayatMaster findGpById(Long gpId) {
        return panchayatDataRepository.getGpByGpId(gpId);
    }
}
