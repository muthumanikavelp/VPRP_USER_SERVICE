package com.vprp.user.data.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vprp.user.data.dto.MemberProfilesResponse;
import com.vprp.user.data.dto.MessageBundleResponse;
import com.vprp.user.entity.MemberProfiles;
import com.vprp.user.entity.MessageBundle;

public interface MessageBundleRepository extends JpaRepository<MessageBundle, char[]> {
	List<MessageBundle> findAll();

	@Query("SELECT new com.vprp.user.data.dto.MessageBundleResponse(mb.langCode, mb.messageBundleJSON) "
			+ "FROM MessageBundle mb WHERE mb.langCode=?1")
	MessageBundleResponse findByLangCode(char[] langCode);

}
