package com.bootcamp.conta.service.dto.conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ContaRequestDTO {
    private String nomeTitular;
    private Integer numeroAgencia;
    private Integer numeroConta;
    private String chavePix;
}
