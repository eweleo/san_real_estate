package com.san.realestate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Reservation extends AbstractEntity{
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Apartment apartment;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Double fullPrice;

    public Reservation() {
    }
}
