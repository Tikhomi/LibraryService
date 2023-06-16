package com.example.LibraryService.service;

import com.example.LibraryService.entity.RentEntity;
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
     * @param rentEntity данные аренды.
     * @return созданную аренду с назначенным идентификатором.
     */
    public RentEntity createRent(RentEntity rentEntity) {
        return rentRepository.save(rentEntity);
    }

    /**
     * Получение списка всех аренд.
     * @return список всех аренд.
     */
    public List<RentEntity> getAllRents() {
        return rentRepository.findAll();
    }

    /**
     * Обновление данных конкретной аренды.
     * @param id идентификатор аренды.
     * @param newRentEntity обновленные данные аренды.
     * @return обновленную аренду.
     * @throws EntityNotFoundException если аренда с указанным идентификатором не найдена.
     */
    public RentEntity updateRent(Long id, RentEntity newRentEntity) {
        Optional<RentEntity> optionalRent = rentRepository.findById(id);
        if (optionalRent.isPresent()) {
            RentEntity existingRentEntity = optionalRent.get();
            existingRentEntity.setStartTime(newRentEntity.getStartTime());
            existingRentEntity.setEndTime(newRentEntity.getEndTime());
            existingRentEntity.setDelay(newRentEntity.getDelay());
            return rentRepository.save(existingRentEntity);
        } else {
            throw new EntityNotFoundException("RentEntity not found with id: " + id);
        }
    }

    /**
     * Удаление конкретной аренды по ее идентификатору.
     * @param id идентификатор аренды.
     * @throws EntityNotFoundException если аренда с указанным идентификатором не найдена.
     */
    public void deleteRent(Long id) {
        Optional<RentEntity> optionalRent = rentRepository.findById(id);
        if (optionalRent.isPresent()) {
            RentEntity rentEntity = optionalRent.get();
            rentRepository.delete(rentEntity);
        } else {
            throw new EntityNotFoundException("RentEntity not found with id: " + id);
        }
    }

    /**
     * Получение конкретной аренды по ее идентификатору.
     * @param id идентификатор аренды.
     * @return аренду с указанным идентификатором.
     * @throws EntityNotFoundException если аренда с указанным идентификатором не найдена.
     */
    public RentEntity getRentById(Long id) {
        Optional<RentEntity> optionalRent = rentRepository.findById(id);
        if (optionalRent.isPresent()) {
            return optionalRent.get();
        } else {
            throw new EntityNotFoundException("RentEntity not found with id: " + id);
        }
    }
}
