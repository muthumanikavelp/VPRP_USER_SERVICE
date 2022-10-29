package com.vprp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.User;
import com.vprp.user.entity.UserCBOs;
import com.vprp.user.entity.UserRoleGeography;

public interface UserRoleGeographyRepository extends JpaRepository<UserRoleGeography, Long> {

//	@Modifying
//	@Transactional
//	@Query("update User u set u.status = ?1 where u.id = ?2")
//	Integer changeUserStatus(String status, Long userId);

	@Transactional
	@Modifying
	@Query("delete from UserRoleGeography g where g.userId = ?1")
	void deleteByUserId(Long userId);

	@Transactional
	@Modifying
	@Query("delete from UserRoleGeography g where g.applicationRoleId = ?1 AND g.userId=?2")
	void deleteByRoleId(Long applicationRoleId, Long userId);

	@Modifying
	@Query("Select g.state FROM UserRoleGeography g where g.userId = ?1")
	List<Long> getAllStateIds(Long userId);

	@Query("Select g FROM UserRoleGeography g where g.userId = ?1 AND g.applicationRoleId=?2 and g.status = 'ACTIVE'")
	UserRoleGeography getAllGeos(Long userId, Long applicationRoleId);


	@Modifying
	@Transactional
	@Query("update UserRoleGeography u set u.status = ?1 where u.userId = ?2")
	Integer changeUserStatus(String status, Long userId);

	//Getting all GP by using user_id
	@Query("Select g.grampPanchayat FROM UserRoleGeography g where g.userId = ?1 AND g.applicationRoleId=?2")
	List<Integer[]> getAllGpByUser(Long userId, Long applicationRoleId);
}