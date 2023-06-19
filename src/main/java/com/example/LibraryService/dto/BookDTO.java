package com.example.LibraryService.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String title;
    private String author;

    public BookDTO(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
