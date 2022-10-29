package com.vprp.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.vprp.user.dto.ApplicationRolesDto;
import com.vprp.user.entity.ApplicationRoles;

public interface ApplicationRolesRepository extends JpaRepository<ApplicationRoles, Long> {

	@Query("SELECT new com.vprp.user.dto.ApplicationRolesDto(ar.applicationId, ar.roleName, ar.roleDescription, ar.geographyLevelsPermitted, ar.mapToGeographics) "
			+ "FROM ApplicationRoles ar WHERE ar.id = ?1")
	ApplicationRolesDto getApplicationRolesById(Long id);

	@Query("SELECT new com.vprp.user.dto.ApplicationRolesDto(ar.applicationId, ar.id) "
			+ "FROM ApplicationRoles ar WHERE ar.applicationId = ?1 AND ar.roleName = ?2")
	ApplicationRolesDto findApplicationByIdName(Long appId, String roleCode);

	@Query("SELECT new com.vprp.user.dto.ApplicationRolesDto(ar.applicationId, ar.id, ar.roleName, ar.status) "
			+ "FROM ApplicationRoles ar WHERE ar.id In (:roleIds)")
	List<ApplicationRolesDto> findAllAppRoleNameAndStatusByUserId(List<Long> roleIds);

	Optional<ApplicationRoles> findById(Long id);

	@Query("SELECT ar FROM ApplicationRoles ar WHERE ar.applicationId = ?1 AND ar.id= ?2")
	ApplicationRoles findCBOAssignmentRule(Long appId, Long appRoleId);
}
