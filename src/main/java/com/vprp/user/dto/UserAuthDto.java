package com.vprp.user.dto;

import java.io.Serializable;
import java.util.List;


public class UserAuthDto implements Serializable {
    private static final long serialVersionUID = 1L;


    private String loginId;

    private String password;

    private Long captchaId;

    private String captchaCode;


    public UserAuthDto(String loginId, String password, Long captchaId, String captchaCode) {
        super();

        this.loginId = loginId;

        this.password = password;

        this.captchaId = captchaId;

        this.captchaCode = captchaCode;
    }


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public Long getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(Long captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }
}


