package com.bootcamp.conta.service.controller;

import com.bootcamp.conta.service.dto.PixRequestDTO;
import com.bootcamp.conta.service.dto.PixResponseDTO;
import com.bootcamp.conta.service.service.PixService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/pix")
@RestController
@RequiredArgsConstructor
public class PixController {

    private final PixService pixService;

    @PostMapping
    public ResponseEntity<PixResponseDTO> pix(@RequestBody @Valid PixRequestDTO pixRequestDTO) {
        PixResponseDTO pixResponseDTO = pixService.realizaPix(pixRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pixResponseDTO);
    }

}
