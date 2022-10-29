package com.vprp.user.dto;

import java.io.Serializable;

public class GpStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long gpId;
    private Integer gpSurveyStatus;

    public GpStatus(Long shgId, Integer shgSurveyStatus) {
        this.gpId = shgId;
        this.gpSurveyStatus = shgSurveyStatus;
    }

    public GpStatus(GpStatus changeStatus) {
    }

    public Long getGpId() {
        return gpId;
    }

    public void setGpId(Long gpId) {
        this.gpId = gpId;
    }

    public Integer getGpSurveyStatus() {
        return gpSurveyStatus;
    }

    public void setGpSurveyStatus(Integer gpSurveyStatus) {
        this.gpSurveyStatus = gpSurveyStatus;
    }
}
