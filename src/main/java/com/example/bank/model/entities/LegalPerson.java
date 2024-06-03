package com.example.bank.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "legal_person")
@Getter
@Setter
public class LegalPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lp_id")
    private Long id;

    @Column(name = "address", length = 255)
    private String address;

    @ManyToOne
    @JoinColumn(name = "ceo_id")
    private NaturalPerson ceo;

    @Column(name = "bookkeeper_full_name", length = 100)
    private String bookkeeperFullName;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "ownship")
    private Ownership ownership;
}
