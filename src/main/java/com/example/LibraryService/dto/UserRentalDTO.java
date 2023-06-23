package com.example.LibraryService.dto;

import lombok.Data;

@Data
public class UserRentalDTO {
    private String firstName;
    private String lastName;
    private String email;

    public UserRentalDTO() {
    }

    public UserRentalDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
