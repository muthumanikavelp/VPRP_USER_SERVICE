package com.vprp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.PanchayatListDto;
import com.vprp.user.dto.StateListDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.dto.VillageDataListDto;
import com.vprp.user.entity.BlockMaster;
import com.vprp.user.entity.PanchayatMaster;
import com.vprp.user.entity.StateMaster;
import com.vprp.user.entity.User;
import com.vprp.user.entity.VillageMaster;

public interface VillageDataRepository extends JpaRepository<VillageMaster, Long> {
	
	@Query("SELECT new com.vprp.user.dto.VillageDataListDto(dm.villageId, dm.villageNameEn) "
			+ "FROM VillageMaster dm where dm.stateId=:stateId AND dm.districtId=:districtId AND dm.blockId=:blockId AND dm.panchayatId IN (:panchayatIds)")
	List<VillageDataListDto> getAllVillageDataByFourInput(@Param("stateId") Long stateId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("panchayatIds") List<Long> panchaytIds);
	
	
	@Query("SELECT new com.vprp.user.dto.VillageDataListDto(dm.villageId, dm.villageNameEn) "
			+ "FROM VillageMaster dm where dm.stateId=?1 AND dm.districtId=?2 AND dm.blockId=?3 AND dm.panchayatId=?4 AND dm.villageId=?5")
	List<VillageDataListDto> getAllVillageDataByFiveInput(Long stateId, Long districtId, Long blockId, Long panchayatId, Long villageId);
	
	/*PG Application Changes Start*/
//	@Query("SELECT v FROM VillageMaster v where v.villageCode = ?1")
//	VillageMaster getVillageByvillageCode(String villageCode);
	/*PG Application Changes End*/
}