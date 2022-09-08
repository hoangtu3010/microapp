package com.piai.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName)
                .lastName(request.lastName)
                .email(request.email)
                .build();

        // todo: check if email valid
//        if (repository.existsByEmail(customer.getEmail())){
//
//        }

        // todo: check if email not taken

        // todo: store customer in db
        repository.save(customer);
    }
}
