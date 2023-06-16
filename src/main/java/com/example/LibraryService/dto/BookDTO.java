package com.example.LibraryService.dto;

import com.example.LibraryService.entity.Rental;
import lombok.Data;

@Data
public class BookDTO {
    private String title;
    private String author;
    private Rental rental;
    public BookDTO(String title, String author, Rental rental) {
        this.title = title;
        this.author = author;
        this.rental = rental;
    }
}
