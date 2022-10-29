package com.vprp.report.dto;

import java.util.List;
import java.util.Map;

public class ReportInputData {
    private static final long serialVersionUID = 1L;

    private String reportName;
    private List<Map<String, Object>> reportParams;

    public ReportInputData() {

    }

    public ReportInputData(String reportName, List<Map<String, Object>> reportParams) {
        this.reportName = reportName;
        this.reportParams = reportParams;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public List<Map<String, Object>> getReportParams() {
        return reportParams;
    }

    public void setReportParams(List<Map<String, Object>> reportParams) {
        this.reportParams = reportParams;
    }
}
