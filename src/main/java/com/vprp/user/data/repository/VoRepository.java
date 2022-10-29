package com.vprp.user.data.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vprp.user.entity.ShgProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.vprp.user.entity.FederationProfiles;
import org.springframework.transaction.annotation.Transactional;

public interface VoRepository extends JpaRepository<FederationProfiles, Long> {

    @Query("SELECT f FROM FederationProfiles f WHERE  f.federationId IN (:federationId) AND f.cboType=1")
    List<FederationProfiles> findFederationByCBOIds(List<Long> federationId);

    @Query("SELECT f FROM FederationProfiles f WHERE  f.panchayatId=?1 AND f.cboType=1")
    List<FederationProfiles> getAllVOsByGP(Long gpId);

    @Query("SELECT f FROM FederationProfiles f WHERE  f.federationId=?1 AND f.cboType=1")
    FederationProfiles findByIdWithType(Long federationId);

    @Query("SELECT f FROM FederationProfiles f WHERE  f.panchayatId IN (:gpIds) AND f.cboType=1")
    List<FederationProfiles> getAllVOsByGP(List<Long> gpIds);

    @Transactional
    @Query("SELECT vo FROM FederationProfiles vo WHERE vo.federationId IN (:voIds)")
    List<FederationProfiles> findShgByShgIds(List<Long> voIds);
}
