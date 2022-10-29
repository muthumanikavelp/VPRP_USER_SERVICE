package com.vprp.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vprp.user.dto.*;
import com.vprp.user.entity.*;
import com.vprp.user.repository.BlockDataRepository;
import com.vprp.user.repository.DistrictDataRepository;
import com.vprp.user.repository.PanchayatDataRepository;
import com.vprp.user.repository.ShgDataRepository.Shg;
import com.vprp.user.repository.StateDataRepository;
import com.vprp.user.repository.VillageDataRepository;
import com.vprp.user.services.MasterDataServices;
import com.vprp.user.utils.JwtTokenUtil;
import javassist.NotFoundException;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/masterdata")
public class MasterDataController {

	@Autowired
	MasterDataServices masterService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@GetMapping("/state/{state_id}")
	public List<StateListDto> getStateById(@PathVariable("state_id") Long stateId) throws Exception {
		try {
			return masterService.getAllStateAndDistrict(stateId);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/district/{district_id}")
	public List<DistrictListDto> getDistrictById(@PathVariable("district_id") Long districtId) throws Exception {
		try {
			return masterService.getDistrictById(districtId);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	
	@GetMapping("/districtbystate/{state_id}")
	public List<DistrictListDto> getDistrictByStateId(@PathVariable("state_id") Long districtId) throws Exception {
		try {
			return masterService.getDistrictByStateId(districtId);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/state")
	public List<StateMaster> getAllState() throws Exception {
		try {

			return masterService.getAllState();

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/district")
	public List<DistrictMaster> getAllDistrict() throws Exception {
		try {
			return masterService.getAllDistrict();

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/block")
	public List<BlockMaster> getAllBlock() throws Exception {
		try {
			return masterService.getAllBlock();

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/panchayat")
	public List<PanchayatMaster> getAllPanchayat() throws Exception {
		try {

			return masterService.getAllPanchayat();

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@PostMapping("/details/gp")
	public List<PanchayatMasterResponse> getAllPanchayatByIds(@RequestBody Set<Long> gpIds) throws Exception {
		try {

			return masterService.getPanchayatByPanchayatId(gpIds);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@PostMapping("/panchayat/vo")
	public List<FederationProfiles> getAllVosByGP(@RequestBody List<Long> panchayatIds) throws Exception {
		try {

			return masterService.getAllVosByGP(panchayatIds);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@PostMapping("/vo/shg")
	public List<ShgProfiles> getAllShgsByVO(@RequestBody List<Long> voIds) throws Exception {
		try {

			return masterService.getAllShgsByVO(voIds);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@PostMapping("/panchayat/vo/{app-id}/{role-id}")
	public ResponseEntity<List<FederationProfiles>> getAllVosByGP(@RequestBody List<Long> panchayatIds,
			@PathVariable("app-id") Long appId, @PathVariable("role-id") Long roleId) throws Exception {
		try {

			if (appId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			if (roleId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			List<FederationProfiles> fedProList = masterService.getAllVosByGP(panchayatIds, appId, roleId);

			return new ResponseEntity<>(fedProList, HttpStatus.OK);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@PostMapping("/vo/shg/{app-id}/{role-id}")
	public ResponseEntity<List<ShgProfiles>> getAllShgsByVO(@RequestBody List<Long> voIds, @PathVariable("app-id") Long appId,
			@PathVariable("role-id") Long roleId) throws Exception {
		try {
			if (appId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			if (roleId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			List<ShgProfiles> shgProList = masterService.getAllShgsByVO(voIds, appId, roleId);
			return new ResponseEntity<>(shgProList, HttpStatus.OK);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@PostMapping("/panchayat/shg")
	public List<ShgProfiles> getAllShgsByGp(@RequestBody List<Long> gpIds) throws Exception {
		try {

			return masterService.getAllShgsByGp(gpIds);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/village")
	public List<VillageMaster> getAllVillage() throws Exception {
		try {

			return masterService.getAllVillage();

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/block/state/{state_id}/district/{district_id}")
	public List<BlockListDto> getAllBlockByStateAndDistric(@PathVariable("state_id") Long stateId,
			@PathVariable("district_id") Long districtId) throws Exception {
		try {
			if (stateId <= 0) {
				throw new NotFoundException("Invalid State");
			}
			if (districtId <= 0) {
				throw new NotFoundException("Invalid Distict");
			}
			return masterService.getAllBlockByStateAndDistrict(stateId, districtId);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/panchayat/state/{state_id}/district/{district_id}/block/{block_id}/panchayat/{panchayat_id}")
	public List<PanchayatListDto> getAllPanchayatByFourInput(@PathVariable("state_id") Long stateId,
			@PathVariable("district_id") Long districtId, @PathVariable("block_id") Long blockId,
			@PathVariable("panchayat_id") Long panchayatId) throws Exception {
		try {
			if (stateId <= 0) {
				throw new NotFoundException("Invalid State");
			}
			if (districtId <= 0) {
				throw new NotFoundException("Invalid District");
			}
			if (blockId <= 0) {
				throw new NotFoundException("Invalid blockId");
			}

			return masterService.getAllPanchayatByFourInput(stateId, districtId, blockId, panchayatId);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/panchayat/state/{state_id}/district/{district_id}/block/{block_id}")
	public List<PanchayatListDto> getAllPanchayatByThreeInput(@PathVariable("state_id") Long stateId,
			@PathVariable("district_id") Long districtId, @PathVariable("block_id") Long blockId) throws Exception {
		try {
			if (stateId <= 0) {
				throw new NotFoundException("Invalid State");
			}
			if (districtId <= 0) {
				throw new NotFoundException("Invalid Distict");
			}
			if (blockId <= 0) {
				throw new NotFoundException("Invalid blockId");
			}
			return masterService.getAllPanchayatByThreeInput(stateId, districtId, blockId);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@PostMapping("/village/state/{state_id}/district/{district_id}/block/{block_id}/panchayat")
	public List<VillageDataListDto> getAllVilageByFourInput(@PathVariable("state_id") Long stateId,
			@PathVariable("district_id") Long districtId, @PathVariable("block_id") Long blockId,
			@RequestBody List<Long> panchayatIds) throws Exception {
		try {
			if (stateId <= 0) {
				throw new NotFoundException("Invalid State");
			}
			if (districtId <= 0) {
				throw new NotFoundException("Invalid Distict");
			}
			if (blockId <= 0) {
				throw new NotFoundException("Invalid blockId");
			}
			return masterService.getAllVillageDataByFourInput(stateId, districtId, blockId, panchayatIds);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/village/state/{state_id}/district/{district_id}/block/{block_id}/panchayat/{panchayat_id}/village/{village_id}")
	public List<VillageDataListDto> getAllVilageByFiveInput(@PathVariable("state_id") Long stateId,
			@PathVariable("district_id") Long districtId, @PathVariable("block_id") Long blockId,
			@PathVariable("panchayat_id") Long panchayatId, @PathVariable("village_id") Long villageId)
			throws Exception {
		try {
			if (stateId <= 0) {
				throw new NotFoundException("Invalid State");
			}
			if (districtId <= 0) {
				throw new NotFoundException("Invalid Distict");
			}
			if (blockId <= 0) {
				throw new NotFoundException("Invalid BlockId");
			}
			if (panchayatId <= 0) {
				throw new NotFoundException("Invalid Panchayat");
			}
			if (villageId <= 0) {
				throw new NotFoundException("Invalid VillageId");
			}
			return masterService.getAllVilageByFiveInput(stateId, districtId, blockId, panchayatId, villageId);

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

}
