package com.san.realestate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentDTO {
    private String title;
    private String description;
    private Integer maxPerson;
    private Integer roomsNumber;
    private Double price;
}
