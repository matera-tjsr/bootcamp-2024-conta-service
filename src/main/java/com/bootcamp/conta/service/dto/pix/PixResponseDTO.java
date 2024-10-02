package com.bootcamp.conta.service.dto.pix;

import com.bootcamp.conta.service.model.Pix;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PixResponseDTO {

    private LocalDateTime createdAt = LocalDateTime.now();
    private String message;
    private Pix pix;
}
