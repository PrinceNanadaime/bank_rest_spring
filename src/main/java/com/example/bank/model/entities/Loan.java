package com.example.bank.model.entities;

import com.example.bank.model.entities.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Table(name = "loan")
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "debt_amount", precision = 15, scale = 2)
    private BigDecimal debtAmount;

    @Column(name = "closed")
    private boolean closed;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
