package com.example.LibraryService.repository;

import com.example.LibraryService.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RentalRepository extends JpaRepository<Rental, Serializable> {
}
