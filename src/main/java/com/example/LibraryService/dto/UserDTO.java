package com.example.LibraryService.dto;

import com.example.LibraryService.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;

    public static UserDTO toModel(User entity) {
        UserDTO model = new UserDTO();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        return model;
    }
}
