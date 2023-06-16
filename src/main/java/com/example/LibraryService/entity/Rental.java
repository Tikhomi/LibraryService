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
    private Long id;

    @Column(name = "dt_start_time")
    private Date startTime;

    @Column(name = "dt_end_time")
    private Date endTime;

    @Column(name = "overdue")
    private Integer overdue;
}
