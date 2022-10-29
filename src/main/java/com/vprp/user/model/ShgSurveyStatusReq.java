package com.vprp.user.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ShgSurveyStatusReq {
	private Integer surveyYear;

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
}
