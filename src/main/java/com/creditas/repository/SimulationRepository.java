package com.creditas.repository;

import com.creditas.entity.LoanSimulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationRepository extends JpaRepository<LoanSimulation, Long> {

}
