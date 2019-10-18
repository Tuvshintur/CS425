package mum.edu.cs.cs425.bankingsystem.service.impl;

import mum.edu.cs.cs425.bankingsystem.model.Customer;
import mum.edu.cs.cs425.bankingsystem.repository.CustomerRepository;
import mum.edu.cs.cs425.bankingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll(Sort.by("lastName"));
    }

    @Override
    public Page<Customer> getAllCustomersPaged(int pageNo) {
        return customerRepository.findAll(PageRequest.of(pageNo, 3, Sort.by("lastName")));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> getCustomersByFirstName(int pageNo, String searchString) {
        return customerRepository.findCustomersByFirstNameStartsWith(searchString, PageRequest.of(pageNo, 3));
    }

    @Override
    public Optional<Customer> getCustomerByCustomerNumber(long customerId) {
        return customerRepository.getCustomerByCustomerNumber(customerId);
    }

    @Override
    public void deleteCustomer(long customerId) {
        customerRepository.deleteById(customerId);
    }
}
