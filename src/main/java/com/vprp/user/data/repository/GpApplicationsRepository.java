package com.vprp.user.data.repository;

import com.vprp.user.entity.GpApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GpApplicationsRepository extends JpaRepository<GpApplications, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM GpApplications  gp WHERE gp.gpId=?1")
	void deleteByGpId(Long id);

	@Query("SELECT gp FROM GpApplications gp WHERE gp.gpId=?1")
	Optional<GpApplications> findGpByGpId(Long gpId);
	
	@Query("SELECT gp FROM GpApplications gp WHERE gp.gpId=?1")
	Optional<GpApplications> findGpByPanchayat(Long gpId);

	@Query("SELECT gp FROM GpApplications gp WHERE gp.gpId IN (:gpIds) AND gp.surveyYear = :year")
	List<GpApplications> findAllGPbyId(Set<Long> gpIds, Integer year);

}
