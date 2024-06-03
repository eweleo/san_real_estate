package com.san.realestate.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDTO {
    private String username;
    private Long apartmentId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
