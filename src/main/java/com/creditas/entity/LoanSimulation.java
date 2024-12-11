package com.creditas.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class LoanSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "rate_id")
    private CreditasRate rate;

    @OneToMany(mappedBy = "simulacaoEmprestimo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InstallmentPlan> installmentPlans;
}

