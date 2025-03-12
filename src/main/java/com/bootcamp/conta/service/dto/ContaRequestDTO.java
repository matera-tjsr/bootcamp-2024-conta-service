package com.bootcamp.conta.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ContaRequestDTO {
    @NotEmpty(message = "E necessario informar um nome.")
    private String nomeTitular;
    @NotNull(message = "Deve ser informado a agencia. Ex: 0001")
    private Integer numeroAgencia;
    @NotNull
    private Integer numeroConta;
    @NotEmpty
    private String chavePix;
}
