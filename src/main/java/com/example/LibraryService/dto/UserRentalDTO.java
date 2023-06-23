package com.example.LibraryService.dto;

import com.example.LibraryService.entity.Rental;

import java.util.List;

public class UserRentalDTO {
    private String userName;
    private List<Rental> rentalList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Rental> getSchedules() {
        return rentalList;
    }

    public void setSchedules(List<Rental> schedules) {
        this.rentalList = schedules;
    }
}
