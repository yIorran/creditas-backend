package com.creditas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LoanSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;

    @Column(name = "taxa")
    private Double rate;

    @Column(name = "qntdParcelas")
    private String quantidadeParcelas;

    @Column(name = "valorParcelas")
    private BigDecimal valorParcelas;

    @Column(name = "valorTotal")
    private BigDecimal valorTotal;
}

