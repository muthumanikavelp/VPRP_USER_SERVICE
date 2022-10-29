package com.vprp.user.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vprp.user.dto.ApplicationPermissionsDto;
import com.vprp.user.entity.ApplicationPermissions;


public interface ApplicationPermissionRepository extends JpaRepository<ApplicationPermissions, Long> {
	@Query("SELECT new com.vprp.user.dto.ApplicationPermissionsDto(ap.id, ap.permissionName, arp.actionPermitted) "
			+ "From ApplicationPermissions ap LEFT JOIN ApplicationRolePermissions arp "
			+ "ON ap.id=arp.applicationPermissionId WHERE arp.applicationRoleId=?1")
	List<ApplicationPermissionsDto> getPermissionAndActions(Long applicationRoleId);
	
}
