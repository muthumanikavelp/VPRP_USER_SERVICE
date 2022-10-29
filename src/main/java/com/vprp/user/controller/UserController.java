package com.vprp.user.controller;
import java.security.spec.KeySpec;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.json.JsonArray;
import javax.xml.bind.DatatypeConverter;

import com.vprp.user.utils.AppConstant;

import org.bouncycastle.asn1.bc.ObjectData;
import org.bouncycastle.jcajce.provider.symmetric.AES;
import org.exolab.castor.types.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.vprp.captcha.CaptchaUtils;
import com.vprp.user.dto.*;
import com.vprp.user.entity.*;
import com.vprp.user.enumurator.UserStatus;
import com.vprp.user.model.UserListResponseModel;
import com.vprp.user.model.UserResponseDtoModel;
import com.vprp.user.model.UserResponseModel;
import com.vprp.user.payload.CreateUserPayload;
import com.vprp.user.repository.UserRepository;
import com.vprp.user.services.UserServices;
import com.vprp.user.utils.JwtTokenUtil;

import cn.apiclub.captcha.Captcha;
import javassist.NotFoundException;
import javassist.bytecode.ByteArray;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;

//@CrossOrigin(origins = {"http://localhost:8081","http://localhost:4200","http://localhost:8090"})
@CrossOrigin()
@RestController
@RequestMapping("/security")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServices userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    @Value("${pg.ency.expiry}")
    private static long encydiffSeconds;
    
    @PostMapping("/user")
    public ResponseEntity<UserResponseModel> createUser(@RequestBody CreateUserPayload user) {
        UserResponseModel userResponse = new UserResponseModel();
        try {
            Boolean isExistUser = userService.isUserExist(user.getLoginId());

            if (isExistUser) {
                userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                userResponse.setData(null);
                userResponse.setStatusMessage("User already exist");
                return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
            } else {
                CreateUserPayload _user = userService.createUser(user);
                if (_user.getId() != null && _user.getId() > 0) {
                    userResponse.setStatusCode(HttpStatus.OK.value());
                    userResponse.setCratedUser(_user);
                    userResponse.setStatusMessage("SUCCESS");
                } else {
                    userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    userResponse.setData(null);
                    userResponse.setStatusMessage("FAILURE	");
                }
                return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
            }

        } catch (Exception e) {
            userResponse.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<UserResponseModel> updateUser(@RequestBody User user) {
        UserResponseModel userResponse = new UserResponseModel();
        try {
            User _user = userService.updateUser(user);
            if (_user.getId() > 0) {
                userResponse.setStatusCode(HttpStatus.OK.value());
                userResponse.setData(_user);
                userResponse.setStatusMessage("SUCCESS");
            } else {
                userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                userResponse.setData(null);
                userResponse.setStatusMessage("FAILURE	");
            }
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<UserResponseModel> resetUserPassword(@PathVariable("id") long id, @RequestBody User user) {
        UserResponseModel userResponse = new UserResponseModel();
        try {
            Integer status = userService.resetUserPassword(id, user.getPassword());
            if (status > 0) {
                userResponse.setStatusCode(HttpStatus.OK.value());
                userResponse.setData(null);
                userResponse.setStatusMessage("SUCCESS");
            } else {
                userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                userResponse.setData(null);
                userResponse.setStatusMessage("FAILURE");
            }
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserResponseModel> userLogin(@RequestBody UserAuthDto userAuthDto, @RequestHeader HttpHeaders headers) {
        UserResponseModel userResponse = new UserResponseModel();
        try {
            if (userService.verifyCaptcha(userAuthDto)) {
                User _user = new User();
                _user.setLoginId(userAuthDto.getLoginId());
                _user.setPassword(userAuthDto.getPassword());
                User _userCBOsDto = userService.userLogin(_user);
                if (_userCBOsDto != null && _userCBOsDto.getId() > 0
                        && _userCBOsDto.getStatus().equals(UserStatus.ACTIVE.toString())) {
                    String channel = headers.getFirst("channel");
                    if(channel!=null)
                        _userCBOsDto.setChannel(channel);
                    String token = jwtTokenUtil.generateToken(_userCBOsDto);
                    userResponse.setStatusCode(HttpStatus.OK.value());
                    userResponse.setData(_userCBOsDto);
                    userResponse.setToken(token);
                    userResponse.setStatusMessage("SUCCESS");
                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
                } else if (_userCBOsDto != null && _userCBOsDto.getStatus().equals(UserStatus.INACTIVE.toString())) {
                    userResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
                    userResponse.setStatusMessage("USER_NOT_ACTIVE");
                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
                } else if (_userCBOsDto != null && _userCBOsDto.getStatus().equals(UserStatus.DELETED.toString())) {
                    userResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                    userResponse.setStatusMessage("USER_NOT_FOUND");
                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
                } else if (_userCBOsDto == null) {
                    userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    userResponse.setStatusMessage("Invalid credentials");
                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
                } else {
                    userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    userResponse.setStatusMessage("Invalid credentials");
                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
                }
            } else {
                userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                userResponse.setStatusMessage("Invalid captcha");
                return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
            }

        } catch (Exception e) {
            userResponse.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

        }
    }

    @PostMapping("/user/authentication")
    public ResponseEntity<UserResponseDtoModel> userAuthentication(@RequestBody User user, @RequestHeader HttpHeaders headers) {
        UserResponseDtoModel userResponseDtoModel = new UserResponseDtoModel();

        try {
            User _user = userService.userLogin(user);
            if (_user == null) {
                userResponseDtoModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
                userResponseDtoModel.setStatusMessage("Invalid credentials");
                return new ResponseEntity<>(userResponseDtoModel, HttpStatus.CREATED);

            } else if (_user.getStatus().equals(UserStatus.INACTIVE.toString())) {
                userResponseDtoModel.setStatusCode(HttpStatus.FORBIDDEN.value());
                userResponseDtoModel.setStatusMessage("USER_NOT_ACTIVE");
                return new ResponseEntity<>(userResponseDtoModel, HttpStatus.BAD_REQUEST);
            } else if (_user.getStatus().equals(UserStatus.DELETED.toString())) {
                userResponseDtoModel.setStatusCode(HttpStatus.NOT_FOUND.value());
                userResponseDtoModel.setStatusMessage("USER_NOT_FOUND");
                return new ResponseEntity<>(userResponseDtoModel, HttpStatus.BAD_REQUEST);
            } else {
                String channel = headers.getFirst("channel");
                if(channel!=null)
                    _user.setChannel(channel);
                String token = jwtTokenUtil.generateToken(_user);
                UserDto userDto = userService.userLoginAuthentication(user);
                userResponseDtoModel.setStatusCode(HttpStatus.OK.value());
                userResponseDtoModel.setToken(token);
                userResponseDtoModel.setStatusMessage("SUCCESS");
                userResponseDtoModel.setData(userDto);
            }

            return new ResponseEntity<>(userResponseDtoModel, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/report/user/authentication")
    public ResponseEntity<UserResponseDtoModel> reportAuthentication(@RequestBody User user, @RequestHeader HttpHeaders headers) {
        UserResponseDtoModel userResponseDtoModel = new UserResponseDtoModel();

        try {
            Boolean isValid = userService.reportUserValidationLogin(user);
            if (!isValid) {
                userResponseDtoModel.setStatusCode(HttpStatus.FORBIDDEN.value());
                userResponseDtoModel.setStatusMessage("Invalid role for Access Report portal");
                return new ResponseEntity<>(userResponseDtoModel, HttpStatus.BAD_REQUEST);
            }
            User _user = userService.userLogin(user);
            if (_user == null) {
                userResponseDtoModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
                userResponseDtoModel.setStatusMessage("Invalid credentials");
                return new ResponseEntity<>(userResponseDtoModel, HttpStatus.BAD_REQUEST);

            } else if (_user.getStatus().equals(UserStatus.INACTIVE.toString())) {
                userResponseDtoModel.setStatusCode(HttpStatus.FORBIDDEN.value());
                userResponseDtoModel.setStatusMessage("USER_NOT_ACTIVE");
                return new ResponseEntity<>(userResponseDtoModel, HttpStatus.BAD_REQUEST);
            } else if (_user.getStatus().equals(UserStatus.DELETED.toString())) {
                userResponseDtoModel.setStatusCode(HttpStatus.NOT_FOUND.value());
                userResponseDtoModel.setStatusMessage("USER_NOT_FOUND");
                return new ResponseEntity<>(userResponseDtoModel, HttpStatus.CREATED);
            } else {
                String token = jwtTokenUtil.generateToken(_user);
                String channel = headers.getFirst("channel");
                if(channel!=null)
                    _user.setChannel(channel);
                UserDto userDto = userService.userLoginAuthentication(user);
                userResponseDtoModel.setStatusCode(HttpStatus.OK.value());
                userResponseDtoModel.setToken(token);
                userResponseDtoModel.setStatusMessage("SUCCESS");
                userResponseDtoModel.setData(userDto);
            }

            return new ResponseEntity<>(userResponseDtoModel, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//	This API Generally used to get token and validate the current user and send the application back

    @GetMapping("/user/info/token")
    public List<ApplicationsDto> getUserByToken(Authentication authentication) throws Exception {
        List<ApplicationsDto> rolesList;
        User loggedInUser = new User();
        try {
            String userName = authentication.getName();
            if (userName == null) {
                throw new NotFoundException("User not found");
            }

            loggedInUser.setLoginId(userName);
            rolesList = userService.userDetailsByToken(loggedInUser);
            if (rolesList == null) {
                throw new NotFoundException("User information not found");
            }

        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
        return rolesList;
    }

    @PostMapping("/user/vprp/authentication")
    public ResponseEntity<UserResponseDtoModel> mobileLogin(@RequestBody User user, @RequestHeader HttpHeaders headers) {
        UserResponseDtoModel userResponseDtoModel = new UserResponseDtoModel();
        String[] roleNameArray = {AppConstant.ROLE_VPRP_SHG, AppConstant.ROLE_VPRP_VO, AppConstant.ROLE_VPRP_GP};
        try {
            User _user = userService.userLogin(user);
            if (_user == null) {
                userResponseDtoModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
                userResponseDtoModel.setStatusMessage("Invalid credentials");
                return new ResponseEntity<>(userResponseDtoModel, HttpStatus.CREATED);

            } else {

                String channel = headers.getFirst("channel");
                if(channel!=null)
                    _user.setChannel(channel);
                String token = jwtTokenUtil.generateToken(_user);
                UserDto userDto = userService.userLoginAuthentication(user);
                if (userDto != null && userDto.getApplications().size() > 0) {
                    for (ApplicationsDto app : userDto.getApplications()) {
                        if (app.getApplicationCode().equals(AppConstant.APP_VPRP)) {
                            if (app.getRoles().size() > 0) {
                                for (ApplicationRolesDto role : app.getRoles()) {
                                    boolean userFound = Arrays.asList(roleNameArray).contains(role.getRoleName());
                                    if (userFound) {
                                        userResponseDtoModel.setStatusCode(HttpStatus.OK.value());
                                        userResponseDtoModel.setToken(token);
                                        userResponseDtoModel.setStatusMessage("SUCCESS");
                                        userResponseDtoModel.setData(userDto);
                                    } else {
                                        userResponseDtoModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
                                        userResponseDtoModel.setStatusMessage("Invalid credentials");
                                        return new ResponseEntity<>(userResponseDtoModel, HttpStatus.CREATED);
                                    }
                                }
                            }

                        }
                    }

                } else {
                    userResponseDtoModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    userResponseDtoModel.setStatusMessage("Invalid credentials");
                    return new ResponseEntity<>(userResponseDtoModel, HttpStatus.CREATED);
                }

            }

            return new ResponseEntity<>(userResponseDtoModel, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseModel> getUserById(@PathVariable("id") long id) throws Exception {
        User _userData = userService.getUserById(id);
        UserResponseModel userResponse = new UserResponseModel();
        try {
            if (_userData != null) {
                userResponse.setStatusCode(HttpStatus.OK.value());
                userResponse.setData(_userData);
                userResponse.setStatusMessage("SUCCESS");
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            } else {
                userResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                userResponse.setData(null);
                userResponse.setStatusMessage("FAILURE");
                return new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }

    }

    @GetMapping("/user/all")
    public ResponseEntity<UserListResponseModel> getAllUsers(@RequestParam(required = false) String userName,
                                                             @RequestParam(required = false) String loginId, @RequestParam(required = false) String phone,
                                                             @RequestParam(required = false) String stateId, @RequestParam(required = false) String districtId,
                                                             @RequestParam(required = false) String blockId, @RequestParam(required = false) String gpId,
                                                             @RequestParam(required = false) String villageId, @RequestParam(required = false) String appId,
                                                             @RequestParam(required = false) String appRoleId, @RequestParam(required = false) Integer page,
                                                             @RequestParam(required = false) Integer limit) {
        UserListResponseModel userList = new UserListResponseModel();
        try {
            StringBuilder query = new StringBuilder(" u.user_status <> 'DELETED' ");

            page = page != null ? page : 1;
            limit = limit != null ? limit : 10;
           // Map<String,String> params = new HashMap<>();
            List<Map<String , String>> params  = new ArrayList<Map<String,String>>();
            if (userName != null && !userName.equals("")) {
                query.append(" AND  u.user_name like '%'||:userName||'%'");
                Map<String,String> paramsMap = new HashMap<>();
                paramsMap.put("name","userName");
                paramsMap.put("value",userName);
                params.add(paramsMap);
            }

            if (loginId != null && !loginId.equals("")) {
                query.append(" AND  u.login_id like '%'||:loginId||'%'");
                Map<String,String> paramsMap = new HashMap<>();
                paramsMap.put("name","loginId");
                paramsMap.put("value",loginId);
                params.add(paramsMap);
            }

            if (phone != null && !phone.equals("")) {
                query.append(" AND  u.mob_num_primary like '%'||:phone||'%'");
                Map<String,String> paramsMap = new HashMap<>();
                paramsMap.put("name","phone");
                paramsMap.put("value",phone);
                params.add(paramsMap);
            }

            if (stateId != null && !stateId.equals("")) {
                query.append(" AND  geo.state = :stateId");
                Map<String,String> paramsMap = new HashMap<>();
                paramsMap.put("name","stateId");
                paramsMap.put("value",stateId);
                paramsMap.put("type","Long");
                params.add(paramsMap);
            }

            if (districtId != null && !districtId.equals("")) {
                query.append(" AND  geo.district = :districtId");
                Map<String,String> paramsMap = new HashMap<>();
                paramsMap.put("name","districtId");
                paramsMap.put("value",districtId);
                paramsMap.put("type","Long");
                params.add(paramsMap);
            }

            if (blockId != null && !blockId.equals("")) {
                query.append(" AND  geo.block = :blockId");
                Map<String,String> paramsMap = new HashMap<>();
                paramsMap.put("name","blockId");
                paramsMap.put("value",blockId);
                paramsMap.put("type","Long");
                params.add(paramsMap);
            }

            if (gpId != null && !gpId.equals("")) {
                query.append(" AND  geo.gram_panychayat @> = {:gpId}");
                Map<String,String> paramsMap = new HashMap<>();
                paramsMap.put("name","gpId");
                paramsMap.put("value",gpId);
                params.add(paramsMap);
            }

            if (villageId != null && !villageId.equals("")) {
                query.append(" AND  geo.village @> {:villageId}");
                Map<String,String> paramsMap = new HashMap<>();
                paramsMap.put("name","villageId");
                paramsMap.put("value",villageId);
                params.add(paramsMap);
            }

            if (appId != null && !appId.equals("")) {
                query.append(" AND  r.application_id = :appId");
                Map<String,String> paramsMap = new HashMap<>();
                paramsMap.put("name","appId");
                paramsMap.put("value",appId);
                paramsMap.put("type","Long");
                params.add(paramsMap);
            }

            if (appRoleId != null && !appRoleId.equals("")) {
                query.append(" AND role.application_role_id = :appRoleId");
                Map<String,String> paramsMap = new HashMap<>();
                paramsMap.put("name","appRoleId");
                paramsMap.put("value",appRoleId);
                paramsMap.put("type","Long");
                params.add(paramsMap);
            }

            Integer count = userService.getUserCountList(query.toString(),params);
            List<User> users = userService.getAllUser(query.toString(),params, page, limit);

            userList.setCount(count);
            userList.setStatusCode(HttpStatus.OK.value());
            userList.setData(users);
            userList.setStatusMessage("SUCCESS");
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userService.deleteUserUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/generateCaptcha")
    @ResponseBody
    public String generateCaptcha() {
        try {
            return this.userService.generateCaptcha();
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString();
        }
    }

    @PostMapping("/validateCaptcha")
    public Captcha ValidateCaptcha() {
        return CaptchaUtils.createCaptcha(240, 70);
    }

    @DeleteMapping("/user")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userService.deleteAllUser();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/users")
    public ResponseEntity<List<UserCBOsDto>> getAllUsersDTOsUser() {
        List<UserCBOsDto> userCBOsDtoList = userService.getAllCBOUser();
        return new ResponseEntity<>(userCBOsDtoList, HttpStatus.OK);
    }

    @PutMapping("/user/{id}/activate")
    public ResponseEntity<UserResponseModel> activeUserStatus(@PathVariable("id") long id) {
        UserResponseModel userResponse = new UserResponseModel();
        try {
            Integer updatedId = userService.markUserActive(id);
            if (updatedId > 0) {
                userResponse.setStatusCode(HttpStatus.OK.value());
                userResponse.setStatusMessage("USER ACTIVATE SUCCESSFULLY");
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            } else {
                userResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                userResponse.setStatusMessage("USER NOT FOUND");
                return new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}/deactivate")
    public ResponseEntity<UserResponseModel> deActiveUserStatus(@PathVariable("id") long id) {
        UserResponseModel userResponse = new UserResponseModel();
        try {
            Integer updatedId = userService.markUserInActive(id);
            if (updatedId > 0) {
                userResponse.setStatusCode(HttpStatus.OK.value());
                userResponse.setStatusMessage("USER DEACTIVATE SUCCESSFULLY");
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            } else {
                userResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                userResponse.setStatusMessage("USER NOT FOUND");
                return new ResponseEntity<>(userResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/logout")
    public ResponseEntity<UserResponseModel> logOutUser(@RequestHeader HttpHeaders headers) {
        UserResponseModel userResponse = new UserResponseModel();
        try {
                String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
                userService.logoutUserUser(token.replace("Bearer","").trim());
                userResponse.setStatusCode(HttpStatus.OK.value());
                userResponse.setStatusMessage("User Logged out");
                return new ResponseEntity<>(userResponse, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    /*PG Changes Start*/
    
    @PostMapping("/updateOtp")
    @ResponseBody
    public User UpdateOtp(@RequestParam String tomailId,Long userId) {
    	User objData;
        try {
        	Boolean isExistUser = userService.Checkuser(userId);
        	Boolean isExistPwdFloat = userService.PwdFloating(userId);
        	if(isExistUser) {
        		objData = new User();
        		objData.setRoleName("Couldn't find your NRLM Account.!");
        		return objData;
        	}
        	else if(isExistPwdFloat) {
        		objData = new User();
        		objData.setRoleName("Otp Already Sent.!");
        		return objData;
        	}else {
	        	String otp= new DecimalFormat("000000").format(new Random().nextInt(99999999));
	        	//this.sendEmail(tomailId, otp);
	        	String Result = this.userService.updateOtp(otp,userId);
	        	objData = new User();
	        	objData.setRoleName(Result);
	        	objData.setOTP(encrypt(otp));
	        	String MobileNo = this.userService.getuserMob(userId);
	        	objData.setMobNumPrimary(encrypt(MobileNo));
	            return objData;
            }
        } catch (Exception e) {
        	objData = new User();
        	objData.setRoleName(e.getMessage());
        	return objData;
        }
    }
    
    @GetMapping("/getUseremail")
    public User GetUseremail(@RequestParam String userId) throws NotFoundException {
    	try {
            return this.userService.GetUseremail(userId);
        } catch (Exception e) {
        	throw new NotFoundException(e.getMessage());
        }
    }
    
	@PostMapping("/sendMail")
	public void sendEmail(String toMailid,String Otp) throws NotFoundException {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        try {
	        msg.setTo(toMailid);
	        
	        msg.setSubject("Testing from Spring Boot");
	        msg.setText("Your OTP "+Otp);
	        javaMailSender.send(msg);
	        }
	        catch(Exception ex) {
	        	throw new NotFoundException(ex.getMessage());
	        }
	    }
	@PostMapping("/authenticate")
	public ResponseEntity<UserResponseDtoModel> authenticate(@RequestBody User user,@RequestHeader HttpHeaders headers) {
		User _user = new User();
		UserResponseDtoModel userResponseDtoModel = new UserResponseDtoModel();
		try {
		
		String channel = headers.getFirst("channel");
        if(channel!=null)
            _user.setChannel(channel);
       
        	_user.setLoginId(decrypt(user.getLoginId()));
        Boolean isExistUser = userService.CheckuserbyUserId(_user.getLoginId());
        if(isExistUser) {
	        userResponseDtoModel.setStatusCode(HttpStatus.OK.value());
	        //userResponseDtoModel.setToken(token);
	        userResponseDtoModel.setStatusMessage("Invalid Credentials.!");
	        //userResponseDtoModel.setUserInfo(userDetails);
	        return new ResponseEntity<>(userResponseDtoModel, HttpStatus.CREATED);
        }else {
	        String token = jwtTokenUtil.generateToken(_user);
	        User userDetails = this.userService.GetUseremail(decrypt(user.getLoginId()));
	        userResponseDtoModel.setStatusCode(HttpStatus.OK.value());
	        userResponseDtoModel.setToken(token);
	        userResponseDtoModel.setStatusMessage("SUCCESS");
	        userResponseDtoModel.setUserInfo(userDetails);
	        return new ResponseEntity<>(userResponseDtoModel, HttpStatus.CREATED);
        }

    } catch (Exception e) {
    	userResponseDtoModel.setStatusMessage(e.getMessage());
        return new ResponseEntity<>(userResponseDtoModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	}
	
	 @PutMapping("/user/{Oldpwd}/changepassword_")
	    public ResponseEntity<UserResponseModel> changeUserpassword_old(@PathVariable("Oldpwd") String Oldpwd, @RequestBody User user, String oldPwd) {
	        UserResponseModel userResponse = new UserResponseModel();
	        try {
	        	/*Decrypt Data*/
	        	Oldpwd = decrypt(Oldpwd);
	        	user.setLoginId(decrypt(user.getLoginId()));
	        	user.setPassword(decrypt(user.getPassword()));
	        	/*Decrypt Data End*/
	        	Boolean isExistUser = userService.Checkpassword(user.getLoginId(),Oldpwd);
	        	if (isExistUser) {
		        	Boolean isExistPwd = userService.Blockedpassword(user.getPassword());
		        	Boolean isExistHstryPwd = userService.PwdHistory(user.getId(),user.getPassword());
		        	Boolean isExistPwdExpiry = userService.PwdExpiry(user.getLoginId(), Oldpwd);
		        	
		        	if(isExistPwdExpiry) {
		        		userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                userResponse.setData(null);
		                userResponse.setStatusMessage("Your OTP has Expired.!");
		        	}else if (isExistPwd) {
		        		userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                userResponse.setData(null);
		                userResponse.setStatusMessage("Passwords cannot be common passwords.!");
		        	}else if(isExistHstryPwd){
		        		userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                userResponse.setData(null);
		                userResponse.setStatusMessage("Passwords must differ from Last 5 passwords.!");
		        	}else {
			            Integer status = userService.resetUserPassword(user.getId(), user.getPassword());
			            if (status > 0) {
			            	PG_UserPwd pwdslno = userService.getUSerPwd(user.getId());
			            	status = userService.updateUserPassword(user.getId(), user.getPassword(), pwdslno.getPasswordSlno());
			            	if (status > 0) {
			            		userResponse.setStatusCode(HttpStatus.OK.value());
				                userResponse.setData(null);
				                userResponse.setStatusMessage("SUCCESS");
			            	}else {
				                userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
				                userResponse.setData(null);
				                userResponse.setStatusMessage("Password History Update Fail.");
			                }
			            } else {
			                userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			                userResponse.setData(null);
			                userResponse.setStatusMessage("FAILURE");
			            }
		            }
	        	}
	        	else {
	        		userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
	                userResponse.setData(null);
	                userResponse.setStatusMessage("Password not Matched.!");
	        	}
	            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	            

	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 
	 @PutMapping("/user/changepassword")
	    public ResponseEntity<UserResponseModel> changeUserpassword(@RequestParam String Oldpwd, @RequestBody User user) {
	        UserResponseModel userResponse = new UserResponseModel();
	        try {
	        	/*Decrypt Data*/
	        	Oldpwd = decrypt(user.getOldpassword() );
	        	user.setLoginId(decrypt(user.getLoginId()));
	        	user.setPassword(decrypt(user.getPassword()));
	        	/*Decrypt Data End*/
	        	Boolean isExistUser = userService.Checkpassword(user.getLoginId(),Oldpwd);
	        	if (isExistUser) {
		        	Boolean isExistPwd = userService.Blockedpassword(user.getPassword());
		        	Boolean isExistHstryPwd = userService.PwdHistory(user.getId(),user.getPassword());
		        	Boolean isExistPwdExpiry = userService.PwdExpiry(user.getLoginId(), Oldpwd);
		        	
		        	if(isExistPwdExpiry) {
		        		userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                userResponse.setData(null);
		                userResponse.setStatusMessage("Your OTP has Expired.!");
		        	}else if (isExistPwd) {
		        		userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                userResponse.setData(null);
		                userResponse.setStatusMessage("Passwords cannot be common passwords.!");
		        	}else if(isExistHstryPwd){
		        		userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                userResponse.setData(null);
		                userResponse.setStatusMessage("Passwords must differ from Last 5 passwords.!");
		        	}else {
			            Integer status = userService.resetUserPassword(user.getId(), user.getPassword());
			            if (status > 0) {
			            	PG_UserPwd pwdslno = userService.getUSerPwd(user.getId());
			            	status = userService.updateUserPassword(user.getId(), user.getPassword(), pwdslno.getPasswordSlno());
			            	if (status > 0) {
			            		userResponse.setStatusCode(HttpStatus.OK.value());
				                userResponse.setData(null);
				                userResponse.setStatusMessage("SUCCESS");
			            	}else {
				                userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
				                userResponse.setData(null);
				                userResponse.setStatusMessage("Password History Update Fail.");
			                }
			            } else {
			                userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			                userResponse.setData(null);
			                userResponse.setStatusMessage("FAILURE");
			            }
		            }
	        	}
	        	else {
	        		userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
	                userResponse.setData(null);
	                userResponse.setStatusMessage("Password not Matched.!");
	        	}
	            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	            

	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @PostMapping("/user/pglogin")
	 public ResponseEntity<UserResponseModel> userpgLogin(@RequestBody UserAuthDto userAuthDto, @RequestHeader HttpHeaders headers) {
	        UserResponseModel userResponse = new UserResponseModel();
	        try {  
	        		User _user = new User();
	                _user.setLoginId(userAuthDto.getLoginId());
	                _user.setPassword(userAuthDto.getPassword());
	                Boolean isExistPwdExpiry = userService.PwdExpiry(_user.getLoginId(), _user.getPassword());
	                if(isExistPwdExpiry) {
	                	userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
	                    userResponse.setStatusMessage("Your OTP has Expired.!");
	                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	                }else {
		                User _userCBOsDto = userService.userLogin(_user);
		                if (_userCBOsDto != null && _userCBOsDto.getId() > 0
		                        && _userCBOsDto.getStatus().equals(UserStatus.ACTIVE.toString())) {
		                    String channel = headers.getFirst("channel");
		                    if(channel!=null)
		                        _userCBOsDto.setChannel(channel);
		                    String token = jwtTokenUtil.generateToken(_userCBOsDto);
		                    userResponse.setStatusCode(HttpStatus.OK.value());
		                    userResponse.setData(_userCBOsDto);
		                    userResponse.setToken(token);
		                    userResponse.setStatusMessage("SUCCESS");
		                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
		                } else if (_userCBOsDto != null && _userCBOsDto.getStatus().equals(UserStatus.INACTIVE.toString())) {
		                    userResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
		                    userResponse.setStatusMessage("USER_NOT_ACTIVE");
		                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
		                } else if (_userCBOsDto != null && _userCBOsDto.getStatus().equals(UserStatus.DELETED.toString())) {
		                    userResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		                    userResponse.setStatusMessage("USER_NOT_FOUND");
		                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
		                } else if (_userCBOsDto == null) {
		                    userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                    userResponse.setStatusMessage("Invalid credentials");
		                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
		                } else {
		                    userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                    userResponse.setStatusMessage("Invalid credentials");
		                    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
		                }
	                }

	        } catch (Exception e) {
	            userResponse.setStatusMessage(e.getMessage());
	            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

	        }
	    }
	 
	 @PostMapping("/master/getCLFList")
	 public List<PG_clfProfile> getClfdata() {
		 List<PG_clfProfile> pgClfprofile;
		 pgClfprofile = userService.getClfdata();
		 return pgClfprofile;
	 }
	 
	 @PostMapping("/master/getPGList")
	 public List<PG_pgprofile> getPGdata() {
		 List<PG_pgprofile> pgprofile;
		 pgprofile = userService.getPGdata();
		 return pgprofile;
	 }
	 
	 @PostMapping("/block/clf/{app-id}/{role-id}")
		public ResponseEntity<List<PG_clfProfile>> getAllClfsByGP(@RequestBody Long block,
				@PathVariable("app-id") Long appId, @PathVariable("role-id") Long roleId) throws Exception {
			try {

				if (appId == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				if (roleId == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}

				List<PG_clfProfile> clfProList = userService.getAllClfsByGP(block, appId, roleId);

				return new ResponseEntity<>(clfProList, HttpStatus.OK);

			} catch (Exception e) {
				throw new NotFoundException(e.getMessage());
			}

		}
	 
	 @PostMapping("/panchayat/pg/{app-id}/{role-id}")
		public ResponseEntity<List<PG_pgprofile>> getAllPgsByGP(@RequestBody Long block,
				@PathVariable("app-id") Long appId, @PathVariable("role-id") Long roleId) throws Exception {
			try {

				if (appId == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				if (roleId == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}

				List<PG_pgprofile> pgProList = userService.getAllPgsByGP(block, appId, roleId);

				return new ResponseEntity<>(pgProList, HttpStatus.OK);

			} catch (Exception e) {
				throw new NotFoundException(e.getMessage());
			}

		}
	 @PostMapping("/cbos/{role-id}")
	 public ResponseEntity<List<List<Integer>>> getCBOs(@RequestBody String CboLevel
			 ,@PathVariable("role-id") Long roleId) throws NotFoundException {
		 try {
			 List<List<Integer>> userCbosList = userService.getCBOs(roleId,CboLevel);
			 return new ResponseEntity<>(userCbosList, HttpStatus.OK);
		 }catch (Exception e) {
				throw new NotFoundException(e.getMessage());
			}
	 }
	 
	 @PostMapping("/getblock")
	 public BlockMaster getblock(String blockCode) {
		 try {
			 BlockMaster blockdata = userService.getblock(blockCode);
			 return blockdata;
		 }catch(Exception ex) {
			 throw new IllegalArgumentException(ex.getMessage());
		 }
		 
	 }
	 
	 @GetMapping("/getUserByLoginId")
	 public ResponseEntity<UserResponseModel> getUserInfo(@RequestParam String loginId,@RequestHeader HttpHeaders headers) {
		 UserResponseModel userResponse = new UserResponseModel();
		 try {
		 //User user = userService.getUserInfo(loginId);
		 //return user;
			 User _userCBOsDto = userService.getUserInfo(loginId);
             if (_userCBOsDto != null && _userCBOsDto.getId() > 0
                     && _userCBOsDto.getStatus().equals(UserStatus.ACTIVE.toString())) {
                 String channel = headers.getFirst("channel");
                 if(channel!=null)
                     _userCBOsDto.setChannel(channel);
                 String token = jwtTokenUtil.generateToken(_userCBOsDto);
                 userResponse.setStatusCode(HttpStatus.OK.value());
                 userResponse.setData(_userCBOsDto);
                 userResponse.setToken(token);
                 userResponse.setStatusMessage("SUCCESS");
                 return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
             } else if (_userCBOsDto != null && _userCBOsDto.getStatus().equals(UserStatus.INACTIVE.toString())) {
                 userResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
                 userResponse.setStatusMessage("USER_NOT_ACTIVE");
                 return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
             } else if (_userCBOsDto != null && _userCBOsDto.getStatus().equals(UserStatus.DELETED.toString())) {
                 userResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                 userResponse.setStatusMessage("USER_NOT_FOUND");
                 return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
             } else if (_userCBOsDto == null) {
                 userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                 userResponse.setStatusMessage("Invalid credentials");
                 return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
             } else {
            	 userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                 userResponse.setStatusMessage("Invalid credentials");
                 return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
             }
		 }catch(Exception e) {
			 userResponse.setStatusMessage(e.getMessage());
	         return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
		 }		 
	 }
	 
	 
	 @GetMapping("/getClfsByBlockCode")
	 public List<PG_clfProfile> getAllClfsByBlockCode(String blockcode) {
		List<PG_clfProfile> objclf = new ArrayList<PG_clfProfile>();
		objclf = userService.getAllClfsByBlockCode(blockcode);
		return objclf;
	 }
	 
	 @PostMapping("/createPg")
	 public  ResponseEntity<UserResponseModel> createPg(Long pgId,String pgCode,String statecode,String districtcode,String blockcode,String panchayatcode,String villagcode,String pgname,String usercode) {
		 UserResponseModel userResponse = new UserResponseModel();
	        try {
	        	Integer Result = userService.setPGDetails(pgId,pgCode,statecode,districtcode,blockcode,panchayatcode,villagcode,pgname,usercode);
	        	if(Result == 1) {
	        		userResponse.setStatusCode(HttpStatus.OK.value());
	                userResponse.setStatusMessage("SUCCESS");
	        	}else {
	        		userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    userResponse.setData(null);
                    userResponse.setStatusMessage("FAILURE");
	        	}
	        	
	        }catch(Exception ex) {
	        	userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
	        	userResponse.setStatusMessage(ex.getMessage());
	            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	 }
	 
	 @PostMapping("/user/againstRole")
	 public ResponseEntity<UserListResponseModel> geUsers(@RequestBody String roleName){
		 UserListResponseModel userList = new UserListResponseModel();
	        try {
	        	List<User> users = userService.getUserAgainstRole(roleName);
	        	userList.setStatusCode(HttpStatus.OK.value());
	            userList.setData(users);
	            userList.setStatusMessage("SUCCESS");
	            return new ResponseEntity<>(userList, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	        }
	 }
	 
	 
	    @PostMapping("/pgusercreation")
	    public ResponseEntity<UserResponseModel> PGcreateUser(@RequestBody CreateUserPayload user, @RequestParam String BlockCode, 
	    		@RequestParam String RoleCode, @RequestParam Integer PgId ) {
	        UserResponseModel userResponse = new UserResponseModel();
	        try {
	        	Boolean isExistMob = userService.isExistMob(user.getMobNumPrimary());
	        	if(!isExistMob) {
		            Boolean isExistUser = userService.isUserExist(user.getLoginId());
	
		            if (isExistUser) {
		                userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                userResponse.setData(null);
		                userResponse.setStatusMessage("User already exist");
		                return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
		            } else {
		                CreateUserPayload _user = userService.createUser(user);
		                if (_user.getId() != null && _user.getId() > 0) {
		                    userResponse.setStatusCode(HttpStatus.OK.value());
		                    userResponse.setCratedUser(_user);
		                    userResponse.setStatusMessage("SUCCESS");
		                } else {
		                    userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                    userResponse.setData(null);
		                    userResponse.setStatusMessage("FAILURE	");
		                }
		                return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
		            }
	            }else{
	            	 String Result = userService.pgusercreation(user,BlockCode,RoleCode,PgId);
	            	if(Result == "Success") {	            		
		            	userResponse.setStatusCode(HttpStatus.OK.value());
			            userResponse.setData(null);
			            userResponse.setStatusMessage(Result);
			            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
		             }else {
	            		userResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		                userResponse.setData(null);
		                userResponse.setStatusMessage("User already exist");
		                return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
	            	}
	            	
	            }

	        } catch (Exception e) {
	            userResponse.setStatusMessage(e.getMessage());
	            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	    
	   
	    // @PostMapping("/decrypt")
	    public static String decrypt(String ciphertext) throws Exception {
	    	
	        SecretKey secretKey = getSecretKey("o9szYIOq1rRMiouNhNvaq96lqUvCekxR");
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        String Data = new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)));
	        
	       /* SecretKey secretKey1 = getSecretKey1("o9szYIOq1rRMiouNhNvaq96lqUvCekxR");
	        Cipher cipher1 = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cipher1.init(Cipher.ENCRYPT_MODE, secretKey1);	       
	        String Data1 = new String(cipher1.doFinal(Base64.getEncoder().encode(Test.getBytes())));*/
	        
	        //String Data1 = encrypt("Muthu");
	        String keyForJS = Base64.getEncoder().encodeToString(KEY);
	        System.out.println("THIS KEY WILL BE USED FOR JS-SIDE = " + keyForJS);

	        String Datasplit[] = Data.split("~");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        LocalDateTime data = LocalDateTime.parse(Datasplit[1],formatter);
	        long diffInSeconds = java.time.Duration.between(data,LocalDateTime.now()).getSeconds();
	        System.out.println("diffInSeconds - " + diffInSeconds);
	        System.out.println("Datasplit[1] - " + Datasplit[1]);
	        System.out.println("Datasplit[0] - " + Datasplit[0]);
	        System.out.println("encydiffSeconds - " + encydiffSeconds);
	        if(diffInSeconds > encydiffSeconds) {
	        	return Datasplit[1];
	        }else {
	        	return Datasplit[0];
	        }
	        
	        // return new String(cipher.doFinal(base64Decode("ASDASDADS")));
	    }

	    public static SecretKey getSecretKey(String secretKey) throws Exception {
	        byte[] decodeSecretKey = Base64.getDecoder().decode(secretKey);
	      //  byte[] decodeSecretKey = DatatypeConverter.parseBase64Binary(secretKey);
	        //byte[] decodeSecretKey = base64Decode(secretKey);
	        return new SecretKeySpec(decodeSecretKey, 0, decodeSecretKey.length, "AES");
	    }
	    
	   /* public static SecretKey getSecretKey1(String secretKey) throws Exception {
	        byte[] decodeSecretKey = Base64.getEncoder().
	      //  byte[] decodeSecretKey = DatatypeConverter.parseBase64Binary(secretKey);
	        //byte[] decodeSecretKey = base64Decode(secretKey);
	        return new SecretKeySpec(decodeSecretKey, 0, decodeSecretKey.length, "AES");
	    }*/
	    
	    
	    public static final byte[] KEY = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'};

	    private static Cipher ecipher;
	    private static Cipher dcipher;

	    static {
	        try {
	            ecipher = Cipher.getInstance("AES");
	            SecretKeySpec eSpec = new SecretKeySpec(KEY, "AES");
	            ecipher.init(Cipher.ENCRYPT_MODE, eSpec);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }


	        try {
	            dcipher = Cipher.getInstance("AES");
	            SecretKeySpec dSpec = new SecretKeySpec(KEY, "AES");
	            dcipher.init(Cipher.DECRYPT_MODE, dSpec);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public static String encrypt(String value) {
	        byte[] b1 = value.getBytes();
	        byte[] encryptedValue;
	        try {
	            encryptedValue = ecipher.doFinal(b1);
	           // return Base64.encodeBase64String(encryptedValue);
	            return Base64.getEncoder().encodeToString(encryptedValue);
	        } catch (Exception e) {
	            throw new IllegalArgumentException(e);
	        }
	    }
	 
	 /*PG Changes End*/

}
