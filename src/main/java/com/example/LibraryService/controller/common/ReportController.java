package com.example.LibraryService.controller.common;

import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.repository.RentalRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/reports")
public class ReportController {
    private final RentalRepository rentalRepository;

    @Autowired
    public ReportController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @GetMapping("/rental")
    public void generateRentalReport(HttpServletResponse response) throws IOException, IOException {
        Iterable<Rental> rentals = rentalRepository.findAll();

        // Создаем новую рабочую книгу Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Rental Report");

        // Создаем заголовки столбцов
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Start Time");
        headerRow.createCell(2).setCellValue("End Time");
        headerRow.createCell(3).setCellValue("Overdue");
        headerRow.createCell(4).setCellValue("Is Active");

        // Заполняем данные из таблицы Rental
        int rowNum = 1;
        for (Rental rental : rentals) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rental.getId());
            row.createCell(1).setCellValue(rental.getStartTime().toString());
            row.createCell(2).setCellValue(rental.getEndTime().toString());
            row.createCell(3).setCellValue(rental.getOverdue());
            row.createCell(4).setCellValue(rental.isActive());
        }

        // Устанавливаем заголовок и тип файла для скачивания
        response.setHeader("Content-Disposition", "attachment; filename=RentalReport.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Записываем данные в поток ответа
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
