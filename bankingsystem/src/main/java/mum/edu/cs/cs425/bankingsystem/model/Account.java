package mum.edu.cs.cs425.bankingsystem.model;

import mum.edu.cs.cs425.bankingsystem.model.validator.UniqueAccountNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    @Digits(message = "* accountNumber is number", fraction = 0, integer = 9)
    @UniqueAccountNumber(message = "* accountNumber is required")
    @Column(name = "accountNumber", unique = true, nullable = false)
    private long accountNumber;

    @Column(name = "balance", nullable = false)
    private double balance;

    @ManyToOne()
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "accountTypeId", referencedColumnName = "accountTypeId")
    private AccountType accountType;

    public Account() {
    }

    public Account(@Digits(message = "* accountNumber is required", fraction = 2, integer = 9) long accountNumber, @Digits(message = "* balance is required", fraction = 2, integer = 9) long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
