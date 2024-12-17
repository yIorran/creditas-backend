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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LoanSimulationServiceImplTest {

    public static final String CUSTOMER_NAME = "Test User";
    public static final String MAIL = "test@example.com";
    public static final String BIRTH_DATE = "1990-01-01";
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
        LoanSimulation loanSimulation = new LoanSimulation();
        Customer customer = new Customer();

        when(customerRepository.getCustomerByEmail(MAIL)).thenReturn(Optional.of(customer));

        loanSimulationService.saveLoanSimulation(BIRTH_DATE, MAIL, CUSTOMER_NAME, loanSimulation);

        verify(customerRepository, times(1)).getCustomerByEmail(MAIL);
        verify(loanSimulationRepository, times(1)).save(loanSimulation);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testSaveLoanSimulationNewCustomer() {
        LoanSimulation loanSimulation = new LoanSimulation();

        when(customerRepository.getCustomerByEmail(MAIL)).thenReturn(Optional.empty());

        loanSimulationService.saveLoanSimulation(BIRTH_DATE, MAIL, CUSTOMER_NAME, loanSimulation);

        verify(customerRepository, times(1)).getCustomerByEmail(MAIL);
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(loanSimulationRepository, times(1)).save(loanSimulation);
    }

    @Test
    public void testSaveLoanSimulationRepositoryException() {
        LoanSimulation loanSimulation = new LoanSimulation();

        when(customerRepository.getCustomerByEmail(MAIL)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> loanSimulationService.saveLoanSimulation(BIRTH_DATE, MAIL, CUSTOMER_NAME, loanSimulation));
    }
}