package com.vprp.user.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.CBOs;
import com.vprp.user.entity.UserCBOs;

public interface UserCBORepository extends JpaRepository<UserCBOs, Long> {	

	@Transactional
	@Modifying
	@Query(value = "insert into user_cbos(cbo_level,user_id,cbo_id,from_date,to_date,application_role_id) values(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
	void saveCboData(String cboLevel, String userId, String cboIds, Date fromDate, Date toDate,
			String applicationRoleId);

	@Transactional
	@Modifying
	@Query("delete from UserCBOs c where  c.userId = ?1")
	void deleteByUserId(Long userId);
	
	@Transactional
	@Modifying
	@Query("delete from UserCBOs c where  c.applicationRoleId = ?1 AND c.userId=?2")
	void deleteByRoleId(Long applicationRoleId, Long userId);

	@Transactional
	@Modifying
	@Query("SELECT new com.vprp.user.dto.CBOs(uc.userId, uc.cboId, uc.cboLevel, uc.fromDate, uc.toDate,  "
			+ "uc.applicationRoleId) FROM UserCBOs uc WHERE uc.applicationRoleId=?1 AND uc.userId=?2 AND uc.cboLevel=?3 AND uc.status='ACTIVE'")
	List<CBOs> getAllCBOsByRole(Long appRoleId, Long userId, String cboLevel);
	
	
	@Transactional
	@Modifying
	@Query("SELECT new com.vprp.user.dto.CBOs(uc.userId, uc.cboId, uc.cboLevel, uc.fromDate, uc.toDate,  "
			+ "uc.applicationRoleId) FROM UserCBOs uc WHERE uc.applicationRoleId=?1 AND uc.userId=?2")
	List<CBOs> getAllCBOsByRole(Long appRoleId, Long userId);
	
	@Transactional
	@Query("SELECT uc.cboId FROM UserCBOs uc WHERE uc.applicationRoleId=?1 AND uc.cboLevel=?2 AND uc.status!='DELETED'")
	List<List<Integer>> getAllCBOIdByAppRoleId(Long appRoleId, String cboLevel);
	@Transactional
	@Query("SELECT uc.cboId FROM UserCBOs uc WHERE uc.userId=?1 AND uc.status='ACTIVE'")
	List<List<Integer>> getAllCBOIdUserId(Long userId);
	
	@Transactional
	@Query("SELECT CASE WHEN count(uc) > 0 THEN true ELSE false END FROM UserCBOs uc WHERE uc.cboId =?1 AND uc.cboLevel=?2 AND uc.applicationRoleId=?3")
	Boolean isAlreadyAssingedCBO(Integer[] cboIds, String cboLevel, Long appRoleId);
	
	@Modifying
	@Transactional
	@Query("update UserCBOs u set u.status = ?1 where u.userId = ?2")
	Integer changeUserStatus(String status, Long userId);
	
	/*PG Application Start*/
	@Transactional
	@Modifying
	@Query("SELECT new com.vprp.user.dto.CBOs(uc.userId, uc.cboId, uc.cboLevel, uc.fromDate, uc.toDate,  "
			+ "uc.applicationRoleId) FROM UserCBOs uc WHERE uc.cboLevel=?1 AND uc.status='ACTIVE'")
	List<CBOs> getAllCBOsforCLF(String cboLevel);
	
	@Modifying
    @Transactional
    @Query("update UserCBOs uc set uc.cboId = array_append(uc.cboId,?2) WHERE uc.userId = ?1")
	Integer updateCbos(Long userId, Integer PgId);
	/*PG Application End*/
}