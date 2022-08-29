package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orderstatus", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nameOrderStatus"
        })
})
@Data
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOrderStatus;
}
