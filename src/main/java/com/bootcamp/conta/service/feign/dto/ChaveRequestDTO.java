package com.bootcamp.conta.service.feign.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ChaveRequestDTO {
    private String chave;
    private Boolean ativa;
}
