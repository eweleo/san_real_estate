package com.san.realestate.service;

import com.san.realestate.entity.Customer;
import com.san.realestate.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer createUser(Customer newCustomer) {
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findByUsername(String username){
        return customerRepository.findCustomerByUsername(username);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public boolean isValidUser(Customer customer){
        return !customerRepository.existsByEmailOrUsername(customer.getEmail(), customer.getUsername());
    }
}
