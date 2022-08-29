package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "food_categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nameCategory"
        })
})
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nameCategory;
}
