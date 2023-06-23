package com.example.LibraryService.service;

import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.repository.RentalRepository;
import com.example.LibraryService.service.Impl.EmailSenderServiceImpl;
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

    @Scheduled(cron = "0 0 0 * * ?")
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
                String message = "Уважаемый(ая) " + rental.getUser().getFirstName() + " "
                        + rental.getUser().getLastName()
                        + ",\n\n";
                message += "Просим вас вернуть книгу '" + rental.getBook().getTitle()
                        + "', которую вы взяли в нашей библиотеке.\n";
                message += "Срок возврата этой книги истек. Пожалуйста, верните ее в библиотеку в ближайшее время.\n\n";
                message += "Благодарим вас за использование наших услуг.\n";
                message += "С наилучшими пожеланиями,\nВаша библиотека";
                emailSenderService.sendEmail(userEmail, subject, message);
            }
        }
    }
}
