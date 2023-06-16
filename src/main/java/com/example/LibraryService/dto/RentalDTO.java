package com.example.LibraryService.dto;

import com.example.LibraryService.entity.Rental;
import lombok.Data;

@Data
public class RentalDTO {
    private Long id;
    private Data startTime;
    private Data endTime;
    private Integer overdue;

    public static RentalDTO toModel(Rental entity) {
        RentalDTO model = new RentalDTO();
        model.setId(entity.getId());
        model.setStartTime((Data) entity.getStartTime());
        model.setEndTime((Data) entity.getEndTime());
        model.setOverdue(entity.getOverdue());
        return model;
    }
}
