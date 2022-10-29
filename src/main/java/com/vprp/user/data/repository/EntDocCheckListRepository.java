package com.vprp.user.data.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.vprp.user.entity.EntDocCheckList;


public interface EntDocCheckListRepository extends JpaRepository<EntDocCheckList, Long> {
	
	@Modifying
	@Transactional
	@Query("SELECT m FROM EntDocCheckList m WHERE m.stateId = :stateId")
	List<EntDocCheckList> getEntDocCheckListData(Long stateId);
		
}
