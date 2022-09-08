package com.piai.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName)
                .lastName(request.lastName)
                .email(request.email)
                .build();

        // todo: check if email valid

        // todo: check if email not taken

        // todo: store customer in db
    }
}
