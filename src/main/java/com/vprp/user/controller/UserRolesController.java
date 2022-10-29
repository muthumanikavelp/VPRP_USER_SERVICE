package com.vprp.user.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vprp.user.dto.ApplicationRolePermissionDto;
import com.vprp.user.dto.ApplicationRolesDto;
import com.vprp.user.entity.ApplicationRolePermissions;
import com.vprp.user.entity.ApplicationRoles;
import com.vprp.user.entity.UserRoles;
import com.vprp.user.repository.ApplicationRolePermissionRepository;
import com.vprp.user.repository.UserRoleRepository;
import com.vprp.user.services.ApplicationService;

@RestController
@RequestMapping("/applications")
public class UserRolesController {
	
	@Autowired
	ApplicationService applicationService;
	
	@GetMapping("/{application-id}/roles")
	public ResponseEntity<List<ApplicationRolesDto>> getApplicationPermissionById(@PathVariable("application-id") Long applicationId) {
		try {
			
			if (applicationId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(applicationService.userRoleByApplication(applicationId),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{application-id}/permissions")
	public ResponseEntity<List<ApplicationRolePermissionDto>> getApplicationRolesById(@PathVariable("application-id") Long applicationId) {
		try {
			
			if (applicationId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(applicationService.getApplictionRolePermissionById(applicationId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
