package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private double priceDiscount;
    @Column(name = "status", columnDefinition = "boolean default true")
    private boolean status;
    @Column(columnDefinition = "TIME")
    private String startAt;
    @Column(columnDefinition = "TIME")
    private String endAt;

    @ManyToOne
    private Seller seller;

}
