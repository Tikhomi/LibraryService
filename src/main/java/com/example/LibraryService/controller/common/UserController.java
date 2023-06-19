package com.example.LibraryService.controller.common;

import com.example.LibraryService.dto.UserDTO;
import com.example.LibraryService.entity.User;
import com.example.LibraryService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public UserDTO getOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public void addUser(@RequestBody User user) {
        userService.addUserInSystem(user);
    }

    @DeleteMapping("admin/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
