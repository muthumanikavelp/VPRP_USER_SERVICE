package com.vprp.user.data.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.data.dto.MemberProfilesResponse;
import com.vprp.user.entity.MemberProfiles;

public interface MemberProfilesRepository extends JpaRepository<MemberProfiles, Long> {

	@Modifying
	@Transactional
	@Query("SELECT mp FROM MemberProfiles mp WHERE mp.cboType=0 AND mp.cboId IN (:cboIds)")
	List<MemberProfiles> getAllMemberProfiles(List<Long> cboIds);
		
}
