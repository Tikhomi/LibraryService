package com.example.LibraryService.controller.common;

import com.example.LibraryService.entity.Rent;
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
    public ResponseEntity<Rent> createRent(@RequestBody Rent rent) {
        Rent createdRent = rentService.createRent(rent);
        return new ResponseEntity<>(createdRent, HttpStatus.CREATED);
    }

    /*
    * Контроллер для получения списка всех аренд
    */
    @GetMapping
    public ResponseEntity<List<Rent>> getAllRents() {
        List<Rent> rents = rentService.getAllRents();
        return new ResponseEntity<>(rents, HttpStatus.OK);
    }

    /*
    * Контроллер для обновления данных конкретной аренды
    */
    @PutMapping("/{id}")
    public ResponseEntity<Rent> updateRent(@PathVariable Long id, @RequestBody Rent rent) {
        Rent updatedRent = rentService.updateRent(id, rent);
        return new ResponseEntity<>(updatedRent, HttpStatus.OK);
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
    public ResponseEntity<Rent> getRentById(@PathVariable Long id) {
        Rent rent = rentService.getRentById(id);
        return new ResponseEntity<>(rent, HttpStatus.OK);
    }


}
