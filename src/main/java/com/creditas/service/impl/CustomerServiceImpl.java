package com.creditas.service.impl;

import com.creditas.entity.Customer;
import com.creditas.repository.CustomerRepository;
import com.creditas.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomerByEmail(String customerEmail) {
        return null;
    }
}
