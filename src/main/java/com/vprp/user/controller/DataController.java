package com.vprp.user.controller;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

import com.vprp.user.data.dto.*;
import com.vprp.user.dto.CBOs;
import com.vprp.user.dto.GpStatus;
import com.vprp.user.dto.ShgStatus;
import com.vprp.user.dto.VoStatus;
import com.vprp.user.entity.*;
import com.vprp.user.model.GpSurveyStatusReq;
import com.vprp.user.model.ShgSurveyStatusReq;
import com.vprp.user.model.UserResponseModel;
import com.vprp.user.services.UserCBOService;
import com.vprp.user.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vprp.user.payload.UploadRequest;
import com.vprp.user.services.DataService;
import com.vprp.user.services.UserServices;

@RestController
@RequestMapping("/offline")
public class DataController {
    @Autowired
    DataService dataService;

    @Autowired
    UserServices userService;

    @Autowired
    UserCBOService userCBOService;

    @GetMapping("/masterdata/{state-id}")
    public ResponseEntity<SchemeData> getApplicationPermissionById(@PathVariable("state-id") Long stateId) {
        try {
            if (stateId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(dataService.getSchemeDataForOffline(stateId,null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vprpworkpacket/{role-code}/{user-id}")
    public ResponseEntity<ShgVprpCRPWorkPacket> downloadData(@PathVariable("role-code") String roleCode,
                                                             @PathVariable("user-id") Long userId) {
        try {

            if (userId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (roleCode == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            ShgVprpCRPWorkPacket shgMembersResponseList = dataService.getAllShgs(roleCode, userId);
            return new ResponseEntity<>(shgMembersResponseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/messagebundle/{lang-code}")
    public ResponseEntity<MessageBundleResponse> messageBundle(@PathVariable("lang-code") char[] langCode) {
        try {

            MessageBundleResponse messageBundle = dataService.getAllMsgByCodeBundle(langCode);
            return new ResponseEntity<>(messageBundle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{role-code}/upload/{is-override}")
    public ResponseEntity<UploadListResponse> uploadData(@PathVariable("is-override") Boolean override,
                                                         @RequestAttribute("USER_SESSION") Map userSession, @PathVariable("role-code") String roleCode, @RequestBody UploadRequest uploadRequest) {
        UploadListResponse uploadRes = new UploadListResponse();
        try {
            if (roleCode == null) {
                uploadRes.setStatusCode(HttpStatus.BAD_REQUEST.value());
                uploadRes.setStatusMessage("Invalid Request");
            }
            String loginId = userSession.get("LOGIN_ID").toString();
            Long userId = userService.getUserIdByLoginId(loginId);
            uploadRes = dataService.uploadData(uploadRequest, roleCode, override, userId);
            return new ResponseEntity<>(uploadRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{role-code}/upload/all/{is-override}")
    public ResponseEntity<List<UploadListResponse>> uploadDataAll(@PathVariable("is-override") Boolean override, @RequestAttribute("USER_SESSION") Map userSession,
                                                                  @RequestBody List<UploadRequest> uploadRequestList, @PathVariable("role-code") String roleCode) {
        UploadListResponse uploadRes = new UploadListResponse();
        try {
            if (roleCode == null) {
                uploadRes.setStatusCode(HttpStatus.BAD_REQUEST.value());
                uploadRes.setStatusMessage("Invalid Request");
            }
            String loginId = userSession.get("LOGIN_ID").toString();
            Long userId = userService.getUserIdByLoginId(loginId);
            List<UploadListResponse> shgApplications = dataService.uploadDataAll(uploadRequestList, roleCode, override, userId);

            return new ResponseEntity<>(shgApplications, HttpStatus.OK);
        }catch (InvalidParameterException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/vprp/{role-code}/download/all")
    public ResponseEntity<VprpCRPMobileUserWorkPacket> downloadOfflineDataForUser(
            @RequestAttribute("USER_SESSION") Map userSession, @PathVariable("role-code") String roleCode,
            @RequestBody DownloadAllRequest downloadAllRequest) {
        VprpCRPMobileUserWorkPacket shgVprpCRPMobileUserWorkPacket = new VprpCRPMobileUserWorkPacket();

        try {

//            TODO Data set atleast have one data
            if (roleCode == null) {
                shgVprpCRPMobileUserWorkPacket.setStatusCode(HttpStatus.BAD_REQUEST.value());
                shgVprpCRPMobileUserWorkPacket.setStatusMessage("Invalid role");
                return new ResponseEntity<>(shgVprpCRPMobileUserWorkPacket,
                        HttpStatus.BAD_GATEWAY);
            } else {
                String loginId = userSession.get("LOGIN_ID").toString();
                Boolean isValidUser = userService.verifyUserWithRoleAndApp(downloadAllRequest.getUserId(), loginId,
                        roleCode, AppConstant.APP_VPRP);
                if (isValidUser) {

                    if (roleCode.equals(AppConstant.ROLE_VPRP_VO)) {
                        if (!downloadAllRequest.getDataset().contains("VO_VPRP_CRP_WORK_PACKET")
                                || downloadAllRequest.getSurveyYear() == null
                                && downloadAllRequest.getIncludeSurveyData() == null) {
                            // TODO
                            shgVprpCRPMobileUserWorkPacket.setStatusCode(HttpStatus.BAD_REQUEST.value());
                            shgVprpCRPMobileUserWorkPacket.setStatusMessage("Invalid data set");
                            return new ResponseEntity<>(shgVprpCRPMobileUserWorkPacket,
                                    HttpStatus.BAD_REQUEST);
                        }
                        VprpCRPMobileUserWorkPacket voVprpCRPMobileUserWorkPacket ;
                        voVprpCRPMobileUserWorkPacket = dataService.voDownload(downloadAllRequest.getUserId(), roleCode,
                                downloadAllRequest.getLangCodes(), downloadAllRequest.getDataset(),
                                downloadAllRequest.getIncludeSurveyData(), downloadAllRequest.getSurveyYear(), downloadAllRequest.getShgIdsToDownload());
                        return new ResponseEntity<>(voVprpCRPMobileUserWorkPacket,
                                HttpStatus.OK);
                    } else if (roleCode.equals(AppConstant.ROLE_VPRP_SHG)) {
                        if (!downloadAllRequest.getDataset().contains("SHG_VPRP_CRP_WORK_PACKET")) {
                            shgVprpCRPMobileUserWorkPacket.setStatusCode(HttpStatus.BAD_REQUEST.value());
                            shgVprpCRPMobileUserWorkPacket.setStatusMessage("Invalid data set");
                            return new ResponseEntity<>(shgVprpCRPMobileUserWorkPacket,
                                    HttpStatus.BAD_REQUEST);
                        }
                        shgVprpCRPMobileUserWorkPacket = dataService.shgDownload(downloadAllRequest.getUserId(),
                                roleCode, downloadAllRequest.getLangCodes(), downloadAllRequest.getDataset());
                        return new ResponseEntity<>(shgVprpCRPMobileUserWorkPacket,
                                HttpStatus.OK);

                    }
                    return new ResponseEntity<>(shgVprpCRPMobileUserWorkPacket,
                            HttpStatus.OK);

                } else {
                    shgVprpCRPMobileUserWorkPacket.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    shgVprpCRPMobileUserWorkPacket.setStatusMessage("Invalid credentials");
                    return new ResponseEntity<>(shgVprpCRPMobileUserWorkPacket,
                            HttpStatus.BAD_REQUEST);
                }
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/vprp/gp_vprp_crp/download/all")
    public ResponseEntity<VprpCRPMobileUserGPWorkPacket> downloadOfflineDataForGPUser(
            @RequestAttribute("USER_SESSION") Map userSession,
            @RequestBody DownloadGPAllRequest downloadAllRequest) {
        VprpCRPMobileUserGPWorkPacket gpDownloadData = new VprpCRPMobileUserGPWorkPacket();

        try {
//            TODO Data set atleast have one data
                String loginId = userSession.get("LOGIN_ID").toString();
                Boolean isValidUser = userService.verifyUserWithRoleAndApp(downloadAllRequest.getUserId(), loginId,
                        AppConstant.ROLE_VPRP_GP, AppConstant.APP_VPRP);
                if (isValidUser) {
                        if (!downloadAllRequest.getDataset().contains("GP_VPRP_CRP_WORK_PACKET")
                                || downloadAllRequest.getSurveyYear() == null
                                && downloadAllRequest.getIncludeSurveyData() == null) {
                            // TODO
                            gpDownloadData.setStatusCode(HttpStatus.BAD_REQUEST.value());
                            gpDownloadData.setStatusMessage("Invalid data set");
                            return new ResponseEntity<>(gpDownloadData,
                                    HttpStatus.BAD_REQUEST);
                        }
                    gpDownloadData = dataService.gpDownload(downloadAllRequest.getUserId(), AppConstant.ROLE_VPRP_GP,
                                downloadAllRequest.getLangCodes(), downloadAllRequest.getDataset(),
                                downloadAllRequest.getIncludeSurveyData(), downloadAllRequest.getSurveyYear(), downloadAllRequest.getGpIdsToDownload());
                        gpDownloadData.setStatusMessage("SUCCESS");
                        gpDownloadData.setStatusCode(HttpStatus.OK.value());
                        return new ResponseEntity<>(gpDownloadData,
                                HttpStatus.OK);

                } else {
                    gpDownloadData.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    gpDownloadData.setStatusMessage("Not Valid GP user");
                    return new ResponseEntity<>(gpDownloadData,
                            HttpStatus.BAD_REQUEST);
                }


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//   SHG Download 
    @GetMapping("/download/{role-code}/{shg-id}")
    public ResponseEntity<ShgProfiles> shgProfile(@PathVariable("role-code") String roleCode,
                                                  @PathVariable("shg-id") Long shgId) {
        try {
            if (shgId < 0) {
                throw new IllegalArgumentException("Invalid Shg Id");
            }
            ShgProfiles shgProfile = dataService.getShgProfile(shgId);
            return new ResponseEntity<>(shgProfile, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // VO download
    @GetMapping("/download/vo_vprp_crp/{vo-id}")
    public ResponseEntity<FederationProfiles> voProfile(
            @PathVariable("vo-id") Long voId) {
        try {
            if (voId < 0) {
                throw new IllegalArgumentException("Invalid Vo Id");
            }
            FederationProfiles vo = dataService.getVoProfile(AppConstant.ROLE_VPRP_VO, 0L, voId);
            return new ResponseEntity<>(vo, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/download/vo_vprp_crp")
    public ResponseEntity<List<FederationProfiles>> voProfileByIds(
            @RequestBody List<Long> voIds) {
        try {
            if (voIds.size() < 0) {
                throw new IllegalArgumentException("Invalid Params");
            }
            List<FederationProfiles> vo = dataService.getVoProfiles(voIds);
            return new ResponseEntity<>(vo, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // GP download
//    @GetMapping("/download/gp/{gp-id}")
//    public ResponseEntity<List<VoApplications>> gpDownload(@PathVariable("gp-id") Long gpId) {
//        try {
//            if (gpId < 0) {
//                throw new IllegalArgumentException("Invalid GP Id");
//            }
//            List<VoApplications> voAppList = dataService.downloadAllVOs(gpId);
//
//            return new ResponseEntity<>(voAppList, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }  catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    // GP details
    @GetMapping("/download/gp/{gp-id}")
    public ResponseEntity<PanchayatMaster> gpDetails(@PathVariable("gp-id") Long gpId) {
        try {
            if (gpId < 0) {
                throw new IllegalArgumentException("Invalid GP Id");
            }
//            List<VoApplications> voAppList = dataService.downloadAllVOs(gpId);
            PanchayatMaster gpDetail = dataService.findGpById(gpId);

            return new ResponseEntity<PanchayatMaster>(gpDetail, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/details/vo/by/gp")
    public ResponseEntity<List<FederationProfiles>> gpDetails(@RequestBody List<Long> gpIds) {
        try {
            if (gpIds.size() <= 0) {
                throw new IllegalArgumentException("Invalid GP Ids");
            }
            List<FederationProfiles> voList= new ArrayList<>();
            for (Long gp: gpIds){
                List<FederationProfiles> gpVoDetails = dataService.gpDetails(gp);
                voList.addAll(gpVoDetails);
            }


            return new ResponseEntity<>(voList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    // VO Survey Status
    @GetMapping("/vo_vprp_crp/{vo-id}/shg/surveystatus")
    public ResponseEntity<?> shgSurveyStatus(@RequestAttribute("USER_SESSION") Map userSession,
                                             @PathVariable("vo-id") Long voId) {
        try {
            if (voId < 0) {
                throw new IllegalArgumentException("Invalid Vo Id");
            }
            String loginId = userSession.get("LOGIN_ID").toString();
            List<Long> validateVoIds = new ArrayList<Long>();
            validateVoIds.add(voId);
            Boolean isValidUser = userService.verifyUserCBOsWithRoleAndApp(loginId, AppConstant.ROLE_VPRP_VO
                    , AppConstant.APP_VPRP, validateVoIds);

            if (isValidUser) {
                Map<Long, List<ShgStatus>> shgSurveyStatus = dataService.shgSurveyStatus(voId);

                return new ResponseEntity<>(shgSurveyStatus, HttpStatus.OK);
            } else {
                throw new Exception("INVALID_USER_ACCESS");
            }
        } catch (Exception e) {
            UserResponseModel userResponse = new UserResponseModel();
            userResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
            userResponse.setStatusMessage(e.getMessage());

            return new ResponseEntity<UserResponseModel>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }
    //  VO Survey Status User
    @GetMapping("/vo_vprp_crp/shg/surveystatus")
    public ResponseEntity<?> shgSurveyStatusByUser(@RequestAttribute("USER_SESSION") Map userSession) {
        try {
            String loginId = userSession.get("LOGIN_ID").toString();
            User currentUser = userService.getUserByLoginId(loginId);
            List<Map<Long, List<ShgStatus>>> shgSurveyStatusList = new ArrayList<>();

            Boolean isValidUser = userService.verifyUserWithRoleAndApp(currentUser.getId(), loginId, AppConstant.ROLE_VPRP_VO, AppConstant.APP_VPRP);

            if (isValidUser) {

                List<CBOs> userCBODtos = userCBOService.getAllCBOsByRole(203L, currentUser.getId(), AppConstant.VO_STATUS_CODE);
                Set<Integer[]> voIdsList = userCBODtos.stream().map(CBOs::getCboId).collect(Collectors.toSet());
                List<Long> voIds = new ArrayList<>();
                for(Integer[] vl: voIdsList){
                    for(Integer v:vl){
                        voIds.add(Long.valueOf(v));
                    }

                }
                for (Long v:  voIds){
                    Map<Long, List<ShgStatus>> shgSurveyStatus = dataService.shgSurveyStatus(v);
                    shgSurveyStatusList.add(shgSurveyStatus);
                }


                return new ResponseEntity<>(shgSurveyStatusList, HttpStatus.OK);
            } else {
                throw new Exception("INVALID_USER_ACCESS");
            }
        } catch (Exception e) {
            UserResponseModel userResponse = new UserResponseModel();
            userResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
            userResponse.setStatusMessage(e.getMessage());

            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }

    // Vo Survey Status

    @GetMapping("/gp_vprp_crp/{gp-id}/vo/surveystatus")
    public ResponseEntity<?> voSurveyStatus(@RequestAttribute("USER_SESSION") Map userSession,
                                            @PathVariable("gp-id") Long gpId) {
        try {
            if (gpId < 0) {
                throw new IllegalArgumentException("Invalid Vo Id");
            }
            String loginId = userSession.get("LOGIN_ID").toString();
            List<Long> validateVoIds = new ArrayList<>();
            validateVoIds.add(gpId);
            Boolean isValidUser = userService.verifyUserGPWithRoleAndApp(loginId, AppConstant.ROLE_VPRP_GP
                    , AppConstant.APP_VPRP);

            if (isValidUser) {
                Map<Long, List<VoStatus>> shgSurveyStatus = dataService.voSurveyStatus(gpId);

                return new ResponseEntity<>(shgSurveyStatus, HttpStatus.OK);
            } else {
                throw new Exception("INVALID_USER_ACCESS");
            }
        } catch (Exception e) {
            UserResponseModel userResponse = new UserResponseModel();
            userResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
            userResponse.setStatusMessage(e.getMessage());

            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }

    // Gp Survey Status Many
    @PostMapping("/gp_vprp_crp/vo/surveystatus")
    public ResponseEntity<?> voSurveyStatusAll(@RequestAttribute("USER_SESSION") Map userSession, @RequestBody List<Long> gpIds) {
        Map<Long, List<VoStatus>> shgSurveyStatus = new HashMap<>();
        try {
            if (gpIds == null) {
                throw new IllegalArgumentException("Invalid Vo Id");
            }
            String loginId = userSession.get("LOGIN_ID").toString();
            Boolean isValidUser = userService.verifyUserGPWithRoleAndApp(loginId, AppConstant.ROLE_VPRP_GP
                    , AppConstant.APP_VPRP);

            if (isValidUser) {
                for(Long g: gpIds){
                    Map<Long, List<VoStatus>>  tempSurveyStatus = dataService.voSurveyStatus(g);
                    if(tempSurveyStatus!=null)
                    tempSurveyStatus.forEach((key, value) ->shgSurveyStatus.put(key,value));

                }
                return new ResponseEntity<>(shgSurveyStatus, HttpStatus.OK);
            } else {
                throw new Exception("INVALID_USER_ACCESS");
            }
        } catch (Exception e) {
            UserResponseModel userResponse = new UserResponseModel();
            userResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
            userResponse.setStatusMessage(e.getMessage());

            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }

    // SHG Survey Status

    @PostMapping("/{role-code}/surveystatus")
    public ResponseEntity<?> shgSurveyStatusByToken(@RequestAttribute("USER_SESSION") Map userSession,@PathVariable("role-code") String roleCode,
                                                    @RequestBody ShgSurveyStatusReq surveyYear) {
        try {
            if (surveyYear.getSurveyYear() < 0) {
                throw new IllegalArgumentException("Invalid Survey Year");
            }
            if(!Objects.equals(roleCode, AppConstant.ROLE_VPRP_SHG)){
                throw new IllegalArgumentException("Invalid Role Code for SHG user");
            }
            String loginId = userSession.get("LOGIN_ID").toString();
            List<Long> validateVoIds = new ArrayList<>();
            Boolean isValidUser = userService.verifyUserCBOsWithRoleAndApp(loginId, AppConstant.ROLE_VPRP_SHG
                    , AppConstant.APP_VPRP, validateVoIds);

            if (isValidUser) {
                User user = userService.getUserByLoginId(loginId);
                Long userId = user.getId();
                List<CBOs> userCBOs = userCBOService.getAllCBOsByRole(202L, userId, AppConstant.SHG_STATUS_CODE);
                List<Long> shgs = new ArrayList<>();
                for(CBOs cbo: userCBOs){
                    for(Integer shgCBOIds: cbo.getCboId()){
                        shgs.add(Long.valueOf(shgCBOIds));
                    }
                }

                Map<Long, ShgStatus> shgSurveyStatus = dataService.shgSurveyStatusByToken(shgs, surveyYear.getSurveyYear());

                return new ResponseEntity<>( shgSurveyStatus,HttpStatus.OK);
            } else {
                throw new Exception("INVALID_USER_ACCESS");
            }
        } catch (Exception e) {
            UserResponseModel userResponse = new UserResponseModel();
            userResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
            userResponse.setStatusMessage(e.getMessage());

            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/vo_vprp_crp/surveystatus")
    public ResponseEntity<?> voSurveyStatusByToken(@RequestAttribute("USER_SESSION") Map userSession,
                                                    @RequestBody ShgSurveyStatusReq surveyYear) {
        try {
            if (surveyYear.getSurveyYear() < 0) {
                throw new IllegalArgumentException("Invalid Survey Year");
            }
            String loginId = userSession.get("LOGIN_ID").toString();
            List<Long> validateVoIds = new ArrayList<>();
            Boolean isValidUser = userService.verifyUserCBOsWithRoleAndApp(loginId, AppConstant.ROLE_VPRP_VO
                    , AppConstant.APP_VPRP, validateVoIds);

            if (isValidUser) {
                User user = userService.getUserByLoginId(loginId);
                Long userId = user.getId();
                List<CBOs> userCBOs = userCBOService.getAllCBOsByRole(203L, userId, AppConstant.VO_STATUS_CODE);
                List<Long> vos = new ArrayList<>();
                for(CBOs cbo: userCBOs){
                    for(Integer shgCBOIds: cbo.getCboId()){
                        vos.add(Long.valueOf(shgCBOIds));
                    }
                }

                Map<Long, VoStatus> voSurveyStatus = dataService.voSurveyStatusByToken(vos, surveyYear.getSurveyYear());

                return new ResponseEntity<>( voSurveyStatus,HttpStatus.OK);
            } else {
                throw new Exception("INVALID_USER_ACCESS");
            }
        } catch (Exception e) {
            UserResponseModel userResponse = new UserResponseModel();
            userResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
            userResponse.setStatusMessage(e.getMessage());

            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }
    //Gp User
    @PostMapping("/gp_vprp_crp/surveystatus")
    public ResponseEntity<?> gpSurveyStatusByToken(@RequestAttribute("USER_SESSION") Map userSession,
                                                    @RequestBody GpSurveyStatusReq surveyYear) {
        try {
            if (surveyYear.getSurveyYear() < 0) {
                throw new IllegalArgumentException("Invalid Survey Year");
            }
            String loginId = userSession.get("LOGIN_ID").toString();
            Long userId =userService.getUserIdByLoginId(loginId);

            Boolean isValidUser = userService.verifyUserWithRoleAndApp(userId, loginId, AppConstant.ROLE_VPRP_GP
                    , AppConstant.APP_VPRP);

            if (isValidUser) {
                List<Integer[]> gpIds =userService.getAllGPByUserId(userId, 204L);
                Set<Long> gpIdsAll = new HashSet<>();
                for(Integer[] gpIdArr: gpIds){
                    for(Integer gpId: gpIdArr){
                        gpIdsAll.add(Long.valueOf(gpId));
                    }
                }

                Map<Long, GpStatus> gpSurveyStatus = dataService.gpSurveyStatusByToken(gpIdsAll, surveyYear.getSurveyYear());

                return new ResponseEntity<>( gpSurveyStatus,HttpStatus.OK);
            } else {
                throw new Exception("INVALID_USER_ACCESS");
            }
        } catch (Exception e) {
            UserResponseModel userResponse = new UserResponseModel();
            userResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
            userResponse.setStatusMessage(e.getMessage());

            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
