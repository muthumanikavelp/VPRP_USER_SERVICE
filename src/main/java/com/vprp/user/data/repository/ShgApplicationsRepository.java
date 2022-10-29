package com.vprp.user.data.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.data.dto.ShgApplicationsResponse;
import com.vprp.user.entity.Applications;
import com.vprp.user.entity.ShgApplications;

public interface ShgApplicationsRepository extends JpaRepository<ShgApplications, Long> {

	@Query("SELECT new com.vprp.user.data.dto.ShgApplicationsResponse(sh.shgId, sh.parentCBOId, sh.parentCBOType, sh.stateId, sh.districtId, "
			+ "sh.blockId, sh.panchayatId, sh.villageId, sh.surveyYear, sh.applicationDataJson, "
			+ "sh.shgSurveyStatus, sh.createdBy, sh.updatedBy, sh.createdDate, sh.updateDate, "
			+ "sh.revisionNumber) FROM ShgApplications sh WHERE sh.shgId=?1")
	ShgApplicationsResponse findShgByShgId(Long shgId);

	@Transactional
	@Modifying
	@Query("DELETE FROM ShgApplications sh WHERE sh.shgId=?1")
	void deleteByShgId(Long id);

	@Transactional	
	@Query("SELECT sh FROM ShgApplications sh WHERE sh.surveyYear=:surveyYear AND sh.shgId IN (:shgIds) ")
	List<ShgApplications> getAppByParentId(Integer surveyYear, Set<Long> shgIds);
	
	@Transactional	
	@Query("SELECT sh FROM ShgApplications sh WHERE sh.surveyYear=:surveyYear AND sh.shgId IN (:shgIds) ")
	List<ShgApplications> getAppByParentId(Integer surveyYear, Long shgIds);


	@Transactional
	@Query("SELECT sh FROM ShgApplications sh WHERE sh.shgId IN (:test) AND sh.surveyYear=(:surveyYear)")
	List<ShgApplications> getShgAppByIds(Integer surveyYear, List<Long> test);
}
