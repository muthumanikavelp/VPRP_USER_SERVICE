package com.vprp.user.dto;

import java.io.Serializable;

public class VoStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long voId;

    private Integer voSurveyStatus;

    public VoStatus(Long shgId, Integer shgSurveyStatus) {
        this.voId = shgId;
        this.voSurveyStatus = shgSurveyStatus;
    }

    public VoStatus(VoStatus changeStatus) {
    }

    public Long getVoId() {
        return voId;
    }

    public void setVoId(Long voId) {
        this.voId = voId;
    }

    public Integer getVoSurveyStatus() {
        return voSurveyStatus;
    }

    public void setVoSurveyStatus(Integer voSurveyStatus) {
        this.voSurveyStatus = voSurveyStatus;
    }
}
