package com.example.bank.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status_values")
@Getter
@Setter
public class ClientStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long id;

    @Column(name = "status_value", length = 50)
    private String value;

}
