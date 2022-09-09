package com.piai.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final RestTemplate restTemplate;

    public Customer registerCustomer(CustomerRequest request) {
        try {
            Customer customer = Customer.builder()
                    .firstName(request.firstName)
                    .lastName(request.lastName)
                    .email(request.email)
                    .build();

            // todo: check if email valid
            if (repository.existsByEmail(customer.getEmail())) {
                log.error("Email is already exists!");
                return null;
            }

            // todo: store customer in db
            repository.saveAndFlush(customer);

            // todo: check if fraudster
            FraudCheckResponse response = restTemplate.getForObject(
                    "http://FRAUD/api/v1/fraud-check/{customerId}",
                    FraudCheckResponse.class,
                    customer.getId()
            );

            if (response.isFraudster) {
                throw new IllegalStateException("fraudster");
            }

            return customer;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return null;
    }
}
