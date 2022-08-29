package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cartdetail")
@Data
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Seller seller;
    private double quantity;
    private double totalPrice;
}
