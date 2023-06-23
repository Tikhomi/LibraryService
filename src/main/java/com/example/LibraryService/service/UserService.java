package com.example.LibraryService.service;
import com.example.LibraryService.dto.UserDTO;
import com.example.LibraryService.dto.UserRentalDTO;
import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.entity.User;
import com.example.LibraryService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUserInSystem(User user) {
        return userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getOne(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return convertToDTO(user);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Long delete(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    public UserRentalDTO viewUserProfile(Authentication authentication) throws Exception {
        /* проверка на аутентификацию пользователя */
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = getUserByEmail(email);

            /* Если пользователя не существует, выбрасываем исключение */
            if (user == null) {
                throw new Exception("User with email " + email + " not found");
            }

            return convertToProfile(user);
        } else {
            /* Если пользователь не аутентифицирован, выбрасываем исключение*/
            throw new AccessDeniedException("User is not authenticated");
        }
    }

    public UserDTO getRentalsForUser(Authentication authentication) throws Exception {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email);

            if (user == null) {
                throw new Exception("User with email " + email + " not found");
            }

            List<Rental> rentals = user.getRentals().stream().filter(rental -> rental.isActive() && rental.getEndTime().
                            toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now()))
                    .collect(Collectors.toList());

            UserRentalDTO userRentalDTO = new UserRentalDTO();
            userRentalDTO.setEmail(user.getFirstName() + " " + user.getLastName());
            return convertToDTO(user);
        }
        throw new AccessDeniedException("User is not authenticated");
    }
    public UserRentalDTO convertToProfile(User user) {
        return new UserRentalDTO(user.getFirstName(), user.getLastName(), user.getEmail());
    }
    public UserDTO convertToDTO(User user) {
        List<Rental> activeRentals = user.getRentals().stream()
                .filter(Rental::isActive)
                .collect(Collectors.toList());
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), activeRentals);
    }
}
