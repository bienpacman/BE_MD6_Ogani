package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Lob //chú thích cùng với @Column để nói rằng cột đó có kiểu BLOB
    private String avatar;
    @NotBlank
    private String address;
    @NotBlank
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

}
