package com.example.bank.service;

import com.example.bank.model.entities.Account;
import com.example.bank.model.entities.Loan;
import com.example.bank.model.repos.AccountRepository;
import com.example.bank.model.repos.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LoanRepository loanRepository;

    // функция взноса
    public void deposit(String accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new EntityNotFoundException("Account not found"));

        // Проверка типа счета
        if (!account.getType().getValue().equals("кредитный") && amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Cannot deposit negative amount to non-credit account");
        }

        Optional<Loan> optionalLoan = loanRepository.findByAccount(account);
        BigDecimal newBalance = account.getCurrentBalance().add(amount);
        account.setCurrentBalance(newBalance);
        accountRepository.save(account);
    }

    // функция снятия
    public void withdraw(String accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new EntityNotFoundException("Account not found"));

        // Проверка типа счета
        if (account.getType().getValue().equals("кредитный")) {
            // Применение банковских правил для кредитных счетов
            BigDecimal balanceAfterWithdrawal = account.getCurrentBalance().subtract(amount);
            if (balanceAfterWithdrawal.compareTo(BigDecimal.ZERO) < 0) {
                // Взимание комиссии
                amount = amount.multiply(BigDecimal.valueOf(1.002));
            }
        } // Проверка баланса счета
        else {
            if (account.getCurrentBalance().compareTo(amount) < 0) {
                throw new IllegalStateException("Insufficient funds");
            }
        }

        BigDecimal newBalance = account.getCurrentBalance().subtract(amount);
        account.setCurrentBalance(newBalance);
        accountRepository.save(account);
    }
}
