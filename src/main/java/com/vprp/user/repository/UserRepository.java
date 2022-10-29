package com.vprp.user.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.AppLoginDto;
import com.vprp.user.dto.AppRoleLoginDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.dto.UserDto;
import com.vprp.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT new com.vprp.user.dto.UserCBOsDto(d.id, d.userName, d.mobNumPrimary, d.mobNumSecondary) "
            + "FROM User d")
    List<UserCBOsDto> getallUsersDtosUser();

    @Query("SELECT new com.vprp.user.dto.UserCBOsDto(d.id, d.userName, d.mobNumPrimary, d.mobNumSecondary) "
            + "FROM User d where d.loginId=?1 and d.password=?2")
    Optional<UserCBOsDto> getUserByLogin(String loginId, String password);

    @Modifying
    @Transactional
    @Query("update User u set u.status = ?1 where u.id = ?2")
    Integer changeUserStatus(String status, Long userId);

    @Modifying
    @Transactional
    @Query("update User u set u.password = ?1, u.pwdresetflag = 'N', u.pwdexpireflag='N'  where u.id = ?2")
    Integer changeUserPassword(String password, Long userId);

    @Modifying
    @Transactional
    @Query("SELECT applicationRoleId FROM UserRoles WHERE userId=?1 AND userStatus='Active'")
    List<Long> getUserRoleIdByUserId(Long userId);

    @Query("SELECT new com.vprp.user.dto.AppRoleLoginDto(ar.applicationId, ar.id, ar.roleName, ap.actionPermitted) "
            + "FROM ApplicationRoles ar LEFT JOIN ApplicationRolePermissions ap ON ap.applicationRoleId= ar.id  "
            + "WHERE ar.id IN (?1)")
    List<AppRoleLoginDto> getApplicationRolePermissionLogin(List<Long> roleIds);

    @Query("SELECT new com.vprp.user.dto.AppLoginDto(id, applicationCode, applicationName, applicationDescription, status) "
            + "FROM Applications WHERE id IN (SELECT DISTINCT applicationId FROM ApplicationRoles WHERE id IN (?1))")
    List<AppLoginDto> getApplicationDetailsLogin(List<Long> roleIds);

    @Query("SELECT new com.vprp.user.entity.User(u.id,u.loginId, u.userName,u.password,u.email,u.pwdresetflag) "
            + "FROM User u where u.loginId=?1")
    User getUserByLoginId(String loginId);
    
    
    /*PG Application Changes Start*/
    @Modifying
    @Transactional
    @Query("update User u set u.password = ?1, u.pwdresetflag = 'Y', u.pwdexpireflag = 'Y', u.pwdvaliduntil = ?3 where u.id = ?2")
    Integer updateOtp(String otp, Long userId, LocalDateTime _datetime);
    
    @Query("SELECT new com.vprp.user.dto.UserCBOsDto(d.id, d.userName, d.mobNumPrimary, d.mobNumSecondary, d.loginId) "
            + "FROM User d where d.status = 'ACTIVE' ")
    List<UserCBOsDto> getallUsersDtosUsers();
    
    @Modifying
    @Transactional
    @Query("update User u set u.channel = '1001' where u.id = ?1")
    Integer updateChannel(Long userId);
    
    @Query("SELECT new com.vprp.user.entity.User(u.id,u.loginId, u.userName,u.password,u.email,u.pwdresetflag) "
            + "FROM User u where u.mobNumPrimary=?1")
    User getUserByMobileno(String Mobno, Integer limit);
    
    @Query("SELECT new com.vprp.user.entity.User(u.id,u.loginId, u.userName,u.password,u.email,u.pwdresetflag) "
            + "FROM User u where u.mobNumPrimary=?1")
    List<User> getUsersByMobileno(String Mobno);
    
    @Query("SELECT new com.vprp.user.entity.User(d.id, d.userName, d.mobNumPrimary, d.mobNumSecondary, d.pwdexpireflag, d.pwdvaliduntil) "
            + "FROM User d where d.loginId=?1 and d.password=?2")
    Optional<User> getUserLogin(String loginId, String password);
    
    @Query("SELECT new com.vprp.user.entity.User(d.id, d.userName, d.mobNumPrimary, d.mobNumSecondary, d.pwdexpireflag, d.pwdvaliduntil) "
            + "FROM User d where d.id=?1")
    Optional<User> getUserLogin(Long userId);
    
    @Query("SELECT new com.vprp.user.entity.User(d.id, d.userName, d.mobNumPrimary, d.mobNumSecondary, d.pwdexpireflag, d.pwdvaliduntil) "
            + "FROM User d where d.loginId=?1")
    Optional<User> getUserLoginId(String userId);
    
    
    /*PG Application Changes End*/
}

//UserCBOsDto(Long userId, String userName, String mobileNumPrimay, String mobileNumSecondary, Long state,
//		Long district, Long block, Long gpInfo, Long village, String cbo4, String cbo3, String cbo2,
//		String cbo1, Long cboId, Integer page)