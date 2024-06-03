package com.san.realestate.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends AbstractEntity {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
    private String cardNumber;
}
