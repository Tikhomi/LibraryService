package com.example.LibraryService.report;

import com.example.LibraryService.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/excel")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("")
    public void generateExcelReport(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=rentals.xls";
        response.setHeader(headerKey, headerValue);
        reportService.generateExcel(response);
    }

    @GetMapping("/last-month")
    public void generateLastMonthReport(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=rentals_last_month.xls";
        response.setHeader(headerKey, headerValue);
        reportService.generateLastMonthReport(response);
    }

    @GetMapping("/last-year")
    public void generateLastYearReport(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=rentals_last_year.xls";
        response.setHeader(headerKey, headerValue);
        reportService.generateLastYearReport(response);
    }

}
