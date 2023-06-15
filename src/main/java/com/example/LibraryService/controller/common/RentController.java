package com.example.LibraryService.controller.common;

import com.example.LibraryService.entity.RentEntity;
import com.example.LibraryService.service.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    /*
    * Контроллер для создания новой аренды
    */
    @PostMapping
    public ResponseEntity<RentEntity> createRent(@RequestBody RentEntity rentEntity) {
        RentEntity createdRentEntity = rentService.createRent(rentEntity);
        return new ResponseEntity<>(createdRentEntity, HttpStatus.CREATED);
    }

    /*
    * Контроллер для получения списка всех аренд
    */
    @GetMapping
    public ResponseEntity<List<RentEntity>> getAllRents() {
        List<RentEntity> rentEntities = rentService.getAllRents();
        return new ResponseEntity<>(rentEntities, HttpStatus.OK);
    }

    /*
    * Контроллер для обновления данных конкретной аренды
    */
    @PutMapping("/{id}")
    public ResponseEntity<RentEntity> updateRent(@PathVariable Long id, @RequestBody RentEntity rentEntity) {
        RentEntity updatedRentEntity = rentService.updateRent(id, rentEntity);
        return new ResponseEntity<>(updatedRentEntity, HttpStatus.OK);
    }

    /*
    * Контроллер для удаления конкретной аренды
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    * Контроллер для получения конкретной аренды по ее идентификатору
    */
    @GetMapping("/{id}")
    public ResponseEntity<RentEntity> getRentById(@PathVariable Long id) {
        RentEntity rentEntity = rentService.getRentById(id);
        return new ResponseEntity<>(rentEntity, HttpStatus.OK);
    }


}
