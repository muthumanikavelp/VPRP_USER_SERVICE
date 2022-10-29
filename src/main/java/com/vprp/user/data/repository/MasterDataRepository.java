package com.vprp.user.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.data.dto.MasterDataResponse;
import com.vprp.user.data.dto.SchemeData;
import com.vprp.user.dto.ApplicationRolePermissionDto;
import com.vprp.user.dto.ApplicationRolesDto;
import com.vprp.user.dto.ApplicationsDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.ApplicationRolePermissions;
import com.vprp.user.entity.ApplicationRoles;
import com.vprp.user.entity.Applications;
import com.vprp.user.entity.MasterData;
import com.vprp.user.entity.User;
import com.vprp.user.entity.UserRoles;

public interface MasterDataRepository extends JpaRepository<MasterData, Long> {
	
	@Modifying
	@Transactional
	@Query("SELECT m FROM MasterData m WHERE m.stateId = :stateId")
	List<MasterData> getMasterData(Integer stateId);
		
}
