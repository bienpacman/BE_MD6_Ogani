package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createAt;
    @ManyToOne
    private OrderStatus orderStatus;
    private double priceTotal;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Seller seller;
}
