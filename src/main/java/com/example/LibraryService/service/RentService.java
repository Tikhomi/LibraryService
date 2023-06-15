package com.example.LibraryService.service;

import com.example.LibraryService.entity.Rent;
import com.example.LibraryService.repository.RentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private final RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    /**
     * Создание новой аренды.
     * @param rent данные аренды.
     * @return созданную аренду с назначенным идентификатором.
     */
    public Rent createRent(Rent rent) {
        return rentRepository.save(rent);
    }

    /**
     * Получение списка всех аренд.
     * @return список всех аренд.
     */
    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    /**
     * Обновление данных конкретной аренды.
     * @param id идентификатор аренды.
     * @param newRent обновленные данные аренды.
     * @return обновленную аренду.
     * @throws EntityNotFoundException если аренда с указанным идентификатором не найдена.
     */
    public Rent updateRent(Long id, Rent newRent) {
        Optional<Rent> optionalRent = rentRepository.findById(id);
        if (optionalRent.isPresent()) {
            Rent existingRent = optionalRent.get();
            existingRent.setStartTime(newRent.getStartTime());
            existingRent.setEndTime(newRent.getEndTime());
            existingRent.setDelay(newRent.getDelay());
            return rentRepository.save(existingRent);
        } else {
            throw new EntityNotFoundException("Rent not found with id: " + id);
        }
    }

    /**
     * Удаление конкретной аренды по ее идентификатору.
     * @param id идентификатор аренды.
     * @throws EntityNotFoundException если аренда с указанным идентификатором не найдена.
     */
    public void deleteRent(Long id) {
        Optional<Rent> optionalRent = rentRepository.findById(id);
        if (optionalRent.isPresent()) {
            Rent rent = optionalRent.get();
            rentRepository.delete(rent);
        } else {
            throw new EntityNotFoundException("Rent not found with id: " + id);
        }
    }

    /**
     * Получение конкретной аренды по ее идентификатору.
     * @param id идентификатор аренды.
     * @return аренду с указанным идентификатором.
     * @throws EntityNotFoundException если аренда с указанным идентификатором не найдена.
     */
    public Rent getRentById(Long id) {
        Optional<Rent> optionalRent = rentRepository.findById(id);
        if (optionalRent.isPresent()) {
            return optionalRent.get();
        } else {
            throw new EntityNotFoundException("Rent not found with id: " + id);
        }
    }
}
