package com.example.libraryService.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RentalDTO {
    private Long id;
    private Date startTime;
    private Date endTime;
    private Integer overdue;
    private boolean isActive;

    public RentalDTO(Long id, Date startTime, Date endTime, Integer overdue, boolean isActive) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.overdue = overdue;
        this.isActive = isActive;
    }
}
