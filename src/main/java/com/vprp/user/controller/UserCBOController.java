package com.vprp.user.controller;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.vprp.user.entity.UserCBOs;
import com.vprp.user.entity.UserRoleGeography;
import com.vprp.user.entity.UserRoles;
import com.vprp.user.model.UserCBOListResponseModel;
import com.vprp.user.model.UserCBOResponseModel;
import com.vprp.user.model.UserGeographyListResponseModel;
import com.vprp.user.model.UserResponseModel;
import com.vprp.user.model.UserRoleListResponseModel;
import com.vprp.user.repository.UserCBORepository;
import com.vprp.user.repository.UserRoleGeographyRepository;
import com.vprp.user.services.UserCBOService;
import com.vprp.user.utils.JwtTokenUtil;
import javassist.NotFoundException;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/security")
public class UserCBOController {

	@Autowired
	UserRoleGeographyRepository userRoleGeographyRepository;

	@Autowired
	UserCBORepository userCBORepository;

	@Autowired
	UserCBOService userCBOService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@PutMapping("/user/{userid}/cbos")
	public ResponseEntity<UserCBOResponseModel> createUserCBO(@PathVariable("userid") long userId,
			@RequestBody List<UserCBOs> cbo) {
		UserCBOResponseModel cboResponse = new UserCBOResponseModel();
		try {
			for (UserCBOs c : cbo) {
				Date currentDate = new Date();
				if (c.getId() != null) {

					Optional<UserCBOs> uc = userCBORepository.findById(c.getId());
					if (uc.isPresent()) {
						c.setStatus(uc.get().getStatus());
					} else {
						c.setStatus("ACTIVE");
					}

				} else {
					c.setStatus("ACTIVE");
				}
				c.setFromDate(currentDate);
			}
			List<UserCBOs> _cbo = userCBOService.addCboToUser(cbo, userId);
			if (_cbo.size() > 0) {
				cboResponse.setStatusCode(HttpStatus.OK.value());
				cboResponse.setData(_cbo);
				cboResponse.setStatusMessage("SUCCESS");
				return new ResponseEntity<>(cboResponse, HttpStatus.CREATED);
			} else {
				cboResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
				cboResponse.setData(null);
				cboResponse.setStatusMessage("FAILURE	");
				return new ResponseEntity<>(cboResponse, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/user/{userid}/geographies")
	public ResponseEntity<UserGeographyListResponseModel> createUserLocation(@PathVariable("userid") Long userId,
			@RequestBody List<UserRoleGeography> geography) {
		UserGeographyListResponseModel geoResponse = new UserGeographyListResponseModel();
		try {
			for (UserRoleGeography g : geography) {
				if (g.getId() != null) {
					Optional<UserRoleGeography> ge = userRoleGeographyRepository.findById(g.getId());
					if (ge.isPresent()) {
						g.setStatus(ge.get().getStatus());
					} else {
						g.setStatus("ACTIVE");
					}
				} else {
					g.setStatus("ACTIVE");
				}

			}
			List<UserRoleGeography> _geography = userCBOService.addGeographyToUser(geography, userId);
			geoResponse.setStatusCode(HttpStatus.OK.value());
			geoResponse.setData(_geography);
			geoResponse.setStatusMessage("SUCCESS");
			return new ResponseEntity<>(geoResponse, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/user/{userid}/roles")
	public ResponseEntity<UserRoleListResponseModel> addRoleToUser(@PathVariable("userid") long userId,
			@RequestBody UserRoles role) {
		UserRoleListResponseModel roleResponse = new UserRoleListResponseModel();
		try {
			UserRoles _role = userCBOService.addRoleToUser(role);
			if (_role.getId() > 0) {
				roleResponse.setStatusCode(HttpStatus.OK.value());
				roleResponse.setData(_role);
				roleResponse.setStatusMessage("SUCCESS");
				return new ResponseEntity<>(roleResponse, HttpStatus.CREATED);
			} else {
				roleResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
				roleResponse.setData(null);
				roleResponse.setStatusMessage("FAILURE	");
				return new ResponseEntity<>(roleResponse, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/usercbo/{id}")
	public ResponseEntity<UserCBOResponseModel> getUserCBOById(@PathVariable("id") long cboId) throws Exception {
		Optional<UserCBOs> _userCBO = userCBOService.getUserCBOById(cboId);
		UserCBOResponseModel userCBOResponse = new UserCBOResponseModel();
		try {
			if (_userCBO.isPresent()) {
				userCBOResponse.setStatusCode(HttpStatus.OK.value());
				userCBOResponse.setCboData(_userCBO.get());
				userCBOResponse.setStatusMessage("SUCCESS");
				return new ResponseEntity<>(userCBOResponse, HttpStatus.OK);
			} else {
				userCBOResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
				userCBOResponse.setData(null);
				userCBOResponse.setStatusMessage("FAILURE");
				return new ResponseEntity<>(userCBOResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@GetMapping("/usercbo/all")
	public ResponseEntity<UserCBOListResponseModel> getAllUsers(@RequestParam(required = false) String userName) {
		UserCBOListResponseModel userCBOList = new UserCBOListResponseModel();
		try {

			List<UserCBOs> usersCBOs = userCBOService.getAllUsers(userName);

			if (usersCBOs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			userCBOList.setStatusCode(HttpStatus.OK.value());
			userCBOList.setData(usersCBOs);
			userCBOList.setStatusMessage("SUCCESS");
			return new ResponseEntity<>(userCBOList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user/{userid}/cbos/{id}")
	public ResponseEntity<HttpStatus> deleteUserCbo(@PathVariable("userid") long userId, @PathVariable("id") long id) {
		try {
			userCBOService.removeCboFromUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user/{userid}/geographies/{id}")
	public ResponseEntity<HttpStatus> deleteUserGeography(@PathVariable("userid") long userId,
			@PathVariable("id") long id) {
		try {
			userCBOService.removeGeographyFromUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user/{userid}/roles/{id}")
	public ResponseEntity<HttpStatus> deleteUserRole(@PathVariable("userid") long userId, @PathVariable("id") long id) {
		try {
			userCBOService.removeRoleFromUser(id, userId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*PG Application Start*/
	@PostMapping("usercbo/pgDeleteRole")
	 public ResponseEntity<UserResponseModel> pgDeleteRole(@RequestParam Long UserId, String RoleName){
		 UserResponseModel userResponse = new UserResponseModel();
		 String Result = userCBOService.pgDeleteRolebyLoginId(UserId,RoleName);
		 try {
			 if(Result == "SUCCESS") {
				 userResponse.setStatusCode(HttpStatus.OK.value());
		         userResponse.setStatusMessage("SUCCESS");
		         return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	         }
			 else {
				 userResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		         userResponse.setStatusMessage("FAILURE");
		         return new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);
			 }
		 }catch(Exception ex) {
			 userResponse.setStatusMessage(ex.getMessage());
	         return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
		 }
		 
	 }
	/*PG Application End*/

}
