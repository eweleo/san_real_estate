package com.san.realestate.repository;

import com.san.realestate.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByEmailOrUsername(String email, String username);
    Optional<Customer> findCustomerByUsername(String username);
}
