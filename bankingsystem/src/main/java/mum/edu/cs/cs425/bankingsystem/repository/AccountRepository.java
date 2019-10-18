package mum.edu.cs.cs425.bankingsystem.repository;

import mum.edu.cs.cs425.bankingsystem.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Page<Account> findAccountsByCustomer_LastNameStartsWith(String searchString, Pageable pageable);

    Optional<Account> findAccountByAccountNumber(long accountNumber);

    @Query(value = "select COALESCE(sum(balance), 0) from accounts inner join account_types on accounts.account_type_id=account_types.account_type_id where account_type_name = 'Loan'", nativeQuery = true)
    double sumOfLoanAccountsBalance();

    @Query(value = "select COALESCE(sum(balance), 0) from accounts inner join account_types on accounts.account_type_id=account_types.account_type_id where account_type_name = 'Checking' or account_type_name ='Saving'", nativeQuery = true)
    double sumOfCheckingAccountsBalance();
}
