package mum.edu.cs.cs425.bankingsystem.service.impl;

import mum.edu.cs.cs425.bankingsystem.model.Account;
import mum.edu.cs.cs425.bankingsystem.repository.AccountRepository;
import mum.edu.cs.cs425.bankingsystem.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll(Sort.by("accountNumber"));
    }

    @Override
    public Page<Account> getAllAccountsPaged(int pageNo) {
        return accountRepository.findAll(PageRequest.of(pageNo, 3, Sort.by("accountNumber")));
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Page<Account> getAccountsByCustomer_LastName(int pageNo, String searchString) {
        return accountRepository.findAccountsByCustomer_LastNameStartsWith(searchString, PageRequest.of(pageNo, 3));
    }

    @Override
    public Optional<Account> getAccountByAccountNumber(long accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

    @Override
    public double computeNetLiquidity() {
        return new BigDecimal((accountRepository.sumOfCheckingAccountsBalance() - accountRepository.sumOfLoanAccountsBalance())).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public void deleteAccountById(long accountId) {
        accountRepository.deleteById(accountId);
    }
}
