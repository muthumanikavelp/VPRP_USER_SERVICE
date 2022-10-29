package com.vprp.user.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.entity.PG_pgprofile;
import com.vprp.user.entity.PG_tpgprofile;

public interface PG_tpgprofileRepository extends JpaRepository<PG_tpgprofile, Long>{
	
//	@Transactional
//	@Modifying
//	@Query("Insert into PG_tpgprofile(pg_id,statecode,districtcode,blockcode,panchayatcode,villagecode"
//			+ ",pg_code,pg_name,parent_cbo_id,created_date,created_by)values(?1,?2,?3,?4,?5,?6,?7,?8,0,?9,?10)")
//	Integer insertByPGId(Long pg_id,String statecode,String districtcode,String blockcode,String panchayatcode,String villagecode,String pgname,LocalDateTime updatedate,String usercode);
//	
	@Query("select new com.vprp.user.entity.PG_tpgprofile(p.pg_id,p.pgcode,p.pgname,3,p.blockid) "
			+ "FROM PG_pgprofile p where p.pg_id = ?1")
	PG_tpgprofile getPgByPGId(Long pgId);
	
	@Transactional
	@Modifying
	@Query("Update PG_tpgprofile set statecode=?2,districtcode=?3,blockcode=?4,panchayatcode=?5,villagecode=?6,pg_name=?7"
			+ ",updateddate=?8,updatedby=?9,pgcode=?10 where pg_id = ?1")
	Integer updatePgByPGId(Long pg_id,String statecode,String districtcode,String blockcode,String panchayatcode,String villagecode,String pgname,LocalDateTime updateddate, String usercode,String pgcode);
}
