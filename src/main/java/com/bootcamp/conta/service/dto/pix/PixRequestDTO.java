package com.bootcamp.conta.service.dto.pix;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PixRequestDTO {

    private String chavePixPagador;
    private String chavePixRecebedor;
    private BigDecimal valor;
    private String idempotencia;

}
