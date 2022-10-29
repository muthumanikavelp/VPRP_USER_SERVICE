package com.vprp.validation;

import com.vprp.user.entity.User;
import com.vprp.user.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

public class UserValidation {
    @Autowired
    UserServices userServices;
    public User getUserByLoginId(String loginId) {
        User user = userServices.getUserByLoginId(loginId);
        if (user == null) {
            return null;
        } return user;

    }

    public Long getUserIdByLoginId(String loginId){
        return userServices.getUserIdByLoginId(loginId);
    }

    public static Boolean verifyUserGPWithRoleAndApp(String loginId, String applicationRoleName,
                                               String applicationName){
        return verifyUserGPWithRoleAndApp(loginId, applicationRoleName, applicationName);
    }

}

