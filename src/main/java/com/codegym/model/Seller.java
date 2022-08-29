package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "^0[0-9]{9}$")
    private String phoneNumber;
    @Lob
    private String avatar;
    @NotBlank
    private String address;
    @Column(name = "is_Accept", columnDefinition = "boolean default false")
    private Boolean isAccept;
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
}
