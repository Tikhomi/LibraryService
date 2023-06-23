package com.example.LibraryService.controller.common;

import com.example.LibraryService.dto.UserDTO;
import com.example.LibraryService.dto.UserRentalDTO;
import com.example.LibraryService.entity.User;
import com.example.LibraryService.repository.UserRepository;
import com.example.LibraryService.service.RentalService;
import com.example.LibraryService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, RentalService rentalService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userService.addUserInSystem(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    /*
    * Профиль пользователя
    */
    @GetMapping("/profile")
    public UserRentalDTO viewUserProfile(Authentication authentication) throws Exception {
        return userService.viewUserProfile(authentication);
    }

    @GetMapping("/profile/my-rental")
    public UserDTO getRentalsForUser(Authentication authentication) throws Exception {
        return userService.getRentalsForUser(authentication);
    }
}
