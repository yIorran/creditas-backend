package com.creditas.repository;

import com.creditas.entity.CreditasTaxa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<CreditasTaxa, Long> {

    @Query("SELECT c.rate FROM CreditasTaxa c WHERE :age BETWEEN c.minAge AND c.maxAge")
    String findBetween(Integer age);

}
