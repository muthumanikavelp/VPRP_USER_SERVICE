package com.vprp.user.services;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.vprp.captcha.CaptchaUtils;
import com.vprp.user.entity.CaptchaModel;
import com.vprp.user.dto.ApplicationRolePermissionDto;
import com.vprp.user.dto.ApplicationRolesDto;
import com.vprp.user.dto.UserCBOsDto;
import com.vprp.user.entity.User;
import com.vprp.user.repository.ApplicationRepository;
import com.vprp.user.repository.ApplicationRolePermissionRepository;
import com.vprp.user.repository.CaptchaRepository;
import com.vprp.user.entity.UserCBOs;
import com.vprp.user.enumurator.UserStatus;
import com.vprp.user.repository.UserCBORepository;
import com.vprp.user.repository.UserRepository;
import com.vprp.user.repository.UserRoleRepository;

import cn.apiclub.captcha.Captcha;

@Service
public class ApplicationService {
		
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	ApplicationRolePermissionRepository applicationRolePermissionsRepository;

	public List<ApplicationRolesDto> userRoleByApplication(Long applicationId) {
		return applicationRepository.applicationRoleByApplication(applicationId);		
	}

	public List<ApplicationRolePermissionDto> getApplictionRolePermissionById(Long applicationId) {
		return applicationRolePermissionsRepository.getApplictionRolePermissionById(applicationId);
		
	}

}
