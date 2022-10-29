package com.vprp.user.services;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.vprp.user.config.TokenProvider;
import com.vprp.user.data.repository.UserSessionRepository;
import com.vprp.user.dto.*;
import com.vprp.user.entity.*;
import com.vprp.user.entity.User.roleApplication;
import com.vprp.user.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import com.google.common.hash.Hashing;
import com.vprp.captcha.CaptchaUtils;
import com.vprp.user.repository.ApplicationPermissionRepository;
import com.vprp.user.repository.ApplicationRepository;
import com.vprp.user.repository.ApplicationRolesRepository;
import com.vprp.user.repository.BlockDataRepository;
import com.vprp.user.repository.CaptchaRepository;
import com.vprp.user.repository.PG_UserPwdRepository;
import com.vprp.user.repository.PG_clfprofileRepository;
import com.vprp.user.repository.PG_pgprofileRepository;
import com.vprp.user.repository.PG_tpgprofileRepository;
import com.vprp.user.repository.PG_userpwdblockedRepository;
import com.vprp.user.repository.PanchayatDataRepository;
import com.vprp.user.enumurator.UserStatus;
import com.vprp.user.payload.CreateUserPayload;
import com.vprp.user.payload.CreateUserPwdPayload;
import com.vprp.user.repository.UserCBORepository;
import com.vprp.user.repository.UserRepository;
import com.vprp.user.repository.UserRoleGeographyRepository;
import com.vprp.user.repository.UserRoleRepository;
import com.vprp.user.repository.VillageDataRepository;
import com.vprp.user.utils.AppConstant;

import cn.apiclub.captcha.Captcha;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service()
public class UserServices {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	UserCBORepository userCBORepository;

	@Autowired
	UserRoleGeographyRepository userRoleGeographyRepository;

	@Autowired
	CaptchaRepository captchaRepository;

	@Autowired
	ApplicationRolesRepository applicationRolesRepository;

	@Autowired
	ApplicationRepository applicationRepository;

	@Autowired
	ApplicationPermissionRepository applicationPermissionRepository;

	@Autowired
	UserRoleRepository userRolesRepository;

	@Autowired
	UserSessionRepository userSessionRepository;

	@Autowired
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	@PersistenceContext
	EntityManager em1;

	@Autowired
	TokenProvider jwt;

	/* PG Application Start */

	@Autowired
	@Value("${pg.pwd.history}")
	private int pwdHistory;
	
	@Autowired
	@Value("${pg.application.Id}")
	private String AppId;
	
	@Autowired
	@Value("${pg.pwd.expiry}")
	private Long Expiry;

	@Autowired
	PG_UserPwdRepository pguserpwdRepository;

	@Autowired
	PG_userpwdblockedRepository pguserpwdblockedRepository;

	@Autowired
	PG_clfprofileRepository pgclfRepository;

	@Autowired
	PG_pgprofileRepository pgRepository;

	@Autowired
	BlockDataRepository blockRepository;

	@Autowired
	PG_tpgprofileRepository tpgRepository;
	
	@Autowired
	PanchayatDataRepository panchayatRepository;
	
	@Autowired
	VillageDataRepository villageRepository;

	/* PG Application End */

	@Transactional(rollbackFor = Exception.class)
	public CreateUserPayload createUser(CreateUserPayload user) {

		CreateUserPayload createUserPayload = new CreateUserPayload();
		String passwordHash = Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString();

		System.out.println(user.toString());
		Long Id = (long) 207;
		if (user.getRoles() != null) {

			user.getRoles().forEach(userRole -> {
				
				/*String sql = "select ar.application_id "
						+ "from nrlm_security.application_roles as ar "
						+ "where 1=1 and ar.id = " + userRole.getApplicationRoleId() +";";
				Query hQuery = em.createNativeQuery(sql, roleApplication.class);
				//ResultSet rs = (ResultSet) hQuery.getSingleResult();
				roleApplication id = (roleApplication) hQuery.getResultList(); 
				if(Obj.getApplication_id() == AppId){
					user.setPwdresetflag('Y');
				}else {
					
					user.setPwdresetflag('N');
				}*/
				if(userRole.getApplicationRoleId().equals((long) 207)) {
					user.setPwdresetflag('Y');
				}else if(userRole.getApplicationRoleId().equals((long) 208)) {
					user.setPwdresetflag('Y');
				}else if(userRole.getApplicationRoleId().equals((long) 209)) {
					user.setPwdresetflag('Y');
				}else if(userRole.getApplicationRoleId().equals((long) 210)) {
					user.setPwdresetflag('Y');					
				}else if(userRole.getApplicationRoleId().equals((long) 211)) {
					user.setPwdresetflag('Y');					
				}else if(userRole.getApplicationRoleId().equals((long) 212)) {
					user.setPwdresetflag('Y');					
				}else {
					user.setPwdresetflag('N');
				}
				
				user.setPwdexpireflag('N');
				
			});
		}
		
		User _user = userRepository.save(new User(user.getLoginId(), user.getUserName(), user.getMobNumPrimary(),
				user.getMobNumSecondary(), user.getEmail(), user.getDesignation(), UserStatus.ACTIVE.toString(),
				passwordHash, user.getPwdresetflag(), user.getPwdexpireflag()));

		final Long userId = _user.getId();

		/*
		 * PG Application Changes Start Changes Made For Password History Maintenance
		 * Purpose
		 */
		PG_UserPwd userPwd = new PG_UserPwd();
		userPwd.setUserId(_user.getId());
		userPwd.setPassword(passwordHash);
		userPwd = setUserPwd(userPwd);
		int Data = userRepository.updateChannel(_user.getId());

		/* PG Application Changes End */

		createUserPayload.setId(_user.getId());
		createUserPayload.setLoginId(_user.getLoginId());
		createUserPayload.setUserName(_user.getUserName());
		createUserPayload.setMobNumPrimary(_user.getMobNumPrimary());
		createUserPayload.setMobNumSecondary(_user.getMobNumSecondary());
		createUserPayload.setEmail(_user.getEmail());
		createUserPayload.setDesignation(_user.getDesignation());
		createUserPayload.setStatus(_user.getStatus());
		Date currentDate = new Date();
		List<UserCBOs> cboList = new ArrayList<>();
		List<UserRoles> roleList = new ArrayList<>();
		List<UserRoleGeography> assignedGeographyList = new ArrayList<>();

		if (user.getRoles() != null) {

			user.getRoles().forEach(userRole -> {

				Optional<ApplicationRoles> appRoles = applicationRolesRepository
						.findById(userRole.getApplicationRoleId());
				Long appRoleId = appRoles.get().getApplicationId();
				if (appRoles.isPresent() && appRoles.get().getCboAssignmentRule() == AppConstant.ALLOW_OVERLAP) {
					if (userRole != null)
						roleList.add(
								new UserRoles(userId, userRole.getApplicationRoleId(), UserStatus.ACTIVE.toString()));
					userRoleRepository.saveAll(roleList);

					if (userRole.getUserRoleGeographies() != null) {

						userRole.getUserRoleGeographies().forEach(geoInfo -> {
							Integer[] v = new Integer[] { 0 };
							if (geoInfo.getVillage() != null) {
								v = geoInfo.getVillage();
							}
							assignedGeographyList.add(new UserRoleGeography(_user.getId(), geoInfo.getState(),
									geoInfo.getDistrict(), geoInfo.getBlock(), geoInfo.getGrampPanchayat(), v,
									geoInfo.getNationalLevel(), userRole.getApplicationRoleId(), "ACTIVE"));

						});
						userRoleGeographyRepository.saveAll(assignedGeographyList);

					}

					if (userRole.getUserCBOs() != null) {
						userRole.getUserCBOs().forEach(cboInfo -> {

							cboList.add(new UserCBOs(_user.getId(), cboInfo.getCboLevel(), cboInfo.getCboIds(),
									currentDate, null, userRole.getApplicationRoleId(), "ACTIVE"));

						});
						userCBORepository.saveAll(cboList);
					}
				} else if (appRoles.isPresent()
						&& appRoles.get().getCboAssignmentRule() == AppConstant.RESTRICT_OVERLAP) {
					if (userRole != null)
						roleList.add(
								new UserRoles(userId, userRole.getApplicationRoleId(), UserStatus.ACTIVE.toString()));

					if (userRole.getUserRoleGeographies() != null) {

						userRole.getUserRoleGeographies().forEach(geoInfo -> {
							Integer[] v = new Integer[] { 0 };
							if (geoInfo.getVillage() != null) {
								v = geoInfo.getVillage();
							}
							assignedGeographyList.add(new UserRoleGeography(_user.getId(), geoInfo.getState(),
									geoInfo.getDistrict(), geoInfo.getBlock(), geoInfo.getGrampPanchayat(), v,
									geoInfo.getNationalLevel(), userRole.getApplicationRoleId(), "ACTIVE"));

						});

					}

					if (userRole.getUserCBOs() != null) {
						userRole.getUserCBOs().forEach(cboInfo -> {
							// check Assigned CBO or Not
							if (appRoles.get() == null) {
								throw new IllegalArgumentException("Invalid Role");
							}
							String roleName = appRoles.get().getRoleName();
							String cboLevel;
							// TODO need to change this implementation
							if (roleName.equals(AppConstant.ROLE_VPRP_SHG)) {
								cboLevel = "0";
							} else if (roleName.equals(AppConstant.ROLE_VPRP_VO)) {
								cboLevel = "1";
							} else {
								cboLevel = "-1";
							}
							Boolean isFound = false;
							if (!cboLevel.equals("-1")) {
								isFound = userCBORepository.isAlreadyAssingedCBO(cboInfo.getCboIds(), cboLevel,
										appRoleId);
							}

							if (!isFound) {
								cboList.add(new UserCBOs(_user.getId(), cboInfo.getCboLevel(), cboInfo.getCboIds(),
										currentDate, null, userRole.getApplicationRoleId(), "ACTIVE"));
							} else {
								throw new IllegalArgumentException("User CBOs Already Assigned");
							}

						});

					}
					userRoleRepository.saveAll(roleList);
					userRoleGeographyRepository.saveAll(assignedGeographyList);
					userCBORepository.saveAll(cboList);
				}

			});
		}

		return createUserPayload;

	}

	public Boolean isUserExist(String loginId) {

		User existringUser = userRepository.getUserByLoginId(loginId);
		if (existringUser != null) {
			if (existringUser.getId() > 0) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	public User updateUser(User user) {

		User updateUser = userRepository.findById(user.getId()).orElseThrow(IllegalArgumentException::new);
		if (updateUser != null) {

			updateUser.setMobNumPrimary(user.getMobNumPrimary());
			updateUser.setMobNumSecondary(user.getMobNumSecondary());
			updateUser.setEmail(user.getEmail());

			userRepository.save(updateUser);
		}
		return updateUser;
	}

	public User getUserByLoginId(String loginId) {
		User user = userRepository.getUserByLoginId(loginId);
		if (user == null) {
			return null;
		} else {

			user.setUserRoles(new ArrayList<>());
			return user;
		}

	}

	public User userLogin(User user) {
		String passwordHash = Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString();
		Optional<UserCBOsDto> _userCBOsDto = userRepository.getUserByLogin(user.getLoginId(), passwordHash);
		if (!_userCBOsDto.isPresent()) {
			return null;
		} else {
			User userDetails = this.getUserById(_userCBOsDto.get().getUserId());

			List<UserCBOs> userCBOs = userDetails.getUserCBOs();

			for (UserCBOs uCBOs : userCBOs) {
				ApplicationRolesDto appRoles = applicationRolesRepository
						.getApplicationRolesById(uCBOs.getApplicationRoleId());
				uCBOs.setApplicationRolesDto(appRoles);
			}

			return userDetails;
		}

	}

	public Boolean reportUserValidationLogin(User user) {
		String passwordHash = Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString();
		Optional<UserCBOsDto> _userCBOsDto = userRepository.getUserByLogin(user.getLoginId(), passwordHash);
		if (_userCBOsDto.isPresent()) {
			User userDetails = this.getUserById(_userCBOsDto.get().getUserId());
			for (UserRoles ur : userDetails.getUserRoles()) {
				ApplicationRolesDto ap = ur.getApplicationRole();
				Boolean isApprovedRole = Arrays.asList(AppConstant.REPORT_APPROVED_ROLES).contains(ap.getRoleName());
				if (isApprovedRole) {
					return true;
				}
			}
		}
		return false;
	}

	public UserDto userLoginAuthentication(User userdto) {
		String passwordHash = Hashing.sha256().hashString(userdto.getPassword(), StandardCharsets.UTF_8).toString();
		Optional<UserCBOsDto> _userCBOsDto = userRepository.getUserByLogin(userdto.getLoginId(), passwordHash);
		if (_userCBOsDto != null) {
			User userDetails = this.getUserById(_userCBOsDto.get().getUserId());

			UserDto userDto = new UserDto(userDetails.getId(), userDetails.getLoginId(), userDetails.getUserName(),
					userDetails.getMobNumPrimary(), userDetails.getStatus(), userDetails.getPassword());

			userDto.setApplications(this.userLoginAuthenticationModified(userdto));
			return userDto;
		} else {
			return null;
		}

	}

	public List<ApplicationsDto> userDetailsByToken(User userdto) {
		if (userdto != null) {
			return this.userLoginAuthenticationModified(userdto);
		}
		return null;
	}

	private List<ApplicationsDto> userLoginAuthenticationModified(User userdto) {

		List<ApplicationRoles> appRolesList = new ArrayList<>();

		User userDetails = userRepository.getUserByLoginId(userdto.getLoginId());

		Long userId = userDetails.getId();

		List<UserRoles> userRoles = userRolesRepository.getAllRoleByUserAppId(userDetails.getId());
		for (UserRoles ur : userRoles) {
			Optional<ApplicationRoles> appRoles = applicationRolesRepository.findById(ur.getApplicationRoleId());
			if (appRoles.isPresent()) {
				appRolesList.add(appRoles.get());
			}
		}

		HashMap<Long, List<ApplicationRoles>> appRoleMap = new HashMap<>();
		for (ApplicationRoles ar : appRolesList) {
			appRoleMap.put(ar.getApplicationId(), appRolesList);
		}

		List<ApplicationsDto> appicationList = new ArrayList<ApplicationsDto>();
		for (Map.Entry<Long, List<ApplicationRoles>> en : appRoleMap.entrySet()) {
			List<ApplicationRolesDto> appRoleDtoList = new ArrayList<ApplicationRolesDto>();
			ApplicationsDto applications = applicationRepository.getApplications(en.getKey());
			for (ApplicationRoles ar : en.getValue()) {
				Long appRoleId = ar.getId();

				ApplicationRolesDto appRoleDto = new ApplicationRolesDto();
				appRoleDto.setRoleId(ar.getId());
				appRoleDto.setRoleName(ar.getRoleName());
				appRoleDto.setStatus(ar.getStatus());
				appRoleDto.setMapToGeographics(ar.getMapToGeographics());
				appRoleDto.setCbosLevelsPermitted(ar.getCbosLevelsPermitted());
				appRoleDto.setGeoLevelPermitted(ar.getGeographyLevelsPermitted());
				// CBOs
				List<CBOs> cbos = userCBORepository.getAllCBOsByRole(appRoleId, userId);
				appRoleDto.setRolesCBO(cbos);
				// Permissions
				List<ApplicationPermissionsDto> appPermissionDto = applicationPermissionRepository
						.getPermissionAndActions(appRoleId);
				appRoleDto.setPermissions(appPermissionDto);

				// Role Geographics
				UserRoleGeography roleGeos = userRoleGeographyRepository.getAllGeos(userId, appRoleId);
				appRoleDto.setRoleGeos(roleGeos);

				appRoleDtoList.add(appRoleDto);

			}
			applications.setRoles(appRoleDtoList);

			appicationList.add(applications);

		}
		return appicationList;

	}

	@Transactional
	public User getUserById(Long id) {
		User userDetails = userRepository.findById(id).get();
		if (userDetails != null) {
			List<UserRoles> roles = userDetails.getUserRoles().stream().filter(
					role -> role.getUserStatus() != null && role.getUserStatus().equals(UserStatus.ACTIVE.toString()))
					.collect(Collectors.toList());
			userDetails.setUserRoles(roles);
			userDetails.getUserRoles().forEach((role) -> {
				if (role != null && role.getApplicationRoleId() != null) {
					ApplicationRolesDto applicationRole = applicationRolesRepository
							.getApplicationRolesById(role.getApplicationRoleId());
					role.setApplicationRoleDto(applicationRole);
				}
			});
		}
		return userDetails;
	}

	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public List<User> getAllUser(String query, List<Map<String, String>> params, Integer page, Integer limit) {
		Integer offset = (page - 1) * limit;
		String sql = "SELECT u.*,array_to_string(array_agg(a.application_name),',') as applicationName,array_to_string(array_agg(r.role_name),',') as roleName FROM nrlm_security.users u  "
				+ " LEFT JOIN nrlm_security.user_role role ON role.user_id = u.id AND  role.status!='DELETED'"
				+ " LEFT JOIN nrlm_security.application_roles r ON r.id = role.application_role_id"
				+ " LEFT JOIN nrlm_security.application a ON a.id = r.application_id "
				+ " LEFT JOIN nrlm_security.user_role_geographies geo ON geo.user_id = u.id "
				+ " LEFT JOIN nrlm_master.state_master state ON state.state_id = geo.state"
				+ " LEFT JOIN nrlm_master.district_master district ON district.district_id = geo.district"
				+ " LEFT JOIN nrlm_master.block_master block ON block.block_id = geo.block WHERE " + query
				+ " GROUP BY u.id " + " OFFSET :offset LIMIT :limit";
		Query hQuery = em.createNativeQuery(sql, User.class).setParameter("offset", offset).setParameter("limit",
				limit);
		for (Map<String, String> paramsMap : params) {
			// hQuery.setParameter(entry.getKey(),entry.getValue());
			if (paramsMap.containsKey("type") && paramsMap.get("type").equals("Long")) {
				hQuery.setParameter(paramsMap.get("name"), Long.parseLong(paramsMap.get("value")));
			} else {
				hQuery.setParameter(paramsMap.get("name"), paramsMap.get("value"));
			}
		}
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) hQuery.getResultList();
		// users = userList.stream().filter(distinctByKey(p ->
		// p.getId())).collect(Collectors.toList());
		return userList;
	}

	public Integer getUserCountList(String query, List<Map<String, String>> params) {
		String sql = "SELECT count(distinct u.id) FROM nrlm_security.users u "
				+ " INNER JOIN nrlm_security.user_role role ON role.user_id = u.id "
				+ " INNER JOIN nrlm_security.application_roles r ON r.id = role.application_role_id"
				+ " INNER JOIN nrlm_security.application a ON a.id = r.application_id "
				+ " LEFT JOIN nrlm_security.user_role_geographies geo ON geo.user_id = u.id "
				+ " LEFT JOIN nrlm_master.state_master state ON state.state_id = geo.state"
				+ " LEFT JOIN nrlm_master.district_master district ON district.district_id = geo.district"
				+ " LEFT JOIN nrlm_master.block_master block ON block.block_id = geo.block WHERE " + query
				+ " GROUP BY u.id ";
		Query hQuery = em.createNativeQuery(sql);
		for (Map<String, String> paramsMap : params) {
			// hQuery.setParameter(entry.getKey(),entry.getValue());
			if (paramsMap.containsKey("type") && paramsMap.get("type").equals("Long")) {
				hQuery.setParameter(paramsMap.get("name"), Long.parseLong(paramsMap.get("value")));
			} else {
				hQuery.setParameter(paramsMap.get("name"), paramsMap.get("value"));
			}
		}
		Integer count = hQuery.getResultList().size();
		return count;

	}

	public void deleteUserUser(Long id) {
		// Change if requires
		userCBORepository.changeUserStatus(UserStatus.DELETED.toString(), id);
		// Change if requires
		userRoleGeographyRepository.changeUserStatus(UserStatus.DELETED.toString(), id);
		userRepository.changeUserStatus(UserStatus.DELETED.toString(), id);
	}

	public void deleteAllUser() {
		userRepository.deleteAll();
	}

	public List<UserCBOsDto> getAllCBOUser() {
		return userRepository.getallUsersDtosUser();
	}

	public Integer markUserActive(Long id) {
		return userRepository.changeUserStatus(UserStatus.ACTIVE.toString(), id);
	}

	public Integer resetUserPassword(Long id, String password) {
		String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		return userRepository.changeUserPassword(passwordHash, id);
	}

	public Integer markUserInActive(Long id) {
		return userRepository.changeUserStatus(UserStatus.INACTIVE.toString(), id);
	}

	public boolean verifyCaptcha(UserAuthDto userAuthDto) throws Exception {
		if (userAuthDto.getCaptchaId() != null) {
			Optional<CaptchaModel> captchaModel = captchaRepository.findById(userAuthDto.getCaptchaId());
			if (captchaModel.isPresent() && captchaModel.get().getAnswer().equals(userAuthDto.getCaptchaCode())) {
				captchaRepository.deleteById(captchaModel.get().getId());
				return true;
			} else {
				return false;
			}
		} else {
			throw new Exception("Captcha details missing");
		}
	}

	public String generateCaptcha() {
		Captcha captcha = CaptchaUtils.createCaptcha(240, 70);
		CaptchaModel captchaModel = new CaptchaModel(captcha.getAnswer());
		captchaRepository.save(captchaModel);
		return "<a href='https://localhost:8080/test'></a><img id='captcha_" + captchaModel.getId()
				+ "' src='data:image/png;base64," + CaptchaUtils.encodeCaptcha(captcha) + "' >";
	}

	public Long getUserIdByLoginId(String loginId) {
		User user = userRepository.getUserByLoginId(loginId);
		return (user != null) ? user.getId() : 0L;
	}

	public Boolean verifyUserWithRoleAndApp(Long userId, String loginId, String applicationRoleName,
			String applicationName) {
		if (!this.getUserIdByLoginId(loginId).equals(userId)) {
			return false;
		}

		List<Long> userApplicationId = userRolesRepository.getRoleIdByUserId(userId);
		List<ApplicationRolesDto> appRoleList = applicationRolesRepository
				.findAllAppRoleNameAndStatusByUserId(userApplicationId);

		for (ApplicationRolesDto appRole : appRoleList) {
			if (appRole.getRoleName().equals(applicationRoleName)) {
				Long appId = appRole.getApplicationId();
				if (appId != null) {
					ApplicationsDto application = applicationRepository.findApplicationNameId(appId);
					if (application.getApplicationCode().equals(applicationName)) {
						return true;
					}
				}

			}
		}

		return false;

	}

	public Boolean verifyUserCBOsWithRoleAndApp(String loginId, String applicationRoleName, String applicationName,
			List<Long> voIds) throws Exception {

		/*
		 * Validate user against loginId from session and userId in request data to
		 * throw exception if not matches
		 *
		 */
		User user = userRepository.getUserByLoginId(loginId);

		List<Long> userApplicationId = userRolesRepository.getRoleIdByUserId(user.getId());

		List<ApplicationRolesDto> appRoleList = applicationRolesRepository
				.findAllAppRoleNameAndStatusByUserId(userApplicationId);

		for (ApplicationRolesDto appRole : appRoleList) {
			if (appRole.getRoleName().equals(applicationRoleName)) {
				Long appId = appRole.getApplicationId();
				if (appId != null) {
					ApplicationsDto application = applicationRepository.findApplicationNameId(appId);
					if (application.getApplicationCode().equals(applicationName)) {
						List<CBOs> userCbos = userCBORepository.getAllCBOsByRole(appRole.getRoleId(), user.getId(),
								"1");
						List<Integer> cboIds = new ArrayList<>();
						userCbos.forEach(cbo -> {
							cboIds.addAll(Arrays.asList(cbo.getCboId()));
						});
						for (Long voId : voIds) {
							if (!cboIds.contains(Integer.parseInt(voId.toString()))) {
								throw new Exception("INVALID VO ACCESS");
							}
						}
						return true;
					}
				}

			}
		}

		return false;

	}

	public Boolean verifyUserGPWithRoleAndApp(String loginId, String applicationRoleName, String applicationName)
			throws Exception {

		/*
		 * Validate user against loginId from session and userId in request data to
		 * throw exception if not matches
		 *
		 */
		User user = userRepository.getUserByLoginId(loginId);

		List<Long> userApplicationId = userRolesRepository.getRoleIdByUserId(user.getId());

		List<ApplicationRolesDto> appRoleList = applicationRolesRepository
				.findAllAppRoleNameAndStatusByUserId(userApplicationId);

		for (ApplicationRolesDto appRole : appRoleList) {
			if (appRole.getRoleName().equals(applicationRoleName)) {
				Long appId = appRole.getApplicationId();
				if (appId != null) {
					ApplicationsDto application = applicationRepository.findApplicationNameId(appId);
					if (application.getApplicationCode().equals(applicationName)) {
						List<Integer[]> gpIds = userRoleGeographyRepository.getAllGpByUser(user.getId(),
								appRole.getRoleId());
						if (gpIds.size() <= 0) {
							throw new Exception("No GPs assigned for this user");
						} else {
							return true;
						}
					}
				}
			}
		}
		return false;

	}

	public List<Integer[]> getAllGPByUserId(Long userId, Long roleId) {
		return userRoleGeographyRepository.getAllGpByUser(userId, roleId);
	}

	public UserSession createUserSession(UserSession userSession) {
		return userSessionRepository.save(userSession);
	}

	public Boolean validateSessionForUser(Long userSession) {
		Optional<UserSession> usrSession = userSessionRepository.findById(userSession);
		if (usrSession.filter(session -> session.getStatus() > 0).isPresent())
			if (!usrSession.get().getExpireTime().before(new Date()))
				return false;
		return true;
	}

	public Boolean validateSessionForUser(String userSession) {
		Optional<UserSession> usrSession = userSessionRepository.findByUserName(userSession);
		if (usrSession.filter(session -> session.getStatus() > 0).isPresent())
			if (!usrSession.get().getExpireTime().before(new Date()))
				return false;
		return true;
	}

	public void logoutUserUser(String token) {
		Long sessionId = jwt.sessionIdByToken(token);
		Date date = new Date();
		userSessionRepository.updateSession(date, sessionId);
	}

	/* PG Application Changes Start */
	public String updateOtp(String otp, Long userId) {
		String passwordHash = Hashing.sha256().hashString(otp, StandardCharsets.UTF_8).toString();
		int Data = userRepository.updateOtp(passwordHash, userId, LocalDateTime.now());
		String Result = "";
		if (Data == 1) {
			Result = "Password Reset Successfully..!";
		}
		return Result;

	}

	public User GetUseremail(String userId) {
		return userRepository.getUserByLoginId(userId);
	}

	public Boolean Checkpassword(String loginId, String password) {
		String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		Optional<UserCBOsDto> existringUser = userRepository.getUserByLogin(loginId, passwordHash);
		if (existringUser == null || existringUser.isEmpty()) {
			return false;
		} else {
			if (existringUser.get().getUserId() > 0) {
				return true;
			} else {
				return false;
			}

		}

	}

	public Boolean Blockedpassword(String password) {
		PG_userpwdblocked blockedPwd = pguserpwdblockedRepository.checkedBlockedpwd(password);
		if (blockedPwd == null) {
			return false;
		} else {
			if (blockedPwd.getId() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public Boolean PwdHistory(Long LoginId, String password) {
		String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		PG_UserPwd PwdHistory = pguserpwdRepository.getUSerPwdHstry(LoginId, passwordHash);
		if (PwdHistory == null) {
			return false;
		} else {
			if (PwdHistory.getId() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public Boolean PwdExpiry(String LoginId, String password) {
		String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		Optional<User> PwdExpiry = userRepository.getUserLogin(LoginId, passwordHash);
		if (PwdExpiry == null || PwdExpiry.isEmpty()) {
			return false;
		} else {
			if (PwdExpiry.get().getId() > 0 && PwdExpiry.get().getPwdexpireflag()== 'Y') {
				long diffInSeconds = java.time.Duration.between(PwdExpiry.get().getPwdvaliduntil(), LocalDateTime.now()).getSeconds();
				if(diffInSeconds > Expiry) {
					return true;
				}else {
					return false;
				}
				
			} else {
				return false;
			}
		}
	}
	
	public Boolean PwdFloating(Long userId) {
		//String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		Optional<User> PwdFloating = userRepository.getUserLogin(userId);
		if (PwdFloating == null || PwdFloating.isEmpty()) {
			return false;
		} else {
			if(PwdFloating.get().getPwdexpireflag() == 'Y') {
				long diffInSeconds = java.time.Duration.between(PwdFloating.get().getPwdvaliduntil(), LocalDateTime.now()).getSeconds();
				if (PwdFloating.get().getId() > 0 && diffInSeconds < Expiry) {
					return true;
					
				} else {
					return false;
				}
			}else {
				return false;
			}
			
		}
	}
	
	public Boolean Checkuser(Long userId) {
		Optional<User> PwdFloating = userRepository.getUserLogin(userId);
		if (PwdFloating == null || PwdFloating.isEmpty()) {
			return true;
		} else {
				if (PwdFloating.get().getId() > 0) {
					return false;
					
				} else {
					return true;
				}			
		}
	}
	
	public Boolean CheckuserbyUserId(String userId) {
		Optional<User> PwdFloating = userRepository.getUserLoginId(userId);
		if (PwdFloating == null || PwdFloating.isEmpty()) {
			return true;
		} else {
				if (PwdFloating.get().getId() > 0) {
					return false;
					
				} else {
					return true;
				}		
		}
	}

	public Integer updateUserPassword(Long id, String password, int pwdslno) {
		String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		int newpwdslno = 0;
		try {
			if (pwdslno == pwdHistory) {
				newpwdslno = 1;
			} else {
				newpwdslno = pwdslno + 1;
			}
			int Isupdated = pguserpwdRepository.updateUserPassword(passwordHash, id, newpwdslno);
			if (Isupdated > 0) {
				return pguserpwdRepository.updateUserPassword(id, pwdslno);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Not able to fetch User Info.!");
		}

		return 0;

	}

	public PG_UserPwd getUSerPwd(Long userId) {
		PG_UserPwd pgUserPwd;
		// String Id = "0";
		// User user = userRepository.getUserByLoginId(Id);
		try {
			pgUserPwd = pguserpwdRepository.getUSerPwd(userId);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Not able to fetch User Info.!");
		}

		return pgUserPwd;

	}

	public PG_UserPwd setUserPwd(PG_UserPwd _pguserpwd) {
		try {
			
			PG_UserPwd _userPwd = null;
			for (int i = 1; i <= pwdHistory;) {
				Date date = new Date();
				Character pwdflag = 'N';
				if (i == pwdHistory) {
					pwdflag = 'Y';
					_userPwd = pguserpwdRepository.save(new PG_UserPwd(_pguserpwd.getUserId(), i,
							_pguserpwd.getPassword(), date, "Admin", pwdflag));
				} else {
					String[] pwd = { "a", "b", "c", "d" };
					String passwordHash = Hashing.sha256().hashString(pwd[i - 1], StandardCharsets.UTF_8).toString();
					_userPwd = pguserpwdRepository
							.save(new PG_UserPwd(_pguserpwd.getUserId(), i, passwordHash, date, "Admin", pwdflag));
				}
				System.out.println(_pguserpwd.toString());
				final Long userPwdId = _userPwd.getId();
				i = i + 1;
			}
			return _userPwd;
		} catch (Exception ex) {
			throw new IllegalArgumentException("Not able to fetch User Info.!");
		}
	}

	public List<PG_clfProfile> getClfdata() {
		try {
			return pgclfRepository.getclfprofile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public List<PG_pgprofile> getPGdata() {
		try {
			return pgRepository.getPgprofile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public List<PG_clfProfile> getAllClfsByGP(Long block, Long appId, Long roleId) {
		List<PG_clfProfile> clfData = new ArrayList<PG_clfProfile>();

		/*
		 * ApplicationRoles appRole =
		 * applicationRoleRepository.findCBOAssignmentRule(appId, roleId); if
		 * (!(appRole.getRoleName().toLowerCase().equals(AppConstant.ROLE_VPRP_VO)) ||
		 * appRole.getCboAssignmentRule() == AppConstant.ALLOW_OVERLAP) { //
		 * CurrentBehaviour
		 * voDataRepository.getAllVosByGp(panchayatIds).forEach(voData::add); } else if
		 * ((appRole.getRoleName().toLowerCase().equals(AppConstant.ROLE_VPRP_VO)) &&
		 * appRole.getCboAssignmentRule() == AppConstant.RESTRICT_OVERLAP) { // OverLap
		 * behavior List<List<Integer>> userCBOIds =
		 * userCBORepository.getAllCBOIdByAppRoleId(roleId, "1"); List<Long>
		 * userCBOLongIds = new ArrayList<Long>(); for (List<Integer> i : userCBOIds) {
		 * for (Integer j : i) { userCBOLongIds.add(j.longValue()); }
		 * 
		 * } voDataRepository.getAllVosByGp(panchayatIds).forEach((voItem) -> { if
		 * (userCBOLongIds.contains(voItem.getFederationId())) {
		 * voItem.setAssigned(true); } else { voItem.setAssigned(false); }
		 * voData.add(voItem); }); }
		 */

		List<List<Integer>> userCBOIds = userCBORepository.getAllCBOIdByAppRoleId(roleId, "2");
		List<Long> userCBOLongIds = new ArrayList<Long>();
		for (List<Integer> i : userCBOIds) {
			for (Integer j : i) {
				userCBOLongIds.add(j.longValue());
			}

		}
		// getAllClfsByGp(block)
		List<PG_clfProfile> objclf = new ArrayList<PG_clfProfile>();
		objclf = pgclfRepository.getAllClfsByGp(block);
		pgclfRepository.getAllClfsByGp(block).forEach((clfItem) -> {
			if (userCBOLongIds.contains(clfItem.getClf_id())) {
				clfItem.setAssigned(true);
			} else {
				clfItem.setAssigned(false);
			}
			clfData.add(clfItem);
		});

		return clfData;

	}

	public List<PG_pgprofile> getAllPgsByGP(Long blockid, Long appId, Long roleId) {
		List<PG_pgprofile> pgData = new ArrayList<PG_pgprofile>();

		List<List<Integer>> userCBOIds = userCBORepository.getAllCBOIdByAppRoleId(roleId, "3");
		List<Long> userCBOLongIds = new ArrayList<Long>();
		for (List<Integer> i : userCBOIds) {
			for (Integer j : i) {
				userCBOLongIds.add(j.longValue());
			}

		}
		// getAllClfsByGp(block)
		pgRepository.getAllPgsByGp(blockid).forEach((pgItem) -> {
			if (userCBOLongIds.contains(pgItem.getPg_id())) {
				pgItem.setAssigned(true);
			} else {
				pgItem.setAssigned(false);
			}
			pgData.add(pgItem);
		});

		return pgData;

	}

	public List<List<Integer>> getCBOs(Long roleId, String Cbolevel) {
		List<List<Integer>> userCBOIds = userCBORepository.getAllCBOIdByAppRoleId(roleId, Cbolevel);
		return userCBOIds;
	}

	public BlockMaster getblock(String blockCode) {
		try {
			BlockMaster blockdata = blockRepository.getStateAndDistrictByBlock(blockCode);
			return blockdata;
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}

	}

	public User getUserInfo(String loginId) {
		try {
			User user = new User();
			user = userRepository.getUserByLoginId(loginId);
			
			/*User userDetails = this.getUserById(user.getId());

			List<UserCBOs> userCBOs = userDetails.getUserCBOs();

			for (UserCBOs uCBOs : userCBOs) {
				ApplicationRolesDto appRoles = applicationRolesRepository
						.getApplicationRolesById(uCBOs.getApplicationRoleId());
				uCBOs.setApplicationRolesDto(appRoles);
			}
			
			return user;*/
			//String passwordHash = Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString();
			Optional<UserCBOsDto> _userCBOsDto = userRepository.getUserByLogin(user.getLoginId(), user.getPassword());
			if (!_userCBOsDto.isPresent()) {
				return null;
			} else {
				User userDetails = this.getUserById(_userCBOsDto.get().getUserId());

				List<UserCBOs> userCBOs = userDetails.getUserCBOs();

				for (UserCBOs uCBOs : userCBOs) {
					ApplicationRolesDto appRoles = applicationRolesRepository
							.getApplicationRolesById(uCBOs.getApplicationRoleId());
					uCBOs.setApplicationRolesDto(appRoles);
				}

				return userDetails;
			}
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}

	}

	public List<PG_clfProfile> getAllClfsByBlockCode(String blockcode) {
		List<UserCBOsDto> objUser = new ArrayList<UserCBOsDto>();
		List<PG_clfProfile> objclf = new ArrayList<PG_clfProfile>();
		List<CBOs> objCbos = new ArrayList<CBOs>();
		objclf = pgclfRepository.getAllClfsByBlockCode(blockcode);
		objCbos = userCBORepository.getAllCBOsforCLF("2");
		objUser = userRepository.getallUsersDtosUsers();
		List<PG_clfProfile> objclfs = new ArrayList<PG_clfProfile>();
		objCbos.forEach((cbosItem) -> {
			PG_clfProfile clfs = new PG_clfProfile();

			userRepository.getallUsersDtosUsers().forEach((userItem) -> {
				Long userId = userItem.getUserId();
				Long cbosUserId = cbosItem.getUserId();
				if (userId.equals(cbosUserId)) {
					pgclfRepository.getAllClfsByBlockCode(blockcode).forEach((clfItem) -> {
						for (int i = 0; i < cbosItem.getCboId().length; i++) {
							Long cboClf_Id = cbosItem.getCboId()[i].longValue();
							Long clfId = clfItem.getClf_id();
							if (cboClf_Id.equals(clfId)) {
								clfs.setClf_id(clfItem.getClf_id());
								clfs.setClfcode(clfItem.getClfcode());
								clfs.setClfname(clfItem.getClfname());
								clfs.setUserid(userItem.getUserId());
								clfs.setUserName(userItem.getUserName());
								clfs.setLoginId(userItem.getLoginid());
								clfs.setBlockcode(blockcode);
								clfs.setUserid(cbosItem.getUserId());
								clfs.setParentcboid(0);
								objclfs.add(clfs);
							}

						}
					});
				}
			});

		});
		
		List<PG_clfProfile> objclfs_ = new ArrayList<PG_clfProfile>();
		for (PG_clfProfile element : objclfs) {
			  
            // If this element is not present in newList
            // then add it
            if (!objclfs_.contains(element)) {
  
            	objclfs_.add(element);
            }
        }
		return objclfs_;

	}

	public Integer setPGDetails(Long pgId,String pgcode,String statecode,String districtcode,String blockcode,String panchayatcode,String villagcode,String pgname,String usercode) {
		
		Integer Result = 0;
		Long cbo = (long) 0;
		LocalDateTime now = LocalDateTime.now();
		try {
			/*BlockMaster blockdata = blockRepository.getStateAndDistrictByBlock(blockcode);
			VillageMaster villagedata = villageRepository.getVillageByvillageCode(villagcode);
			PanchayatMaster panchayatdata = panchayatRepository.getGpByGpCode(panchayatcode);*/
			PG_tpgprofile objPg = tpgRepository.getPgByPGId(pgId);
			if(objPg != null) {
				Result = tpgRepository.updatePgByPGId(pgId, statecode, districtcode, blockcode, panchayatcode, villagcode, pgname, now, usercode, pgcode);
			}else {
				
				PG_tpgprofile _user = tpgRepository.save(new PG_tpgprofile(pgId, statecode, districtcode,
						blockcode, panchayatcode, villagcode, pgname,pgcode,
						now, usercode,cbo,0,cbo, cbo,cbo,cbo,cbo ));
				Result = 1;
			}
		}catch(Exception ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
		return Result;
	}
	
	
	public List<User> getUserAgainstRole(String roleName) {
		//u.id,u.login_id as loginId,u.mob_num_primary as mobNumPrimary,u.user_Status as userStatus,u.email
		String sql = "select u.user_name,u.designation,u.channel,u.id,u.login_id,u.mob_num_primary,u.mob_num_secondary"
				+ ",u.password,u.password_reset_flag,u.user_Status,u.email"
				+ ",ar.application_id,ar.role_name as roleName,ar.role_description as roleDesc,ar.status as roleStatus "
				+ ",b.block_code,b.block_name_en,u.password_expire_flag,u.password_validuntil "
				+ "from nrlm_security.application_roles as ar "
				//+ " inner join nrlm_security.application_roles as ar on a.id = ar.application_id"
				+ "inner join nrlm_security.user_role as ur on ar.id = ur.application_role_id and ur.status = 'ACTIVE'\r\n"
				+ "inner join nrlm_security.users as u on ur.user_id = u.id and u.user_status = 'ACTIVE'\r\n"
				+ "inner join nrlm_security.user_role_geographies as g on u.id = g.user_id and g.status = 'ACTIVE' "
				+ "and ur.application_role_id = g.application_role_id "
				+ "inner join nrlm_master.block_master as b on g.block = b.block_id " 
				//+ "where 1=1 and ar.role_name in ('" + roleName.replace(",", "','") + "')";
				+ "where 1=1 and ar.role_name = '" + roleName.replace('"',' ').trim() +"'";
		Query hQuery = em.createNativeQuery(sql, User.class);
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) hQuery.getResultList();
		// users = userList.stream().filter(distinctByKey(p ->
		// p.getId())).collect(Collectors.toList());
		return userList;
	}
	
	
	public Boolean isExistMob(String Mobno) {
		Integer limit = 1;
		List<User> existringUser = userRepository.getUsersByMobileno(Mobno);
		if (existringUser.isEmpty()) { 
			return false;
		}else {
		
			if (existringUser.get(0).getId() > 0) {
				return true;
			} else {
				return false;
			}

		}
		//return false;
	}
	
	public String pgusercreation(CreateUserPayload user, String BlockCode, String RoleCode, Integer PgId) {
		
		String sql = "select u.user_name,u.designation,u.channel,u.id,u.login_id,u.mob_num_primary,u.mob_num_secondary"
				+ ",u.password,u.password_reset_flag,u.user_Status,u.email"
				+ ",ar.application_id,ar.role_name as roleName,ar.role_description as roleDesc,ar.status as roleStatus "
				+ ",b.block_code,b.block_name_en,u.password_expire_flag,u.password_validuntil "
				+ "from nrlm_security.application_roles as ar "
				//+ " inner join nrlm_security.application_roles as ar on a.id = ar.application_id"
				+ "inner join nrlm_security.user_role as ur on ar.id = ur.application_role_id and ur.status = 'ACTIVE'\r\n"
				+ "inner join nrlm_security.users as u on ur.user_id = u.id and u.user_status = 'ACTIVE'\r\n"
				+ "inner join nrlm_security.user_role_geographies as g on u.id = g.user_id and g.status = 'ACTIVE' "
				+ "and ur.application_role_id = g.application_role_id "
				+ "inner join nrlm_master.block_master as b on g.block = b.block_id " 
				//+ "where 1=1 and ar.role_name in ('" + roleName.replace(",", "','") + "')";
				+ "where 1=1 and b.block_code = '"+ BlockCode.replace('"',' ').trim()
				+"' and u.mob_num_primary = '"+ user.getMobNumPrimary()
				+"' and ar.role_name = '" + RoleCode.replace('"',' ').trim() +"'";
		Query hQuery = em.createNativeQuery(sql, User.class);
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) hQuery.getResultList();
		
		
		if(userList.isEmpty()) 
		{
			return "Mobile Number Exists. User not avilable in this user Block.";
			
		}else {
		for(int i = 0; i < userList.size(); i++) {
			
			String sql1 = "select u.user_name,u.designation,u.channel,u.id,u.login_id,u.mob_num_primary,u.mob_num_secondary"
					+ ",u.password,u.password_reset_flag,u.user_Status,u.email from nrlm_Security.user_cbos as c"
					+ " inner join nrlm_security.users as u on c.user_id = u.id and u.user_status = 'ACTIVE'"
					+ " where 1=1 and u.id = "+userList.get(i).getId();
			Query hQuery1 = em.createNativeQuery(sql1, User.class);
			@SuppressWarnings("unchecked")
			List<User> usersList = (List<User>) hQuery1.getResultList();
			if(usersList.isEmpty()) 
			{
				UserCBOs _user = new UserCBOs();
				_user.setUserId(userList.get(0).getId());
				_user.setCboLevel(user.getRoles().get(0).getUserCBOs().get(0).getCboLevel());
				_user.setCboId(user.getRoles().get(0).getUserCBOs().get(0).getCboIds());
				_user.setFromDate(new Date());
				_user.setToDate(new Date());
				_user.setApplicationRoleId(user.getRoles().get(0).getApplicationRoleId());
				_user.setStatus("ACTIVE");
				UserCBOs _user1 = userCBORepository.save(_user);
				
			}else {
				if(usersList.get(0).getId() > 0) {
					//user.getRoles().get(0).getApplicationRoleId();
					//user.getRoles().get(0).getUserCBOs().get(0).getCboIds();
					//EntityManager em1 = EntityManager.createEntityManager();
				/*	String updateQuery = "update nrlm_Security.user_cbos set cbo_id = array_append(cbo_id, "+PgId+") "
							+ "WHERE user_id = "+userList.get(i).getId();
					/*Query q = em.createNativeQuery(updateQuery);
					q.executeUpdate();
					Query query = em1.createNativeQuery("update nrlm_Security.user_cbos set cbo_id = array_append(cbo_id, "+PgId+") "
							+ "WHERE user_id = "+userList.get(i).getId());
					int rowsUpdated = query.executeUpdate();*/
					Integer rowsUpdated = userCBORepository.updateCbos(userList.get(i).getId(),PgId);
					
				}
			}
		}
		}
		
		return "Success";
		
	}
	
	
	public String getuserMob(Long userId) {
		Optional<User> PwdFloating = userRepository.getUserLogin(userId);
		if (PwdFloating == null || PwdFloating.isEmpty()) {
			return "";
		} else {
				if (PwdFloating.get().getId() > 0) {
					return PwdFloating.get().getMobNumPrimary();
					
				} else {
					return PwdFloating.get().getMobNumPrimary();
				}			
		}
	}

	/* PG Application Changes End */

}
