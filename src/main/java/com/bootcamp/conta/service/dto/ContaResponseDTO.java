package com.bootcamp.conta.service.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ContaResponseDTO {
    private UUID id;
    private String nomeTitular;
}
