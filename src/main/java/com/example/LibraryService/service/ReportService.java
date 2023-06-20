package com.example.LibraryService.service;

import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.repository.RentalRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private RentalRepository rentalRepository;
    /*
    * создание Excel-отчета с информацией о всех арендах
    */
    public void generateExcel(HttpServletResponse response) throws Exception {
        List<Rental> rentals = rentalRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Rental Info");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID аренды");
        row.createCell(1).setCellValue("Дата начала аренды");
        row.createCell(2).setCellValue("Дата окончания аренды");
        row.createCell(3).setCellValue("Просрочка");
        row.createCell(4).setCellValue("Активная аренда");
        row.createCell(5).setCellValue("Книга");
        row.createCell(6).setCellValue("Пользователь");
        int dataRowIndex = 1;
        for (Rental rental : rentals) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(rental.getId());
            dataRow.createCell(1).setCellValue(rental.getStartTime().toString());
            dataRow.createCell(2).setCellValue(rental.getEndTime().toString());
            Integer overdue = rental.getOverdue();
            dataRow.createCell(3).setCellValue(overdue != null ? overdue.intValue() : 0);
            dataRow.createCell(4).setCellValue(rental.isActive());
            dataRow.createCell(5).setCellValue(rental.getBook().getTitle());
            dataRow.createCell(6).setCellValue(rental.getUser().getEmail());
            dataRowIndex++;
        }
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

    /*
    * создание Excel-отчета с информацией об арендах, которые произошли за последний месяц(последние 30 дней)
    */
    public void generateLastMonthReport(HttpServletResponse response) throws Exception {
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysAgo = currentDate.minusDays(30);

        List<Rental> rentals = rentalRepository.findByStartTimeBetween(
                Date.from(thirtyDaysAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        );

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Rental Report - Last 30 Days");
        HSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID аренды");
        headerRow.createCell(1).setCellValue("Дата начала аренды");
        headerRow.createCell(2).setCellValue("Дата окончания аренды");
        headerRow.createCell(3).setCellValue("Просрочка");
        headerRow.createCell(4).setCellValue("Активная аренда");
        headerRow.createCell(5).setCellValue("Книга");
        headerRow.createCell(6).setCellValue("Пользователь");
        int dataRowIndex = 1;
        for (Rental rental : rentals) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(rental.getId());
            dataRow.createCell(1).setCellValue(rental.getStartTime().toString());
            dataRow.createCell(2).setCellValue(rental.getEndTime().toString());
            Integer overdue = rental.getOverdue();
            dataRow.createCell(3).setCellValue(overdue != null ? overdue.intValue() : 0);
            dataRow.createCell(4).setCellValue(rental.isActive());
            dataRow.createCell(5).setCellValue(rental.getBook().getTitle());
            dataRow.createCell(6).setCellValue(rental.getUser().getEmail());
            dataRowIndex++;
        }

        response.setHeader("Content-Disposition", "attachment; filename=RentalReport_Last30Days.xls");
        response.setContentType("application/vnd.ms-excel");

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

    /*
     * создание Excel-отчета с информацией об арендах, которые произошли за последний год(последние 365 дней)
     */
    public void generateLastYearReport(HttpServletResponse response) throws Exception {
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearAgo = currentDate.minusYears(1);

        List<Rental> rentals = rentalRepository.findByStartTimeBetween(
                Date.from(oneYearAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        );

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Rental Report - Last Year");
        HSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID аренды");
        headerRow.createCell(1).setCellValue("Дата начала аренды");
        headerRow.createCell(2).setCellValue("Дата окончания аренды");
        headerRow.createCell(3).setCellValue("Просрочка");
        headerRow.createCell(4).setCellValue("Активная аренда");
        headerRow.createCell(5).setCellValue("Книга");
        headerRow.createCell(6).setCellValue("Пользователь");
        int dataRowIndex = 1;
        for (Rental rental : rentals) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(rental.getId());
            dataRow.createCell(1).setCellValue(rental.getStartTime().toString());
            dataRow.createCell(2).setCellValue(rental.getEndTime().toString());
            Integer overdue = rental.getOverdue();
            dataRow.createCell(3).setCellValue(overdue != null ? overdue.intValue() : 0);
            dataRow.createCell(4).setCellValue(rental.isActive());
            dataRow.createCell(5).setCellValue(rental.getBook().getTitle());
            dataRow.createCell(6).setCellValue(rental.getUser().getEmail());
            dataRowIndex++;
        }

        response.setHeader("Content-Disposition", "attachment; filename=RentalReport_LastYear.xls");
        response.setContentType("application/vnd.ms-excel");

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }
}
