package com.example.LibraryService.dto;

import com.example.LibraryService.entity.Rental;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private List<Rental> rentals;

    public UserDTO(String firstName, String lastName, String email, List<Rental> rentals) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rentals = rentals;
    }
}
