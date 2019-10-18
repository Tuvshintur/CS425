package mum.edu.cs.cs425.bankingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "accountTypes")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountTypeId;

    @NotBlank(message = "*accountTypeName is required")
    @Column(nullable = false, name = "accountTypeName")
    private String accountTypeName;

    public AccountType() {
    }

    public AccountType(@NotBlank(message = "*accountTypeName is required") String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

}
