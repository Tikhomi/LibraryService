package com.example.LibraryService.repository;

import com.example.LibraryService.entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<RentEntity, Long> {
}
