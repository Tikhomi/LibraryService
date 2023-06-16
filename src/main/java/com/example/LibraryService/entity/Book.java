package com.example.LibraryService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long id;

    @Column(name = "nm_title")
    private String title;

    @Column(name = "nm_author")
    private String author;

    @OneToMany(mappedBy = "book")
    private List<Rental> rentals;
}
