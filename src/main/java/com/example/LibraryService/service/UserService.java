package com.example.LibraryService.service;

import com.example.LibraryService.dto.UserDTO;
import com.example.LibraryService.entity.User;
import com.example.LibraryService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private  UserRepository userRepository;

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
                .map(UserDTO::toModel)
                .collect(Collectors.toList());
    }

    public UserDTO getOne(Long id) {
        User user = userRepository.findById(id).get();
        return UserDTO.toModel(user);
    }

    public Long delete(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
