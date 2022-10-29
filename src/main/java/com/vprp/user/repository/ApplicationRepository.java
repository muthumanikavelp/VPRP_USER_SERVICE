package com.vprp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.ApplicationRolePermissionDto;
import com.vprp.user.dto.ApplicationRolesDto;
import com.vprp.user.dto.ApplicationsDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.ApplicationRolePermissions;
import com.vprp.user.entity.ApplicationRoles;
import com.vprp.user.entity.Applications;
import com.vprp.user.entity.User;
import com.vprp.user.entity.UserRoles;

public interface ApplicationRepository extends JpaRepository<Applications, Long> {
	
//	@Modifying
//	@Transactional
//	@Query("SELECT new com.vprp.user.dto.ApplicationRolePermissionDto(d.actionPermitted) "
//			+ "FROM ApplicationRoles d WHERE d.applicationId=?1")
//	List<ApplicationRolePermissionDto> getApplictionRolePermissionById(Long applicationRoleId);

	@Modifying
	@Transactional
	@Query("SELECT new com.vprp.user.dto.ApplicationRolesDto(ar.applicationId, ar.id, ar.roleName,ar.roleDescription, ar.geographyLevelsPermitted,ar.cbosLevelsPermitted,ar.mapToCbos,ar.mapToGeographics, ar.status)"
			+ "FROM ApplicationRoles ar WHERE ar.applicationId=?1")
	List<ApplicationRolesDto> applicationRoleByApplication(Long applicationId);
	
	
	@Transactional
	@Query("SELECT new com.vprp.user.dto.ApplicationsDto(ap.id, ap.applicationCode, ap.applicationName, ap.applicationDescription, ap.status)"
			+ "FROM Applications ap WHERE ap.id=?1")
	ApplicationsDto getApplications(Long applicationId);

	@Transactional
	@Query("SELECT new com.vprp.user.dto.ApplicationsDto(ap.id, ap.applicationCode, ap.applicationName, ap.applicationDescription, ap.status)"
			+ "FROM Applications ap WHERE ap.applicationCode=?1")
	ApplicationsDto findApplicationByName(String appName);
	
	
	@Transactional
	@Query("SELECT new com.vprp.user.dto.ApplicationsDto(ap.id, ap.applicationCode, ap.applicationName, ap.applicationDescription, ap.status)"
			+ "FROM Applications ap WHERE ap.id=?1")
	ApplicationsDto findApplicationNameId(Long appId);
	
	
	
	
}
