
package com.vprp.user.payload;

public class UploadRequest {

    private Long shgId;
    private Long voId;
    private Long gpId;
    private String jsonString;
    private Integer surveyYear;
    private Long userId;
    private String additionalData;

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    UploadRequest() {

    }


    public UploadRequest(Long shgId, Long voId, String jsonString, Integer surveyYear, Long userId, String additionalData) {
        super();
        this.shgId = shgId;
        this.voId = voId;
        this.jsonString = jsonString;
        this.surveyYear = surveyYear;
        this.userId = userId;
        this.additionalData = additionalData;
    }

    public Long getGpId() {
        return gpId;
    }

    public void setGpId(Long gpId) {
        this.gpId = gpId;
    }

    public Long getShgId() {
        return shgId;
    }

    public void setShgId(Long shgId) {
        this.shgId = shgId;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getVoId() {
        return voId;
    }

    public void setVoId(Long voId) {
        this.voId = voId;
    }
}