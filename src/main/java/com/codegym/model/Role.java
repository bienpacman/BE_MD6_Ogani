package com.codegym.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) //Khi thực hiện insert dữ liệu, khai báo kiểu dữ liệu, thay vì dùng kiểu String, ta dùng kiểu Enum như thế khi code sẽ đảm bảo được giữ liệu chỉ nhận các giá trị nhất định.
    @NaturalId
    @Column(length = 60)
    private RoleName name;


}
