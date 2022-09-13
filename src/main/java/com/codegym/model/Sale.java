package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

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
    @Column(name = "status", columnDefinition = "boolean default false")
    private boolean status;
    private Timestamp startAt;
    private Timestamp endAt;

    @ManyToOne
    private Seller seller;

}
