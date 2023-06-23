package com.example.LibraryService.dto;

import com.example.LibraryService.entity.Rental;
import lombok.Data;

import java.util.List;

@Data
public class UserRentalDTO {
    private String userName;
    private List<Rental> rentalList;
}
