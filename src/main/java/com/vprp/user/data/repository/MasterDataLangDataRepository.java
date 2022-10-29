package com.vprp.user.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.data.dto.MasterDataLangDataResponse;
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
import com.vprp.user.entity.MasterDataLang;
import com.vprp.user.entity.User;
import com.vprp.user.entity.UserRoles;

public interface MasterDataLangDataRepository extends JpaRepository<MasterDataLang, Long> {
	
	@Modifying
	@Transactional
	@Query("SELECT new com.vprp.user.data.dto.MasterDataLangDataResponse(ms.id, ms.mstId, ms.mstLangTextName, ms.mstLangCode, ms.createdBy, "
			+ "ms.updatedBy, ms.createdDate, ms.updateDate, ms.revisionNumber, ms.stateId) FROM MasterDataLang ms WHERE ms.mstId IN (:mstIds) ")
	List<MasterDataLangDataResponse> getMasterDataLang(@Param("mstIds") List<Long> mstIds);

	@Modifying
	@Transactional
	@Query("SELECT new com.vprp.user.data.dto.MasterDataLangDataResponse(ms.id, ms.mstId, ms.mstLangTextName, ms.mstLangCode, ms.createdBy, "
			+ "ms.updatedBy, ms.createdDate, ms.updateDate, ms.revisionNumber, ms.stateId) FROM MasterDataLang ms WHERE ms.mstId IN (:mstIds) AND ms.mstLangCode IN(:langCodes)")
	List<MasterDataLangDataResponse> getMasterDataLang(@Param("mstIds") List<Long> mstIds,@Param("langCodes") List<String> langCodes);
		
}
