package com.vprp.user.data.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class DownloadGPAllRequest implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Long userId;
	List<String> dataset;
	List<String> langCodes;

	Boolean includeSurveyData;
	Integer surveyYear;
	List<Long> gpIdsToDownload;

	public List<Long> getGpIdsToDownload() {
		return gpIdsToDownload;
	}

	public void setGpIdsToDownload(List<Long> gpIdsToDownload) {
		this.gpIdsToDownload = gpIdsToDownload;
	}

	public DownloadGPAllRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DownloadGPAllRequest(Long userId, List<String> dataset, List<String> langCodes) {
		super();
		this.userId = userId;
		this.dataset = dataset;
		this.langCodes = langCodes;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<String> getDataset() {
		return dataset;
	}

	public Boolean getIncludeSurveyData() {
		return includeSurveyData;
	}

	public void setIncludeSurveyData(Boolean includeSurveyData) {
		this.includeSurveyData = includeSurveyData;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDataset(List<String> dataset) {
		this.dataset = dataset;
	}

	public List<String> getLangCodes() {
		return langCodes;
	}

	public void setLangCodes(List<String> langCodes) {
		this.langCodes = langCodes;
	}

}
