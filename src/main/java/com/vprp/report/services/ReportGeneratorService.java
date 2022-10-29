package com.vprp.report.services;

import com.vprp.report.dto.GeneratePDFResponse;
import com.vprp.report.dto.ReportInputData;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.swing.text.Style;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

@Service
public class ReportGeneratorService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String PATH = System.getProperty("user.home") + "/";
//    private static final String PATH = "/home/ubuntu/";

    public GeneratePDFResponse generateReport(Long userId, ReportInputData reportInputData) throws JRException, SQLException, IOException {
        URL filePath = getClass().getResource("/" + reportInputData.getReportName() + ".jrxml");
        InputStream employeeReportStream
                = filePath.openStream();
        JasperReport jasperReport
                = JasperCompileManager.compileReport(employeeReportStream);
//        jasperReport.getDefaultStyle().setPdfEncoding("UTF-8");


        Connection con;
        Map<String, Object> params = new HashMap<>();
        GeneratePDFResponse generatePDFResponse = new GeneratePDFResponse();

        con = jdbcTemplate.getDataSource().getConnection();
        try {
            java.util.Locale locale = new Locale("en", "IN");
            params.put(JRParameter.REPORT_LOCALE, locale);

            addReportParams(reportInputData.getReportParams(), params);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, params, con);

            JRPdfExporter exporter = new JRPdfExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            String updatedPath = getPath(PATH, "/vprp_report/" + userId + "/");
            String reportFileName = reportInputData.getReportName() + getTimeStamp() + ".pdf";
            String completePath = updatedPath + "/" + reportFileName;
            exporter.setExporterOutput(
                    new SimpleOutputStreamExporterOutput(completePath));

            SimplePdfReportConfiguration reportConfig
                    = new SimplePdfReportConfiguration();
            reportConfig.setSizePageToContent(true);
            reportConfig.setForceLineBreakPolicy(false);


            SimplePdfExporterConfiguration exportConfig
                    = new SimplePdfExporterConfiguration();
            exportConfig.setMetadataAuthor("vprp");
//            exportConfig.setEncrypted(true);
            exportConfig.setAllowedPermissionsHint("PRINTING");


            exporter.setConfiguration(reportConfig);
            exporter.setConfiguration(exportConfig);
            exporter.exportReport();
            byte[] arr = convertPDF(completePath);

            generatePDFResponse.setGeneratedPath(completePath);
            generatePDFResponse.setPdfData(arr);
            generatePDFResponse.setReportFileName(reportFileName);

        }
        catch (Exception ex) {
           System.out.println(ex);
               ex.printStackTrace();
        }
        finally {
            con.close();
        }
        return generatePDFResponse;
    }

    private void addReportParams(List<Map<String, Object>> reportParams, Map<String, Object> params) {
        reportParams.forEach((reportParam) -> {
            if (reportParam.containsKey("castType") && reportParam.get("castType").toString().equals("Long")) {
                params.put(reportParam.get("name").toString(), Long.parseLong(reportParam.get("value").toString()));
            } else if (reportParam.containsKey("castType") && reportParam.get("castType").toString().equals("Locale")) {
                java.util.Locale locale = new Locale(reportParam.get("value").toString(), "IN");
                params.put(JRParameter.REPORT_LOCALE, locale);
            } else {
                params.put(reportParam.get("name").toString(), reportParam.get("value").toString());
            }
        });
    }

    private static String getPath(String path, String directory) {
        return fileWithDirectoryAssurance(path + directory).getAbsolutePath();
    }

    private static File fileWithDirectoryAssurance(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Folder Created Successfully");
            } else {
                System.out.println("Unable to create Folder");
            }
        }

        return dir;
    }

    private String getTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        //Returns current time in millis
        long timeMilli2 = calendar.getTimeInMillis();
        return String.valueOf(timeMilli2);
    }

    private static byte[] convertPDF(String path) throws IOException {
        Path pdfPath = Paths.get(path);
        byte[] pdf = Files.readAllBytes(pdfPath);
        return pdf;
    }

}
