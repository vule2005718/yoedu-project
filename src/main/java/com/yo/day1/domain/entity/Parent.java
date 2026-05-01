package com.yo.day1.domain.entity;

import com.yo.day1.domain.AuditableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "parents")
@Data
@EqualsAndHashCode(callSuper = false)
public class Parent extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = false, length = 20)
    private  String phone;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String address;
}
