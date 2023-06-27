package com.example.libraryService.dto;

import com.example.libraryService.entity.Rental;
import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    private String title;
    private String author;
    private List<Rental> rentals;

    public BookDTO(String title, String author, List<Rental> rentals) {
        this.title = title;
        this.author = author;
        this.rentals = rentals;
    }
}
