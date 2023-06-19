package com.example.LibraryService.dto;

import com.example.LibraryService.entity.Rental;
import lombok.Data;

import java.util.Date;

@Data
public class RentalDTO {
    private Long id;
    private Date startTime;
    private Date endTime;
    private Integer overdue;
    private boolean isActive;

    public static RentalDTO toModel(Rental entity) {
        RentalDTO model = new RentalDTO();
        model.setId(entity.getId());
        model.setStartTime(entity.getStartTime());
        model.setEndTime(entity.getEndTime());
        model.setOverdue(entity.getOverdue());
        model.setActive(entity.isActive());
        return model;
    }
}
