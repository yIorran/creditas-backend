package com.creditas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parcela;
    private String valor;

    @ManyToOne
    @JoinColumn(name = "installment_plan_id")  // A chave estrangeira correta
    private InstallmentPlan installmentPlan;
}

