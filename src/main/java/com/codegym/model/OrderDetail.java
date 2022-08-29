package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orderdetails")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;
    private double quantity;
    private double price;

}
