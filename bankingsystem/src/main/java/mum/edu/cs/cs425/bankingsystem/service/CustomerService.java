package mum.edu.cs.cs425.bankingsystem.service;

import mum.edu.cs.cs425.bankingsystem.model.Customer;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CustomerService {

    public abstract Iterable<Customer> getAllCustomers();
    public abstract Page<Customer> getAllCustomersPaged(int pageNo);
    public abstract Customer saveCustomer(Customer customer);
    public abstract Page<Customer> getCustomersByFirstName(int pageNo, String searchString);
    public abstract Optional<Customer> getCustomerByCustomerNumber(long customerId);
    public abstract void deleteCustomer(long customerId);
}
