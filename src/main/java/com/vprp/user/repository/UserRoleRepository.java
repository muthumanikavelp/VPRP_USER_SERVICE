package com.vprp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.ApplicationRolesDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.ApplicationRoles;
import com.vprp.user.entity.User;
import com.vprp.user.entity.UserRoles;

public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
	
	
	@Query("SELECT us.applicationRoleId FROM UserRoles us WHERE us.userId=:userId")
	List<Long> getRoleIdByUserId(Long userId);
	
	@Query("SELECT us FROM UserRoles us WHERE us.userId=:userId AND us.applicationRoleId=:applicationRoleId")
	List<UserRoles> getAllRoleByUserAppId(Long userId, Long applicationRoleId);
	
	@Query("SELECT us FROM UserRoles us WHERE us.userId=:userId AND us.userStatus!='DELETED'")
	List<UserRoles> getAllRoleByUserAppId(Long userId);
}
