package com.example.LibraryService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table(name = "rental")
@Data
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rental")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "dt_start_time")
    private Date startTime;

    @Column(name = "dt_end_time")
    private Date endTime;

    @Column(name = "overdue")
    private Integer overdue;

    @Column(name = "is_active")
    private boolean isActive;

//    private long calculateDaysOverdue() {
////        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        Date nowDate =
//        int result = LocalDate.now().compareTo(endTime);
////        if (isActive && )
//    }
}
