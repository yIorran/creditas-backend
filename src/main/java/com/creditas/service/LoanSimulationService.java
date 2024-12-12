package com.creditas.service;

import com.creditas.entity.LoanSimulation;

public interface LoanSimulationService {

    void saveLoanSimulation(String birthDate, String customerEmail, String customerName, LoanSimulation loanSimulation);

}