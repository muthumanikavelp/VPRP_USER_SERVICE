package com.vprp.report.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class GeneratePDFResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String generatedPath;
	private byte[] pdfData;
	private String reportFileName;

	public GeneratePDFResponse(String generatedPath, byte[] pdfData, String reportFileName) {
		this.generatedPath = generatedPath;
		this.pdfData = pdfData;
		this.reportFileName = reportFileName;
	}

	public GeneratePDFResponse() {
	}

	public String getGeneratedPath() {
		return generatedPath;
	}

	public void setGeneratedPath(String generatedPath) {
		this.generatedPath = generatedPath;
	}

	public byte[] getPdfData() {
		return pdfData;
	}

	public void setPdfData(byte[] pdfData) {
		this.pdfData = pdfData;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public String getReportFileName() {
		return this.reportFileName;
	}
}