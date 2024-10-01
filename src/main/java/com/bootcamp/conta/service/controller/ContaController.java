package com.bootcamp.conta.service.controller;

import com.bootcamp.conta.service.dto.ContaRequestDTO;
import com.bootcamp.conta.service.model.Conta;
import com.bootcamp.conta.service.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/contas")
@RestController
@RequiredArgsConstructor
public class ContaController {

    private final ContaRepository contaRepository;

    @PostMapping
    public ResponseEntity<Conta> conta(@RequestBody ContaRequestDTO contaRequestDTO){
        
        Conta conta = Conta.builder()
                .nomeTitular(contaRequestDTO.getNomeTitular())
                .numeroAgencia(contaRequestDTO.getNumeroAgencia())
                .numeroDaConta(contaRequestDTO.getNumeroDaConta())
                .chavePix(contaRequestDTO.getChavePix())
                .saldo(BigDecimal.ZERO)
        .build();

        conta = contaRepository.save(conta);

        return new ResponseEntity<>(conta, CREATED);
    }

//    @PutMapping("/{id}")
//    public Conta atualizarConta(@RequestBody ContaRequestDTO contaRequestDTO, @PathVariable UUID id){
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Conta> deletarConta(@PathVariable UUID id) {
//
//    }
//
    @PostMapping("/{id}")
    public Conta conta(@PathVariable UUID id) throws Exception {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new Exception("Conta não existe."));
        return conta;
    }

    @GetMapping
    public List<Conta> contas(){
        List<Conta> contas = contaRepository.findAll();
        return contas;
    }
}
