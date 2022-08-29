package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "productimage")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String image;
    @ManyToOne
    private Product product;
}
