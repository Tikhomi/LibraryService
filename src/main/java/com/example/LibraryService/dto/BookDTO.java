package com.example.LibraryService.dto;

public class BookDTO {
    String title;
    String author;
    boolean availability;
    public BookDTO(String title, String author, boolean availability) {
        this.title = title;
        this.author = author;
        this.availability = availability;
    }
}
