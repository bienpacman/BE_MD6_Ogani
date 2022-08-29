package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@Table(name = "comments")
public class ProductComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date creatAt;
    private String message;
    private int rating;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Customer customer;
}
