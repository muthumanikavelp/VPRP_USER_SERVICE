package com.vprp.user.data.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.vprp.user.data.dto.ShgProfilesResponse;
import com.vprp.user.entity.ShgProfiles;

public interface ShgProfilesRepository extends JpaRepository<ShgProfiles, Long> {

	@Modifying
	@Transactional
	@Query("SELECT new com.vprp.user.data.dto.ShgProfilesResponse(sh.shgId, sh.stateId, sh.districtId, sh.blockId, sh.panchayatId, sh.villageId, "
			+ "sh.geoEntityCode, sh.shgCode, sh.shgName, sh.parentCBOId, sh.parentCBOType, "
			+ "sh.dataSource, sh.createdBy, sh.updatedBy, sh.createdDate, sh.updateDate) FROM ShgProfiles sh WHERE sh.shgId IN (:cboIds)")
	List<ShgProfilesResponse> getAllShgByCbos(List<Long> cboIds);

	@Transactional
	@Query("SELECT new com.vprp.user.data.dto.ShgProfilesResponse(sh.shgId, sh.shgId, sh.stateId, sh.districtId, sh.blockId, sh.panchayatId, sh.villageId, "
			+ "sh.geoEntityCode, sh.shgCode, sh.shgName, sh.parentCBOId, sh.parentCBOType, "
			+ "sh.dataSource, sh.createdBy, sh.updatedBy, sh.createdDate, sh.updateDate) FROM ShgProfiles sh WHERE sh.shgId =?1")
	ShgProfilesResponse findShgByShgId(Long shgId);

	@Transactional
	@Query("SELECT sh FROM ShgProfiles sh WHERE sh.shgId IN (:shgIds)")
	List<ShgProfiles> findShgByShgIds(List<Long> shgIds);

	@Transactional
	@Query("SELECT sh FROM ShgProfiles sh WHERE sh.shgId IN (:shgIds)")
	List<ShgProfiles> findShgByShgIds(Long shgIds);

	@Transactional
	@Query("SELECT new com.vprp.user.data.dto.ShgProfilesResponse(sh.shgId, sh.stateId, sh.districtId, sh.blockId, sh.panchayatId, sh.villageId, "
			+ "sh.geoEntityCode, sh.shgCode, sh.shgName, sh.parentCBOId, sh.parentCBOType, "
			+ "sh.dataSource, sh.createdBy, sh.updatedBy, sh.createdDate, sh.updateDate) FROM ShgProfiles sh WHERE sh.panchayatId IN (:panchayatIds)")
	List<ShgProfilesResponse> findShgByGP(Set<Long> panchayatIds);
	
	
	@Transactional
	@Query("SELECT sh FROM ShgProfiles sh WHERE sh.parentCBOId IN (:cboId) AND sh.parentCBOType=1")
	List<ShgProfiles> findShgByParentCBOs(Long cboId);

}
