package com.example.LibraryService.repository;

import com.example.LibraryService.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Serializable> {
    List<Rental> findByStartTimeBetween(Date startDate, Date endDate);
}
