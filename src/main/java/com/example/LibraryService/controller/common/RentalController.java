package com.example.LibraryService.controller.common;

import com.example.LibraryService.dto.RentalDTO;
import com.example.LibraryService.entity.Rental;
import com.example.LibraryService.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    /*
     * Контроллер для создания новой аренды
     */
    @PostMapping("/add")
    public void createRent(@RequestBody Rental rentEntity) {
        rentalService.createRent(rentEntity);
    }

    /*
     * Контроллер для получения списка всех аренд
     */
    @GetMapping("/all")
    public List<RentalDTO> getAllRents() {
        return rentalService.getAllRents();
    }
    /*
     * Контроллер для удаления конкретной аренды
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentalService.deleteRent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * Контроллер для получения конкретной аренды по ее идентификатору
     */
    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentById(@PathVariable Long id) {
        RentalDTO rental = rentalService.getRentById(id);
        return ResponseEntity.ok(rental);
    }
}
