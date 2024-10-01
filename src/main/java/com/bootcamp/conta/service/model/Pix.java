package com.bootcamp.conta.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private String chavePixPagador;

    @Column
    private String chavePixRecebedor;

    @Column
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

}
