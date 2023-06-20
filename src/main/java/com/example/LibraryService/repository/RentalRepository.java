package com.example.LibraryService.repository;

import com.example.LibraryService.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query("SELECT r FROM Rental r WHERE r.isActive = true")
    List<Rental> findActiveRentals();
}
