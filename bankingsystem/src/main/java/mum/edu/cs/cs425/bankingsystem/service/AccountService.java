package mum.edu.cs.cs425.bankingsystem.service;

import mum.edu.cs.cs425.bankingsystem.model.Account;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AccountService {

    public abstract Iterable<Account> getAllAccounts();
    public abstract Page<Account> getAllAccountsPaged(int pageNo);
    public abstract Account saveAccount(Account account);
    public abstract Page<Account> getAccountsByCustomer_LastName(int pageNo, String searchString);
    public abstract Optional<Account> getAccountByAccountNumber(long accountNumber);
    public abstract double computeNetLiquidity();
    public abstract void deleteAccountById(long accountId);
}
