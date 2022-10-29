package com.vprp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.ApplicationRolePermissionDto;
import com.vprp.user.dto.ApplicationRolesDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.ApplicationRolePermissions;
import com.vprp.user.entity.ApplicationRoles;
import com.vprp.user.entity.User;
import com.vprp.user.entity.UserRoles;

public interface ApplicationRolePermissionRepository extends JpaRepository<ApplicationRoles, Long> {
	
	@Modifying
	@Transactional
	@Query("SELECT new com.vprp.user.dto.ApplicationRolePermissionDto(d.actionPermitted) "
			+ "FROM ApplicationRolePermissions d WHERE d.applicationRoleId=?1")
	List<ApplicationRolePermissionDto> getApplictionRolePermissionById(Long applicationRoleId);
	
}
