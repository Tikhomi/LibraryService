package com.example.LibraryService.controller.common;

import com.example.LibraryService.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=rentals.xls";
        response.setHeader(headerKey, headerValue);
        reportService.generateExcel(response);
    }
}
