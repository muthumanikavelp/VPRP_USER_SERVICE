package com.vprp.user.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.dto.PG_CBOsDto;
import com.vprp.user.entity.PG_clfProfile;
import com.vprp.user.entity.PG_pgprofile;

public interface PG_pgprofileRepository extends JpaRepository<PG_pgprofile, Long>{
	
	@Query("select new com.vprp.user.entity.PG_pgprofile(p.pg_id,p.stateid,p.districtid"
			+ ",p.blockid,p.panchayatid,p.villageid,p.geoentitycode,p.pgcode,p.pgname"
			+ ",p.parentcboid,p.parentcbotype,p.datasource)"
			+ "from PG_pgprofile p where 1=1")
	List<PG_pgprofile> getPgprofile();
	
//	@Query("SELECT new com.vprp.user.entity.PG_pgprofile(p.pg_id,p.pgcode,p.pgname,3,p.panchayatid) "
//			+ "FROM PG_pgprofile p where p.panchayatid in (:panchayatIds)")
//	List<PG_pgprofile> getAllPgsByGp(@Param("panchayatIds") List<Long> panchayatIds);
	
	@Query("select new com.vprp.user.entity.PG_pgprofile(p.pg_id,p.pgcode,p.pgname,3,p.blockid) "
			+ "FROM PG_pgprofile p where p.blockid = ?1")
	List<PG_pgprofile> getAllPgsByGp(Long blockIds);
	
}
