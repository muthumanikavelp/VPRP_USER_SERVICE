package com.vprp.user.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.data.dto.EntitlementDataResponse;
import com.vprp.user.data.dto.MasterDataResponse;
import com.vprp.user.data.dto.SchemeData;
import com.vprp.user.dto.ApplicationRolePermissionDto;
import com.vprp.user.dto.ApplicationRolesDto;
import com.vprp.user.dto.ApplicationsDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.ApplicationRolePermissions;
import com.vprp.user.entity.ApplicationRoles;
import com.vprp.user.entity.Applications;
import com.vprp.user.entity.Entitlement;
import com.vprp.user.entity.MasterData;
import com.vprp.user.entity.User;
import com.vprp.user.entity.UserRoles;

public interface EntilementsRepository extends JpaRepository<Entitlement, Long> {
	
	@Modifying
	@Transactional
	@Query("SELECT new com.vprp.user.data.dto.EntitlementDataResponse(em.id, em.entName, em.isGroup, em.planType, em.status, em.createdBy, "
			+ "em.updatedBy, em.createdDate, em.updateDate, em.revisionNumber, em.stateId, "
			+ "em.isRankingEnabled, em.order, em.rankingType, em.sno, em.applCount, em.rankCount, "
			+ "em.voCount, em.gpCount) FROM Entitlement em WHERE em.stateId = ?1")
	List<EntitlementDataResponse> getEntitlementsData(Long stateId);
		
}
