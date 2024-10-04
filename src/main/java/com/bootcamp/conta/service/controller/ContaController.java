package com.bootcamp.conta.service.controller;

import com.bootcamp.conta.service.dto.ContaDTO;
import com.bootcamp.conta.service.dto.ContaRequestDTO;
import com.bootcamp.conta.service.dto.ContaResponseDTO;
import com.bootcamp.conta.service.service.ContaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequestMapping("/api/contas")
@RestController
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaResponseDTO> conta(@RequestBody ContaRequestDTO contaRequestDTO) throws Exception {
        ContaResponseDTO contaResponseDTO = contaService.criarConta(contaRequestDTO);
        return new ResponseEntity<>(contaResponseDTO, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> atualizarConta(@RequestBody ContaRequestDTO contaRequestDTO, @PathVariable UUID id) throws Exception {
        ContaResponseDTO contaResponseDTO = contaService.atualizarConta(contaRequestDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(contaResponseDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable UUID id) {
        contaService.deletarConta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> conta(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.buscaContaById(id));
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> contas(){
        return ResponseEntity.status(HttpStatus.OK).body(contaService.buscaTodasContas());

    }
}
