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
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private RentalRepository rentalRepository;
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
            dataRow.createCell(3).setCellValue(rental.getOverdue());
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
}
