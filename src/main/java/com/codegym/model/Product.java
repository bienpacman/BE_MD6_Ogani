package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Lob
    private String image;
    @Min(0)
    private int quantityStorage;
    private String description;
    @Min(0)
    private double price;
    @Column (columnDefinition = "BIGINT default 0")
    private Long sold;
    @ManyToOne
    private ProductCategory productCategory;
    @ManyToOne
    private Seller seller;

}
