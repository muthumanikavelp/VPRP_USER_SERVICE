package com.vprp.user.services;

import java.util.*;
import java.util.stream.Collectors;

import com.vprp.user.dto.*;
import com.vprp.user.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vprp.user.repository.ApplicationRolesRepository;
import com.vprp.user.repository.BlockDataRepository;
import com.vprp.user.repository.DistrictDataRepository;
import com.vprp.user.repository.PanchayatDataRepository;
import com.vprp.user.repository.ShgDataRepository;
import com.vprp.user.repository.ShgDataRepository.Shg;
import com.vprp.user.repository.StateDataRepository;
import com.vprp.user.repository.UserCBORepository;
import com.vprp.user.repository.VillageDataRepository;
import com.vprp.user.repository.VoDataRepository;
import com.vprp.user.utils.AppConstant;

@Service
public class MasterDataServices {

	@Autowired
	StateDataRepository stateDataRepository;

	@Autowired
	DistrictDataRepository districtDataRepository;

	@Autowired
	BlockDataRepository blockDataRepository;

	@Autowired
	PanchayatDataRepository panchayatDataRepository;

	@Autowired
	VillageDataRepository villageDataRepository;

	@Autowired
	VoDataRepository voDataRepository;

	@Autowired
	ShgDataRepository shgDataRepository;

	@Autowired
	ApplicationRolesRepository applicationRoleRepository;

	@Autowired
	UserCBORepository userCBORepository;

	public List<VillageDataListDto> getAllVilageByFiveInput(Long stateId, Long districtId, Long blockId,
			Long panchayatId, Long villageId) {
		List<VillageDataListDto> villageData = new ArrayList<VillageDataListDto>();
		villageDataRepository.getAllVillageDataByFiveInput(stateId, districtId, blockId, panchayatId, villageId)
				.forEach(villageData::add);
		return villageData;

	}

	public List<VillageDataListDto> getAllVillageDataByFourInput(Long stateId, Long districtId, Long blockId,
			List<Long> panchayatIds) {
		List<VillageDataListDto> villageData = new ArrayList<VillageDataListDto>();
		villageDataRepository.getAllVillageDataByFourInput(stateId, districtId, blockId, panchayatIds)
				.forEach(villageData::add);
		return villageData;

	}

	public List<PanchayatListDto> getAllPanchayatByThreeInput(Long stateId, Long districtId, Long blockId) {
		List<PanchayatListDto> panchayatData = new ArrayList<PanchayatListDto>();
		panchayatDataRepository.getAllPanchayatByThreeInput(stateId, districtId, blockId).forEach(panchayatData::add);
		return panchayatData;

	}

	public List<PanchayatListDto> getAllPanchayatByFourInput(Long stateId, Long districtId, Long blockId,
			Long panchayatId) {
		List<PanchayatListDto> panchayatData = new ArrayList<PanchayatListDto>();
		panchayatDataRepository.getAllPanchayatByFourInput(stateId, districtId, blockId, panchayatId)
				.forEach(panchayatData::add);
		return panchayatData;
	}

	public List<StateListDto> getAllStateAndDistrict(Long stateId) {
		List<StateListDto> stateList = new ArrayList<StateListDto>();
		if (stateId > 0) {
			stateDataRepository.getAllStateAndDistrict(stateId).forEach(stateList::add);
		}
		return stateList;
	}

	public List<DistrictListDto> getDistrictById(Long districtId) {
		List<DistrictListDto> district = new ArrayList<DistrictListDto>();
		if (districtId > 0) {
			districtDataRepository.getAllStateAndDistrict(districtId).forEach(district::add);
		}
		return district;
	}

	public List<DistrictListDto> getDistrictByStateId(Long stateId) {
		List<DistrictListDto> district = new ArrayList<DistrictListDto>();
		if (stateId > 0) {
			districtDataRepository.getAllDistrictByStateId(stateId).forEach(district::add);
		}
		return district;
	}

	public List<StateMaster> getAllState() {
		List<StateMaster> stateData = new ArrayList<StateMaster>();
		stateDataRepository.findAll().forEach(stateData::add);
		// define custom order to sort a list using lambda expression
		Collections.sort(stateData, Collections.reverseOrder());
		return stateData;
	}

	public List<DistrictMaster> getAllDistrict() {
		List<DistrictMaster> districtData = new ArrayList<DistrictMaster>();
		districtDataRepository.findAll().forEach(districtData::add);
		return districtData;
	}

	public List<BlockMaster> getAllBlock() {
		List<BlockMaster> blockData = new ArrayList<BlockMaster>();
		blockDataRepository.findAll().forEach(blockData::add);
		return blockData;
	}

	public List<PanchayatMaster> getAllPanchayat() {
		List<PanchayatMaster> panchayatData = new ArrayList<PanchayatMaster>();
		panchayatDataRepository.findAll().forEach(panchayatData::add);
		return panchayatData;
	}

	public List<PanchayatMaster> getAllPanchayatByIds(List<Long> panchayatIds) {
		List<PanchayatMaster> panchayatList = new ArrayList<PanchayatMaster>();
		for(Long gp: panchayatIds){
			Optional<PanchayatMaster> panchayatData = panchayatDataRepository.findById(gp);
			if(panchayatData.isPresent())
				panchayatList.add(panchayatData.get());
		}
		return panchayatList;
	}

	public List<PanchayatMasterResponse> getPanchayatByPanchayatId(Set<Long> panchayatIds) {
		return panchayatDataRepository.getPanchayatByPanchayatId(panchayatIds);
	}

	public List<VillageMaster> getAllVillage() {
		List<VillageMaster> villageData = new ArrayList<VillageMaster>();
		villageDataRepository.findAll().forEach(villageData::add);
		return villageData;
	}

	public List<FederationProfiles> getAllVosByGP(List<Long> panchayatIds) {
		List<FederationProfiles> voData = new ArrayList<FederationProfiles>();
		voDataRepository.getAllVosByGp(panchayatIds).forEach(voData::add);
		return voData;
	}

//	public List<Shg> getAllShgsByGP(List<Long> pachayatIds) {
//		List<Shg> shgData = new ArrayList<Shg>();
//		shgDataRepository.getAllShgsByGP(pachayatIds).forEach(shgData::add);
//		return shgData;
//	}

	public List<ShgProfiles> getAllShgsByVO(List<Long> voIds) {
		List<ShgProfiles> shgData = new ArrayList<ShgProfiles>();
		shgDataRepository.getAllShgsByVO(voIds).forEach(shgData::add);
		return shgData;
	}

	public List<ShgProfiles> getAllShgsByGp(List<Long> gpIds) {
		List<ShgProfiles> shgData = new ArrayList<ShgProfiles>();
		shgDataRepository.getAllShgsByGp(gpIds).forEach(shgData::add);
		return shgData;
	}

	public List<BlockListDto> getAllBlockByStateAndDistrict(Long stateId, Long districtId) {
		List<BlockListDto> blockData = new ArrayList<BlockListDto>();
		blockData.addAll(blockDataRepository.getAllBlockByStateAndDistrict(stateId, districtId));
		return blockData;
	}

	public List<ShgProfiles> getAllShgsByVO(List<Long> voIds, Long appId, Long roleId) {
		List<ShgProfiles> shgData = new ArrayList<ShgProfiles>();
		ApplicationRoles appRole = applicationRoleRepository.findCBOAssignmentRule(appId, roleId);
		if (!(appRole.getRoleName().toLowerCase().equals(AppConstant.ROLE_VPRP_SHG))
				|| Objects.equals(appRole.getCboAssignmentRule(), AppConstant.ALLOW_OVERLAP)) {
			// CurrentBehaviour
			shgData.addAll(shgDataRepository.getAllShgsByVO(voIds));
		} else if (appRole.getCboAssignmentRule() == AppConstant.RESTRICT_OVERLAP) {
			// OverLap behavior

			List<List<Integer>> userCBOIds = userCBORepository.getAllCBOIdByAppRoleId(roleId, "0");
			List<Long> userCBOLongIds = new ArrayList<Long>();

			for (List<Integer> i : userCBOIds) {
				for (Integer j : i) {
					userCBOLongIds.add(j.longValue());
				}

			}
			shgDataRepository.getAllShgsByVO(voIds).forEach((shgItem) -> {
				if (userCBOLongIds.contains(shgItem.getShgId())) {
					shgItem.setAssigned(true);
				} else {
					shgItem.setAssigned(false);
				}
				shgData.add(shgItem);
			});
		}

		return shgData;
	}

	public List<FederationProfiles> getAllVosByGP(List<Long> panchayatIds, Long appId, Long roleId) {
		List<FederationProfiles> voData = new ArrayList<FederationProfiles>();

		ApplicationRoles appRole = applicationRoleRepository.findCBOAssignmentRule(appId, roleId);
		if (!(appRole.getRoleName().toLowerCase().equals(AppConstant.ROLE_VPRP_VO))
			|| appRole.getCboAssignmentRule() == AppConstant.ALLOW_OVERLAP) {
			// CurrentBehaviour
			voDataRepository.getAllVosByGp(panchayatIds).forEach(voData::add);
		} else if ((appRole.getRoleName().toLowerCase().equals(AppConstant.ROLE_VPRP_VO))
				&& appRole.getCboAssignmentRule() == AppConstant.RESTRICT_OVERLAP) {
			// OverLap behavior
			List<List<Integer>> userCBOIds = userCBORepository.getAllCBOIdByAppRoleId(roleId, "1");
			List<Long> userCBOLongIds = new ArrayList<Long>();
			for (List<Integer> i : userCBOIds) {
				for (Integer j : i) {
					userCBOLongIds.add(j.longValue());
				}

			}
			voDataRepository.getAllVosByGp(panchayatIds).forEach((voItem) -> {
				if (userCBOLongIds.contains(voItem.getFederationId())) {
					voItem.setAssigned(true);
				} else {
					voItem.setAssigned(false);
				}
				voData.add(voItem);
			});
		}

		return voData;

	}

}
