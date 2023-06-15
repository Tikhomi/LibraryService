package com.example.LibraryService.repository;

import com.example.LibraryService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
