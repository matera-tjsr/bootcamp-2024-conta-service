package com.bootcamp.conta.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PixResponseDTO {

    private LocalDateTime createdAt = LocalDateTime.now();
    private String message;
    private PixDTO pix;
}
