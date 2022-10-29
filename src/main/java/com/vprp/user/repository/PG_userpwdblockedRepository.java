package com.vprp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vprp.user.entity.PG_userpwdblocked;

public interface PG_userpwdblockedRepository extends JpaRepository<PG_userpwdblocked, Long>{
	@Query("SELECT new com.vprp.user.entity.PG_userpwdblocked (u.id,u.password) "
            + "FROM PG_userpwdblocked u where u.password=?1")
	PG_userpwdblocked checkedBlockedpwd(String password);

}
