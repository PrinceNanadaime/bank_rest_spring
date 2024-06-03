package com.example.bank.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ownship_values")
@Getter
@Setter
public class Ownership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ownship_id")
    private Long id;

    @Column(name = "ownship_value", length = 100)
    private String value;
}
