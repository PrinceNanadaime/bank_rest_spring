package com.example.bank.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "acc_id", length = 7)
    private String id;

    @Column(name = "opening_date")
    private Date openingDate;

    @ManyToOne
    @JoinColumn(name = "acc_owner")
    private NaturalPerson owner;

    @Column(name = "current_balance", precision = 15, scale = 2, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private BigDecimal currentBalance;

    @OneToOne
    @JoinColumn(name = "acc_type")
    private AccountType type;
}
