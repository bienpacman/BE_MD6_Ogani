package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
public class SellerRegisterRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @JsonIgnore
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    @NotBlank
    @Pattern(regexp = "^0[0-9]{9}$")
    private String phone;
    @NotBlank
    private String address;
    @Lob
    private String avatar;
    @Column(columnDefinition = "boolean default false")
    private Boolean isAccept;
}
