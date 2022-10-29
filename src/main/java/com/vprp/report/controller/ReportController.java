package com.vprp.report.controller;

import com.vprp.report.dto.GeneratePDFResponse;
import com.vprp.report.dto.ReportInputData;
import com.vprp.report.services.ReportGeneratorService;
import com.vprp.user.services.UserServices;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportGeneratorService reportGeneratorService;

    @Autowired
    UserServices userServices;

    @PostMapping(value = "/generate-pdf", produces = "application/pdf")
    public ResponseEntity<byte[]> generatePdf(@RequestAttribute("USER_SESSION") Map userSession, @RequestBody ReportInputData reportInputData) throws Exception {
        try {
            String loginId = userSession.get("LOGIN_ID").toString();
            Long userId = userServices.getUserIdByLoginId(loginId);

            GeneratePDFResponse reportUrl = reportGeneratorService.generateReport(userId, reportInputData);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            // Here you have to set the actual filename of your pdf
            String filename = reportUrl.getGeneratedPath();
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity<>(reportUrl.getPdfData(), headers, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }

    }
}

