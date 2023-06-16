package com.example.LibraryService.dto;

import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Rental> rentals;

    public static UserDTO toModel(User entity) {
        UserDTO model = new UserDTO();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setRentals(entity.getRentals());
        return model;
    }
}
