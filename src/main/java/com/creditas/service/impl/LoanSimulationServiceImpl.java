package com.creditas.service.impl;

import com.creditas.entity.Customer;
import com.creditas.entity.LoanSimulation;
import com.creditas.repository.CustomerRepository;
import com.creditas.repository.LoanSimulationRepository;
import com.creditas.service.LoanSimulationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoanSimulationServiceImpl implements LoanSimulationService {

    private final LoanSimulationRepository loanSimulationRepository;
    private final CustomerRepository customerRepository;

    @Async
    @Override
    @Transactional
    public void saveLoanSimulation(String birthDate, String customerEmail, String customerName, LoanSimulation loanSimulation) {
        log.info("Async process terminated");
        customerRepository.getCustomerByEmail(customerEmail).ifPresentOrElse(
                customer -> updateExistingCustomer(customer, loanSimulation),
                () -> createNewCustomer(birthDate, customerEmail, customerName, loanSimulation)
        );
        log.info("Async process terminated");
    }

    private void updateExistingCustomer(Customer customer, LoanSimulation loanSimulation) {
        loanSimulation.setCustomer(customer);
        customer.getLoanSimulations().add(loanSimulation);

        loanSimulationRepository.save(loanSimulation);
        customerRepository.save(customer);
        log.info("Customer updated: {}", customer);
    }

    private void createNewCustomer(String birthDate, String customerEmail, String customerName, LoanSimulation loanSimulation) {
        Customer customer = Customer.builder()
                .birthDate(birthDate)
                .email(customerEmail)
                .name(customerName)
                .build();
        loanSimulation.setCustomer(customer);
        customer.setLoanSimulations(List.of(loanSimulation));

        customerRepository.save(customer);
        loanSimulationRepository.save(loanSimulation);
        log.info("New customer created: {}", customer);
    }
}