package com.vprp.user.repository;

import java.util.List;

import com.vprp.user.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.StateListDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.repository.impl.StateDataRepositoryImpl;

public interface ShgDataRepository extends JpaRepository<ShgMaster, String> {
	
	public static interface Shg {

		String getShgCode();

		String getShgName();

		String getShgType();

	}

	@Query("SELECT new com.vprp.user.entity.ShgProfiles(shg.shgId,shg.shgCode,shg.shgName,shg.parentCBOId) FROM ShgProfiles shg"
			+" WHERE shg.parentCBOId IN (:voIds)")
	List<ShgProfiles> getAllShgsByVO(@Param("voIds") List<Long> voIds);
	
	@Query("SELECT new com.vprp.user.entity.ShgProfiles(shg.shgId,shg.shgCode,shg.shgName,shg.parentCBOId) FROM ShgProfiles shg"
			+" WHERE shg.parentCBOId IN (:voIds) AND shg.shgId NOT IN(:cboIds) AND shg.parentCBOType=1")
	List<ShgProfiles> getAllShgsByVO(@Param("voIds") List<Long> voIds, @Param("cboIds") List<Long> cboIds);


	@Query("SELECT new com.vprp.user.entity.ShgProfiles(shg.shgId,shg.shgCode,shg.shgName,shg.parentCBOId) FROM ShgProfiles shg"
			+" WHERE shg.panchayatId IN (:gpIds)")
	List<ShgProfiles> getAllShgsByGp(@Param("gpIds") List<Long> gpIds);
//	@Query(value = "SELECT shg.shg_code AS shgCode,shg.group_name AS shgName,shg.shg_type shgType FROM hr_shg_detail shg"
//			+ " INNER JOIN vo_shg_mapping vm ON vm.shg_code = shg.shg_code WHERE vm.vo_code in (:pachayatIds)", nativeQuery = true)
//	List<Shg> getAllShgsByGP(@Param("pachayatIds") List<Long> pachayatIds);
//	
//	
	
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