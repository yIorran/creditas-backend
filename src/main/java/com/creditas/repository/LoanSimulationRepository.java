package com.creditas.repository;

import com.creditas.entity.LoanSimulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanSimulationRepository extends JpaRepository<LoanSimulation, Long> {
}
