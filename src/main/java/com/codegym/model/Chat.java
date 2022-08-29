package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String content;
    @OneToOne
    private Seller seller;
    @OneToOne
    private Customer customer;
}
