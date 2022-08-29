package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
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
    private String description;
    private double price;
    @Column (columnDefinition = "BIGINT default 0")
    private Long sold;
    @ManyToOne
    private ProductCategory productCategory;
    @ManyToOne
    private Seller seller;

}
