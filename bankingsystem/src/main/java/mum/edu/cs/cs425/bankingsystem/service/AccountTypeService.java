package mum.edu.cs.cs425.bankingsystem.service;

import mum.edu.cs.cs425.bankingsystem.model.AccountType;
import org.springframework.data.domain.Page;

public interface AccountTypeService {

    public abstract Iterable<AccountType> getAllAccountTypes();
    public abstract Page<AccountType> getAllAccountTypesPaged(int pageNo);
    public abstract AccountType saveAccountType(AccountType accountType);
    public abstract void deleteAccounTypeById(int accountTypeId);
}
