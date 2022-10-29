package com.vprp.user.data.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.vprp.user.data.dto.ShgProfilesResponse;
import com.vprp.user.entity.DocLang;
import com.vprp.user.entity.FederationProfiles;
import com.vprp.user.entity.ShgProfiles;

public interface DocLangRepository extends JpaRepository<DocLang, Long> {
	
	@Query("SELECT d FROM DocLang d WHERE  d.docId IN (:docLangId)")
	List<DocLang> findDocLangById(Set<Long> docLangId);

}
