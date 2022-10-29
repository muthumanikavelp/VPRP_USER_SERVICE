package com.vprp.user.dto;

import java.io.Serializable;

public class ShgStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long shgId;

    private Integer shgSurveyStatus;

    public ShgStatus(Long shgId, Integer shgSurveyStatus) {
        this.shgId = shgId;
        this.shgSurveyStatus = shgSurveyStatus;
    }

    public ShgStatus(ShgStatus changeStatus) {
    }

    public Long getShgId() {
        return shgId;
    }

    public void setShgId(Long shgId) {
        this.shgId = shgId;
    }

    public Integer getShgSurveyStatus() {
        return shgSurveyStatus;
    }

    public void setShgSurveyStatus(Integer shgSurveyStatus) {
        this.shgSurveyStatus = shgSurveyStatus;
    }
}
