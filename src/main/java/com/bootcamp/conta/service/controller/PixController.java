package com.bootcamp.conta.service.controller;

import com.bootcamp.conta.service.dto.pix.PixResponseDTO;
import com.bootcamp.conta.service.dto.pix.PixRequestDTO;
import com.bootcamp.conta.service.service.PixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pix")
public class PixController {

    private final PixService service;

    @PostMapping
    public ResponseEntity<PixResponseDTO> pix(@RequestBody PixRequestDTO pixRequestDTO) {
        PixResponseDTO pixResponseDTO = service.enviarPix(pixRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pixResponseDTO);
    }
}
