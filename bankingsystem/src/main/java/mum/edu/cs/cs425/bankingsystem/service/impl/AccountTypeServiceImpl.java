package mum.edu.cs.cs425.bankingsystem.service.impl;

import mum.edu.cs.cs425.bankingsystem.model.AccountType;
import mum.edu.cs.cs425.bankingsystem.repository.AccountTypeRepository;
import mum.edu.cs.cs425.bankingsystem.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private AccountTypeRepository accountTypeRepository;

    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public Iterable<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll(Sort.by("accountTypeId"));
    }

    @Override
    public Page<AccountType> getAllAccountTypesPaged(int pageNo) {
        return accountTypeRepository.findAll(PageRequest.of(pageNo, 3 , Sort.by("accountTypeId")));
    }

    @Override
    public AccountType saveAccountType(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    @Override
    public void deleteAccounTypeById(int accountTypeId) {
        accountTypeRepository.deleteById(accountTypeId);
    }
}
