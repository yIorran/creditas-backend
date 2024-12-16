package com.creditas.service.impl;

import com.creditas.entity.Customer;
import com.creditas.entity.LoanSimulation;
import com.creditas.repository.CustomerRepository;
import com.creditas.repository.LoanSimulationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class LoanSimulationServiceImplTest {

    @Mock
    private LoanSimulationRepository loanSimulationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private LoanSimulationServiceImpl loanSimulationService;

    public LoanSimulationServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveLoanSimulationExistingCustomer() {
        String birthDate = "1990-01-01";
        String customerEmail = "test@example.com";
        String customerName = "Test User";
        LoanSimulation loanSimulation = new LoanSimulation();
        Customer customer = new Customer();

        when(customerRepository.getCustomerByEmail(customerEmail)).thenReturn(Optional.of(customer));

        loanSimulationService.saveLoanSimulation(birthDate, customerEmail, customerName, loanSimulation);

        verify(customerRepository, times(1)).getCustomerByEmail(customerEmail);
        verify(loanSimulationRepository, times(1)).save(loanSimulation);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testSaveLoanSimulationNewCustomer() {
        String birthDate = "1990-01-01";
        String customerEmail = "test@example.com";
        String customerName = "Test User";
        LoanSimulation loanSimulation = new LoanSimulation();

        when(customerRepository.getCustomerByEmail(customerEmail)).thenReturn(Optional.empty());

        loanSimulationService.saveLoanSimulation(birthDate, customerEmail, customerName, loanSimulation);

        verify(customerRepository, times(1)).getCustomerByEmail(customerEmail);
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(loanSimulationRepository, times(1)).save(loanSimulation);
    }
}
