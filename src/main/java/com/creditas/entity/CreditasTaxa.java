package com.creditas.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CreditasTaxa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_age")
    private String minAge;

    @Column(name = "max_age")
    private String maxAge;

    @Column(name = "taxa")
    private Double rate;

    @Column(name = "createdAt")
    private String createdAt;

    @OneToMany(mappedBy = "rate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoanSimulation> simulacoesEmprestimo;

}