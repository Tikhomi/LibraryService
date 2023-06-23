package com.example.LibraryService.service;
import com.example.LibraryService.dto.UserDTO;
import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.entity.User;
import com.example.LibraryService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Long delete(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    private UserDTO convertToDTO(User user) {
        List<Rental> activeRentals = user.getRentals().stream()
                .filter(Rental::isActive)
                .collect(Collectors.toList());
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), activeRentals);
    }
}
