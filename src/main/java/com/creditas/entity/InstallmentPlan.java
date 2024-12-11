package com.creditas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class InstallmentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "installmentPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Installment> parcelas;

    @ManyToOne
    @JoinColumn(name = "simulacao_id")
    private LoanSimulation simulacaoEmprestimo;
}

