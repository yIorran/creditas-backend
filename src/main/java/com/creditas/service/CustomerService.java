package com.creditas.service;

import com.creditas.entity.Customer;

public interface CustomerService {

    Customer getCustomerByEmail(String customerEmail);

}
