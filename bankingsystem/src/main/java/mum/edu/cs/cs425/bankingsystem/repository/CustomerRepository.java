package mum.edu.cs.cs425.bankingsystem.repository;

import mum.edu.cs.cs425.bankingsystem.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findCustomersByFirstNameStartsWith(String searchString, Pageable pageable);

    Optional<Customer> getCustomerByCustomerNumber(long customerId);
}
