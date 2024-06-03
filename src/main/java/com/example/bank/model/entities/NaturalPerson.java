package com.example.bank.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "natural_person")
@Getter
@Setter
public class NaturalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "np_id")
    private Long id;

    @Column(name = "np_name", length = 50)
    private String name;

    @Column(name = "np_surname", length = 50)
    private String surname;

    @Column(name = "np_patronymic", length = 50)
    private String patronymic;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "sex")
    private Gender sex;

    @OneToOne
    @JoinColumn(name = "status")
    private ClientStatus status;

    @OneToOne
    @JoinColumn(name = "in_staff")
    private StaffStatus inStaff;

}

