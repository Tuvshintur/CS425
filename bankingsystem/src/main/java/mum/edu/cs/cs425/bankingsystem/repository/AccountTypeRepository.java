package mum.edu.cs.cs425.bankingsystem.repository;

import mum.edu.cs.cs425.bankingsystem.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

}
