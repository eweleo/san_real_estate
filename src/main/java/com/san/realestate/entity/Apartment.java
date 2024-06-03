package com.san.realestate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Apartment extends AbstractEntity {
    private String uuid;
    private String title;
    @Column(length = 4000)
    private String description;
    private Double price;

    public Apartment() {
    }
}
