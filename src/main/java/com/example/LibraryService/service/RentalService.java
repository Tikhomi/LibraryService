package com.example.LibraryService.service;

import com.example.LibraryService.dto.RentalDTO;
import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.repository.RentalRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    /**
     * Создание новой аренды.
     * @param rentEntity данные аренды.
     * @return созданную аренду с назначенным идентификатором.
     */
    public Rental createRent(Rental rentEntity) {
        return rentalRepository.save(rentEntity);
    }

    /**
     * Получение списка всех аренд.
     * @return список всех аренд.
     */
    public List<RentalDTO> getAllRents() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(RentalDTO::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Удаление конкретной аренды по ее идентификатору.
     * @param id идентификатор аренды.
     * @throws EntityNotFoundException если аренда с указанным идентификатором не найдена.
     */
    public void deleteRent(Long id) {
        Optional<Rental> optionalRent = rentalRepository.findById(id);
        if (optionalRent.isPresent()) {
            rentalRepository.delete(optionalRent.get());
        } else {
            throw new EntityNotFoundException("Rental not found with id: " + id);
        }
    }

    /**
     * Получение конкретной аренды по ее идентификатору.
     *
     * @param id идентификатор аренды.
     * @return аренду с указанным идентификатором.
     * @throws EntityNotFoundException если аренда с указанным идентификатором не найдена.
     */
    public RentalDTO getRentById(Long id) {
        Rental optionalRent = rentalRepository.findById(id).get();
        return RentalDTO.toModel(optionalRent);
    }

    public Long deleteRental(Long id) {
        rentalRepository.deleteById(id);
        return id;
    }

    public void processRentals() {
        List<Rental> activeRentals = rentalRepository.findActiveRentals();

        for (Rental rental : activeRentals) {
            if (rental.getEndTime().before(new Date())) {
                // Аренда просрочена, начислить штраф
                rental.setOverdue(calculateOverdueDays(rental.getEndTime()));
            }

            if (rental.isActive() && rental.getOverdue() == 0) {
                // Завершение аренды, изменить статус
                rental.setActive(false);
            }

            rentalRepository.save(rental);
        }
    }

    private int calculateOverdueDays(Date endTime) {
        Date currentDate = new Date();
        long diffInMillies = Math.abs(currentDate.getTime() - endTime.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int) diffInDays;  // Вычитаем 10 дней, так как аренда идет на 10 дней
    }
}
