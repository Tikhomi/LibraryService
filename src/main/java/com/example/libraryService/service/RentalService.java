package com.example.libraryService.service;

import com.example.libraryService.dto.RentalDTO;
import com.example.libraryService.entity.Rental;
import com.example.libraryService.repository.RentalRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public Rental createRent(Rental rentEntity) {
        return rentalRepository.save(rentEntity);
    }

    public List<RentalDTO> getAllRents() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteRent(Long id) {
        Optional<Rental> optionalRent = rentalRepository.findById(id);
        if (optionalRent.isPresent()) {
            rentalRepository.delete(optionalRent.get());
        } else {
            throw new EntityNotFoundException("Rental not found with id: " + id);
        }
    }

    public RentalDTO getRentById(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + id));
        return convertToDTO(rental);
    }

    private RentalDTO convertToDTO(Rental rental) {
        return new RentalDTO(rental.getId(),
                rental.getStartTime(), rental.getEndTime(), rental.getOverdue(), rental.isActive());
    }
}

