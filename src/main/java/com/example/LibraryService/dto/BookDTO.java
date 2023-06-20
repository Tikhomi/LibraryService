package com.example.LibraryService.dto;

import com.example.LibraryService.entity.Book;
import com.example.LibraryService.entity.Rental;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookDTO {
    private String title;
    private String author;
    private List<RentalDTO> rentals;

    public static BookDTO toModel(Book entity) {
        BookDTO model = new BookDTO();
        model.setTitle(entity.getTitle());
        model.setAuthor(entity.getAuthor());
        List<RentalDTO> rentalDTOS = entity.getRentals().stream()
                .filter(Rental::isActive)
                .map(RentalDTO::toModel)
                .collect(Collectors.toList());
        model.setRentals(rentalDTOS);
        return model;
    }
}
