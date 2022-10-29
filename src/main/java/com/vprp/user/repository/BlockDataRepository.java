package com.vprp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.BlockListDto;
import com.vprp.user.dto.DistrictListDto;
import com.vprp.user.dto.StateListDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.BlockMaster;
import com.vprp.user.entity.StateMaster;
import com.vprp.user.entity.User;

public interface BlockDataRepository extends JpaRepository<BlockMaster, Long> {
	

	@Query("SELECT new com.vprp.user.dto.BlockListDto(dm.blockId, dm.blockNameEn) "
			+ "FROM BlockMaster dm where dm.stateId=?1 AND dm.district_id=?2")
	List<BlockListDto> getAllBlockByStateAndDistrict(Long stateId, Long districtId);
	
	/*PG Application Start*/
	@Query("SELECT new com.vprp.user.entity.BlockMaster(b.blockId, b.blockNameEn, b.blockCode,"
			+ "b.stateId, b.district_id) "
			+ "FROM BlockMaster b where b.blockCode=?1")
	BlockMaster getStateAndDistrictByBlock(String blockCode);
	/*PG Application End*/
}