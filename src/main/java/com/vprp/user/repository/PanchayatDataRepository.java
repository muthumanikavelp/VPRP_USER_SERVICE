package com.vprp.user.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.vprp.user.dto.PanchayatListDto;
import com.vprp.user.dto.PanchayatMasterResponse;
import com.vprp.user.entity.PanchayatMaster;

public interface PanchayatDataRepository extends JpaRepository<PanchayatMaster, Long> {

	@Query("SELECT new com.vprp.user.dto.PanchayatListDto(dm.panchayatId, dm.panchayatNameEn) "
			+ "FROM PanchayatMaster dm where dm.stateId=?1 AND dm.districtId=?2 AND dm.blockId=?3")
	List<PanchayatListDto> getAllPanchayatByThreeInput(Long stateId, Long districtId, Long blockId);

	@Query("SELECT new com.vprp.user.dto.PanchayatListDto(dm.panchayatId, dm.panchayatNameEn) "
			+ "FROM PanchayatMaster dm where dm.stateId=?1 AND dm.districtId=?2 AND dm.blockId=?3 AND dm.panchayatId=?4")
	List<PanchayatListDto> getAllPanchayatByFourInput(Long stateId, Long districtId, Long blockId, Long panchaytId);

	@Query("SELECT new com.vprp.user.dto.PanchayatMasterResponse(p.panchayatId, p.panchayatCode, p.panchayatNameEn, p.stateId, "
			+ "p.districtId, p.blockId) FROM PanchayatMaster p WHERE p.panchayatId IN(:panchayatIds)")
	List<PanchayatMasterResponse> getPanchayatByPanchayatId(Set<Long> panchayatIds);

	@Query("SELECT p.stateId FROM PanchayatMaster p WHERE p.panchayatId IN(:panchayatIds)")
	List<Long> getStateIdsByPanchayat(List<Long> panchayatIds);

	@Query("SELECT new com.vprp.user.dto.PanchayatListDto(dm.panchayatId, dm.panchayatNameEn) "
			+ "FROM PanchayatMaster dm where dm.panchayatId IN (:panchayatIds)")
	List<PanchayatListDto> getPanchayatByIds(List<Long> panchayatIds);

	@Query("SELECT dm FROM PanchayatMaster dm where dm.panchayatId = ?1")
	PanchayatMaster getGpByGpId(Long panchayatId);

	
//	@Query("SELECT dm FROM PanchayatMaster dm where dm.panchayatCode = ?1")
//	PanchayatMaster getGpByGpCode(String panchayatCode);



}