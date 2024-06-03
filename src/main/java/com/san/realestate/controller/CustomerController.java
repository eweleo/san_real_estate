package com.san.realestate.controller;

import com.san.realestate.entity.Customer;
import com.san.realestate.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(path = "/create")
    ResponseEntity<String> createUser(@RequestBody Customer newCustomer) {
        if(customerService.findByUsername(newCustomer.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("This username already exists");
        }

        Customer customer = customerService.createUser(newCustomer);
        if (customer.getId() != null) {
            return ResponseEntity.created(URI.create("/" + customer.getId())).body("Customer is created");
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{username}")
    ResponseEntity<Customer> findById(@PathVariable String username){
        Optional<Customer> customer = customerService.findByUsername(username);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
