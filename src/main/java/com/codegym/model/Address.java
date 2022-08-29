package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nameAddress;
    @ManyToOne
    private Customer customer;
}
