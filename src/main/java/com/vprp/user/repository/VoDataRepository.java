package com.vprp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.StateListDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.dto.VillageDataListDto;
import com.vprp.user.entity.FederationProfiles;
import com.vprp.user.entity.StateMaster;
import com.vprp.user.entity.User;
import com.vprp.user.entity.VoMaster;
import com.vprp.user.repository.impl.StateDataRepositoryImpl;

public interface VoDataRepository extends JpaRepository<FederationProfiles, Long> {
	
	@Query("SELECT new com.vprp.user.entity.FederationProfiles(fp.federationId, fp.federationCode, fp.federationName, fp.cboType,fp.panchayatId) "
			+ "FROM FederationProfiles fp where fp.panchayatId in (:gpIds) AND EXISTS (SELECT 1 from ShgProfiles sp WHERE sp.parentCBOType=1 AND sp.parentCBOId = fp.federationId)")
	List<FederationProfiles> getAllVosByGp(@Param("gpIds") List<Long> gpIds);

//@Query("SELECT new com.vprp.user.entity.FederationProfiles(fp.federationId, fp.federationCode, fp.federationName, fp.cboType,fp.panchayatId) "
//		+ "FROM FederationProfiles fp where fp.panchayatId in (:gpIds)")
//List<FederationProfiles> getAllVosByGp(@Param("gpIds") List<Long> gpIds);

@Query("SELECT new com.vprp.user.entity.FederationProfiles(fp.federationId, fp.federationCode, fp.federationName, fp.cboType,fp.panchayatId) "
			+ "FROM FederationProfiles fp where fp.panchayatId in (:gpIds) AND fp.federationId NOT IN (:cboIds) AND fp.parentCBOType=1")
	List<FederationProfiles> getAllVosByGp(@Param("gpIds") List<Long> gpIds,  @Param("cboIds") List<Long> cboIds);
}