package com.example.bank.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "in_staff_values")
@Getter
@Setter
public class StaffStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "in_staff_id")
    private Long id;

    @Column(name = "in_staff_value", length = 50)
    private String value;
}
