package com.vprp.user.data.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vprp.user.entity.ShgApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.vprp.user.entity.VoApplications;

public interface VoApplicationsRepository extends JpaRepository<VoApplications, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM VoApplications vo WHERE vo.voId=?1")
	void deleteByVoId(Long id);

	@Query("SELECT vo FROM VoApplications vo WHERE vo.voId=?1")
	VoApplications findVoByVoId(Long shgId);
	
	@Query("SELECT vo FROM VoApplications vo WHERE vo.panchayatId=?1")
	Optional<VoApplications> findVoByPanchayat(Long gpId);

	@Query("SELECT vo FROM VoApplications vo WHERE vo.voId IN (:voIds)")
	List<VoApplications> findVoByVoId(Set<Long> voIds);

	@Transactional
	@Query("SELECT vo FROM VoApplications vo WHERE vo.voId IN (:test) AND vo.surveyYear=(:surveyYear)")
	List<VoApplications> getVoAppByIds(Integer surveyYear, List<Long> test);

}
