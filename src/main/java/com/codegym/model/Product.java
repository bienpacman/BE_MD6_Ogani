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
    private String description;
    @Min(0)
    private double price;
    @Column (columnDefinition = "INT default 0")
    @Min(0)
    private Integer quantityStorage;
    @Column (columnDefinition = "BIGINT default 0")
    private Long sold;
    @Column(name = "is_Delete", columnDefinition = "boolean default false")
    private Boolean isDelete;
    @ManyToOne
    private ProductCategory productCategory;
    @ManyToOne
    private Seller seller;

}
