package com.vprp.user.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.vprp.user.entity.ApplicationPermissions;
import com.vprp.user.repository.ApplicationPermissionRepository;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ApplicationController {
	
	@Autowired
	ApplicationPermissionRepository applicationPermissionRepository;

	@PostMapping("/application")
	public ResponseEntity<ApplicationPermissions> createApplication(@RequestBody ApplicationPermissions applicationPermissions) {
		try {
			ApplicationPermissions _applicationPermission = applicationPermissionRepository
					.save(new ApplicationPermissions(applicationPermissions.getApplicationId(), 
							applicationPermissions.getPermissionName()));
			return new ResponseEntity<>( _applicationPermission, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

	

}
