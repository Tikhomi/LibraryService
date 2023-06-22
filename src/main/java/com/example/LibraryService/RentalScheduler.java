package com.example.LibraryService;

import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.repository.RentalRepository;
import com.example.LibraryService.service.EmailSenderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class RentalScheduler {
    @Autowired
    private final RentalRepository rentalRepository;
    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    @Scheduled(cron = "0 * * * * ?")
    public void updateOverdue() {
        Iterable<Rental> rentals = rentalRepository.findAll();
        Date currentDate = new Date();
        for (Rental rental : rentals) {
            if (rental.isActive() && currentDate.after(rental.getEndTime())) {
                long daysDifference = ChronoUnit.DAYS.between(rental.getEndTime().toInstant(), currentDate.toInstant());
                rental.setOverdue(Math.max(0, Math.toIntExact(daysDifference)));
                rentalRepository.save(rental);

                String userEmail = rental.getUser().getEmail();
                String subject = "Просроченная аренда";
                String message = "Просрочилась аренда для пользователя " + rental.getUser().getLastName();
                emailSenderService.sendEmail(userEmail, subject, message);
            }
        }
    }
}
