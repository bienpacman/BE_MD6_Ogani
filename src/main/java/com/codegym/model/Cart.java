package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Customer customer;

}
