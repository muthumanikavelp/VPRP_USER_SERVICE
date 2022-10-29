package com.vprp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.StateListDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.StateMaster;
import com.vprp.user.entity.User;
import com.vprp.user.repository.impl.StateDataRepositoryImpl;

public interface StateDataRepository extends JpaRepository<StateMaster, Long>{
	
	@Query("SELECT new com.vprp.user.dto.StateListDto(sm.stateId, sm.stateNameEn) "
			+ "FROM StateMaster sm where sm.stateId=?1")
	List<StateListDto> getAllStateAndDistrict(Long stateId);
	
	
	
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