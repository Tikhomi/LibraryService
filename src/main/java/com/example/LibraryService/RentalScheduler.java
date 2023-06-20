package com.example.LibraryService;

import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class RentalScheduler {
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalScheduler(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void updateOverdue() {
        Iterable<Rental> rentals = rentalRepository.findAll();
        Date currentDate = new Date();
        for (Rental rental : rentals) {
            if (rental.isActive() && currentDate.after(rental.getEndTime())) {
                long daysDifference = ChronoUnit.DAYS.between(rental.getEndTime().toInstant(), currentDate.toInstant());
                rental.setOverdue(Math.max(0, Math.toIntExact(daysDifference)));
                rentalRepository.save(rental);
            }
        }
    }
}
