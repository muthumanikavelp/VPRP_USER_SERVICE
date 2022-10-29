package com.vprp.user.data.repository;
import com.vprp.user.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    @Query(value = "SELECT us FROM UserSession us WHERE us.loginId= ?1 ORDER BY us.createOn DESC")
    Optional<UserSession> findByUserName(String userSession);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserSession us SET us.status=0, us.updatedOn=?1 WHERE us.id= ?2")
    void updateSession(Date date , Long userSession);
}