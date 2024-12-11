package com.creditas.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class LoanSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientId;

    // Correção no relacionamento
    @ManyToOne
    @JoinColumn(name = "rate_id")  // Adicionando o nome correto da chave estrangeira
    private CreditasTaxa rate;

    @OneToMany(mappedBy = "simulacaoEmprestimo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InstallmentPlan> installmentPlans;
}

