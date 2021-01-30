package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        customer.setId("10");
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(String customerId) {
        return customerRepository.findById(customerId);
    }
}
