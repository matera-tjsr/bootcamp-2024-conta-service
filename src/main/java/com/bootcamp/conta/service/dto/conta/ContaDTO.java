package com.bootcamp.conta.service.dto.conta;

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

    private UUID id;
    private String nomeTitular;
    private Integer numeroAgencia;
    private Integer numeroConta;
    private String chavePix;
    private BigDecimal saldo;

}
