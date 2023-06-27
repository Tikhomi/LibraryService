package com.example.libraryService.service.scheduler;

import com.example.libraryService.service.email.EmailSenderServiceImpl;
import com.example.libraryService.entity.Rental;
import com.example.libraryService.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class RentalScheduler {

    private final RentalRepository rentalRepository;
    private final EmailSenderServiceImpl emailSenderService;

    @Scheduled(fixedDelay = 60000)
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
