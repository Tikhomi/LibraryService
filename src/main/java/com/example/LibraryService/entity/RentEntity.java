package com.example.LibraryService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "sec_rent")
@Data
@AllArgsConstructor
@NoArgsConstructor//комент
public class RentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dt_start_time")
    private LocalTime startTime;

    @Column(name = "dt_end_time")
    private LocalTime endTime;

    @Column(name = "delay")
    private Integer delay;
}
