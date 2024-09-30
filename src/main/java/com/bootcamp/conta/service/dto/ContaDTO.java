package com.bootcamp.conta.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ContaDTO {

    @JsonIgnore
    private UUID id;
    private String nome;
    private Integer numeroAgencia;
    private Integer numeroConta;
    @JsonIgnore
    private BigDecimal saldo;

}
