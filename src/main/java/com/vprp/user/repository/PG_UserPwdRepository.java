package com.vprp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.entity.PG_UserPwd;

public interface PG_UserPwdRepository extends JpaRepository<PG_UserPwd, Long>{
	
	@Modifying
    @Transactional
    @Query("update PG_UserPwd u set u.password = ?1, u.currentpwdflag = 'Y'"
    		+ " where u.userId = ?2 and u.passwordSlno = ?3")
    Integer updateUserPassword(String passwordHash, Long id, int pwdslno);
	
	@Modifying
    @Transactional
	@Query("update PG_UserPwd u set u.currentpwdflag = 'N'"
    		+ " where u.userId = ?1 and u.passwordSlno = ?2")
    Integer updateUserPassword(Long id, int pwdslno);
	
	@Query("select new com.vprp.user.entity.PG_UserPwd(u.id,u.userId,u.passwordSlno"
			+ ",u.password,u.createdDate,u.createdBy,u.currentpwdflag)"
			+ "from PG_UserPwd u where u.userId = ?1 and u.currentpwdflag = 'Y'")
	PG_UserPwd getUSerPwd(Long userId);
	
	@Query("select new com.vprp.user.entity.PG_UserPwd(u.id)"
			+ "from PG_UserPwd u where u.userId = ?1 and u.password = ?2")
	PG_UserPwd getUSerPwdHstry(Long userId, String Pwd);
	
	
}
