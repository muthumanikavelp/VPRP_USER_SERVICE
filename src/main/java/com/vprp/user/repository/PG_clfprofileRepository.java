package com.vprp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vprp.user.entity.PG_clfProfile;

public interface PG_clfprofileRepository extends JpaRepository<PG_clfProfile, Long>{
	
	@Query("select new com.vprp.user.entity.PG_clfProfile(p.clf_id,p.stateid,p.districtid"
			+ ",p.blockid,p.panchayatid,p.villageid,p.geoentitycode,p.clfcode,p.clfname"
			+ ",p.parentcboid,p.parentcbotype,p.datasource,p.statecode,p.districtcode"
			+ ",p.blockcode,p.panchayatcode,villagecode)"
			+ "from PG_clfProfile p where 1=1")
	List<PG_clfProfile> getclfprofile();
	
	@Query("SELECT new com.vprp.user.entity.PG_clfProfile(c.clf_id, c.clfcode, c.clfname,2,c.blockid,c.parentcboid) "
			+ "FROM PG_clfProfile c where c.blockid = ?1")
	List<PG_clfProfile> getAllClfsByGp(Long blockIds);
	
	@Query("SELECT new com.vprp.user.entity.PG_clfProfile(c.clf_id, c.clfcode, c.clfname,2,c.blockid) "
			+ "FROM PG_clfProfile c where c.blockcode = ?1")
	List<PG_clfProfile> getAllClfsByBlockCode(String blockcode);
}

