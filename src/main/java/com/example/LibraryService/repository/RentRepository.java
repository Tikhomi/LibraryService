package com.example.LibraryService.repository;

import com.example.LibraryService.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
