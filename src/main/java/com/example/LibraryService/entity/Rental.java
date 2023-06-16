package com.example.LibraryService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "rental")
@Data
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rental")
    private Long id_rental;

    @Column(name = "dt_start_time")
    private Date dt_start_time;

    @Column(name = "dt_end_time")
    private Date dt_end_time;

    @Column(name = "overdue")
    private Integer overdue;
}