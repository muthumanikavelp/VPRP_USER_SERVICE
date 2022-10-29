package com.vprp.user.services;

import java.util.*;

import com.vprp.user.dto.ApplicationRolesDto;
import com.vprp.user.dto.CBOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vprp.user.entity.User;
import com.vprp.user.entity.UserCBOs;
import com.vprp.user.entity.UserRoleGeography;
import com.vprp.user.entity.UserRoles;
import com.vprp.user.enumurator.UserStatus;
import com.vprp.user.repository.ApplicationRolesRepository;
import com.vprp.user.repository.UserCBORepository;
import com.vprp.user.repository.UserRepository;
import com.vprp.user.repository.UserRoleGeographyRepository;
import com.vprp.user.repository.UserRoleRepository;

@Service
public class UserCBOService {

	@Autowired
	UserCBORepository userCBORepository;

	@Autowired
	UserRoleGeographyRepository userRoleGeographyRepository;

	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	/*PG Application Changes Start*/
	@Autowired
    @Value("${pg.application.Id}")
    private Long applicationId;
	
	@Autowired
    ApplicationRolesRepository applicationRolesRepository;
	/*PG Application Changes End*/
	
	public Optional<UserCBOs> getUserCBOById(long cboId) {
		return userCBORepository.findById(cboId);
	}

	public List<UserCBOs> getAllUsers(String userName) {
		List<UserCBOs> usersCBOs = new ArrayList<UserCBOs>();
		userCBORepository.findAll().forEach(usersCBOs::add);
		return usersCBOs;
	}

	public void deleteUser(long id) {
		userCBORepository.deleteById(id);
	}

	public List<UserCBOs> addCboToUser(List<UserCBOs> _userCbo, Long userId) {

		return userCBORepository.saveAll(_userCbo);
	}

	public void removeCboFromUser(Long id) {
		userCBORepository.deleteById(id);
	}

	public List<UserRoleGeography> addGeographyToUser(List<UserRoleGeography> _userRoleGeography, Long userId) {

		return userRoleGeographyRepository.saveAll(_userRoleGeography);

	}

	public UserRoles addRoleToUser(UserRoles _userRole) {
		_userRole.setUserStatus(UserStatus.ACTIVE.toString());
		UserRoles userRole = userRoleRepository.save(_userRole);
		return userRole;
	}

	public void removeGeographyFromUser(Long id) {
		userRoleGeographyRepository.deleteById(id);
	}

	public void removeRoleFromUser(Long id, Long userId) {

		List<UserRoles> roles = userRoleRepository.getAllRoleByUserAppId(userId, id);
		for (UserRoles us : roles) {
			us.setUserStatus(UserStatus.DELETED.toString());
		}

		userRoleRepository.saveAll(roles);
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			// deleting user CBO
			userCBORepository.deleteByRoleId(id, userId);
			// deleting user Geographic
			userRoleGeographyRepository.deleteByRoleId(id, userId);
		}
	}
	public List<CBOs> getAllCBOsByRole(Long appRoleId, Long userId, String cboLevel){
		return userCBORepository.getAllCBOsByRole(appRoleId, userId, cboLevel);
	}

	public List<CBOs> getAllCBOsByRole(Long appRoleId, Long userId){
		return userCBORepository.getAllCBOsByRole(appRoleId, userId);
	}

	public Set<Long> getAllCBOIdUserId(Long userId){
		Set<Long> userCBOs = new HashSet<>();
		List<List<Integer>> userCboIds = userCBORepository.getAllCBOIdUserId(userId);
		if(userCboIds.isEmpty()){
			return Collections.emptySet();
		}
		for(List<Integer> userCboId : userCboIds){
			for(Integer id : userCboId){
				userCBOs.add(Long.valueOf(id));
			}
		}
		return userCBOs;
	}
	
	/*PG Application Changes Start*/
	public String pgDeleteRolebyLoginId(Long UserId, String RoleName) {
		String Result = "";
		try {
		ApplicationRolesDto appRolesDto = applicationRolesRepository.findApplicationByIdName(applicationId,RoleName);
		removeRoleFromUser(appRolesDto.getRoleId(), UserId);
		Result = "SUCCESS";
		return Result;
		}catch(Exception ex){
			Result = ex.getMessage();
			return Result;
		}
		
	}
	/*PG Application Changes End*/
}
