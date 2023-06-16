package com.example.LibraryService.dto;

import com.example.LibraryService.entity.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RentalDTO {
    private Long id;
    private Data dt_start_time;
    private Data dt_end_time;
    private Integer overdue;

    public static RentalDTO toModel(Rental entity) {
        RentalDTO model = new RentalDTO();
        model.setId(entity.getId_rental());
        model.setDt_start_time((Data) entity.getDt_start_time());
        model.setDt_end_time((Data) entity.getDt_end_time());
        model.setOverdue(entity.getOverdue());
        return model;
    }
}
