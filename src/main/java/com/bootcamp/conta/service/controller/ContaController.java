package com.bootcamp.conta.service.controller;

import com.bootcamp.conta.service.dto.ContaDTO;
import com.bootcamp.conta.service.dto.ContaRequestDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/contas")
@RestController
public class ContaController {

    private List<ContaDTO> contas = new ArrayList<>();

    @PostMapping
    public ContaDTO conta(@RequestBody ContaRequestDTO contaRequestDTO){

        ContaDTO conta = ContaDTO.builder()
            .id(UUID.randomUUID())
            .numeroAgencia(contaRequestDTO.getNumeroAgencia())
            .numeroConta(contaRequestDTO.getNumeroAgencia())
            .saldo(BigDecimal.ZERO)
            .nome(contaRequestDTO.getNome())
            .build();

        contas.add(conta);
        return conta;
    }

    @PutMapping("/{id}")
    public ContaDTO atualizarConta(@RequestBody ContaRequestDTO contaRequestDTO, @PathVariable UUID id){

        var conta = contas.stream().filter(contaDTO -> contaDTO.getId().equals(id)).findFirst();

        ContaDTO contaDTO = conta.get();

        contaDTO.setNumeroConta(contaRequestDTO.getNumeroConta());
        contaDTO.setNumeroAgencia(contaRequestDTO.getNumeroAgencia());
        contaDTO.setNome(contaRequestDTO.getNome());

        return contaDTO;
    }

    @GetMapping
    public List<ContaDTO> contas(){
        return contas;
    }
}
