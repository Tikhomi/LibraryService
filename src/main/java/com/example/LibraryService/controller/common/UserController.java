package com.example.LibraryService.controller.common;

import com.example.LibraryService.dto.UserDTO;
import com.example.LibraryService.dto.UserRentalDTO;
import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.entity.User;
import com.example.LibraryService.repository.UserRepository;
import com.example.LibraryService.service.RentalService;
import com.example.LibraryService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<UserDTO> viewUserProfile(Authentication authentication) throws AccessDeniedException {
        /* проверка на аутентификацию пользователя */
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userService.getUserByEmail(email);

            /*Если пользователя не существует, возвращаем ответ с кодом 404 Not Found */
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(UserDTO.toModel(user));
        }
        /* Если пользователь не аутентифицирован, возвращаем ответ с кодом 401 Unauthorized */
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/profile/my-rental")
    public ResponseEntity<UserRentalDTO> getRentalsForUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email);

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            List<Rental> rentals = user.getRentals().stream().filter(rental -> rental.isActive() && rental.getEndTime().
                            toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now()))
                    .collect(Collectors.toList());

            UserRentalDTO userRentalDTO = new UserRentalDTO();
            userRentalDTO.setUserName(user.getFirstName() + " " + user.getLastName());
            userRentalDTO.setRentalList(rentals);

            return ResponseEntity.ok(userRentalDTO);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
