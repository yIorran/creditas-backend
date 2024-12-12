package com.creditas.repository;

import com.creditas.entity.CreditasRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<CreditasRate, Long> {

    @Query("SELECT c.rate FROM CreditasRate c WHERE :age BETWEEN c.minAge AND c.maxAge")
    Double findBetween(Integer age);

}
