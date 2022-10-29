package com.vprp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.DistrictListDto;
import com.vprp.user.dto.StateListDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.DistrictMaster;
import com.vprp.user.entity.StateMaster;
import com.vprp.user.entity.User;

public interface DistrictDataRepository extends JpaRepository<DistrictMaster, Long> {
	
	@Query("SELECT new com.vprp.user.dto.DistrictListDto(dm.districtId, dm.districtNameEn, dm.stateId) "
			+ "FROM DistrictMaster dm LEFT JOIN dm.stateMaster sm where dm.districtId=?1")
	List<DistrictListDto> getAllStateAndDistrict(Long stateId);
	
	@Query("SELECT new com.vprp.user.dto.DistrictListDto(dm.districtId, dm.districtNameEn, dm.stateId) "
			+ "FROM DistrictMaster dm WHERE dm.stateId=?1")
	List<DistrictListDto> getAllDistrictByStateId(Long stateId);
	
//	@Query("SELECT new com.vprp.user.dto.UserCBOsDto(d.id, d.userName, d.mobNumPrimary, d.mobNumSecondary, d.state, d.district"
//			+ ",d.block, d.grampPanchayat, d.village, e.cboLevel, e.cboLevel, e.cboLevel, e.cboLevel, e.cboId, 1) "
//			+ "FROM User d LEFT JOIN d.userCBOs e where d.loginId=?1 and d.password=?2")
//	UserCBOsDto getUserByLogin(String loginId, String password);
//	
//	@Modifyins
//	@Transactional
//	@Query("update User u set u.status = ?1 where u.id = ?2")
//	Integer changeUserStatus(String status, Long userId);
	
}

//UserCBOsDto(Long userId, String userName, String mobileNumPrimay, String mobileNumSecondary, Long state,
//		Long district, Long block, Long gpInfo, Long village, String cbo4, String cbo3, String cbo2,
//		String cbo1, Long cboId, Integer page)