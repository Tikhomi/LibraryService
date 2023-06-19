package com.example.LibraryService.dto;

import com.example.LibraryService.entity.User;
import com.example.LibraryService.entity.Rental;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<RentalDTO> rentals;

    public static UserDTO toModel(User entity) {
        UserDTO model = new UserDTO();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setEmail(entity.getEmail());
        List<RentalDTO> rentalDTOS = entity.getRentals().stream()
                .filter(Rental::isActive)
                .map(RentalDTO::toModel)
                .collect(Collectors.toList());

        model.setRentals(rentalDTOS);
        return model;
    }

}
