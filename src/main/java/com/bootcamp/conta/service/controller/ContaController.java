package com.bootcamp.conta.service.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.bootcamp.conta.service.dto.conta.ContaDTO;
import com.bootcamp.conta.service.dto.conta.ContaRequestDTO;
import com.bootcamp.conta.service.dto.conta.ContaResponseDTO;
import com.bootcamp.conta.service.exception.ContaExistenteException;
import com.bootcamp.conta.service.exception.ContaNaoExisteException;
import com.bootcamp.conta.service.model.Conta;
import com.bootcamp.conta.service.repository.ContaRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

@Slf4j
@RequestMapping("/api/contas")
@RestController
@RequiredArgsConstructor
public class ContaController {

    private final ContaRepository contaRepository;

    @PostMapping
    public ResponseEntity<ContaResponseDTO> conta(@RequestBody ContaRequestDTO contaRequestDTO) throws Exception {

        Optional<Conta> contaOptional = contaRepository.findByNomeTitularAndNumeroContaAndChavePix(
            contaRequestDTO.getNomeTitular(),
            contaRequestDTO.getNumeroConta(),
            contaRequestDTO.getChavePix()
        );

        if (contaOptional.isPresent()){
            throw new ContaExistenteException("Conta já existe.");
        }
        
        Conta conta = Conta.builder()
                .nomeTitular(contaRequestDTO.getNomeTitular())
                .numeroAgencia(contaRequestDTO.getNumeroAgencia())
                .numeroConta(contaRequestDTO.getNumeroConta())
                .chavePix(contaRequestDTO.getChavePix())
                .saldo(BigDecimal.ZERO)
        .build();

        Conta contaSalva = contaRepository.save(conta);

        ContaResponseDTO contaResponseDTO = ContaResponseDTO.builder()
            .id(contaSalva.getId())
            .nomeTitular(contaRequestDTO.getNomeTitular())
            .build();

        log.info("ContaResponseDTO: {}", contaResponseDTO);


        return new ResponseEntity<>(contaResponseDTO, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> atualizarConta(@RequestBody ContaRequestDTO contaRequestDTO, @PathVariable UUID id) throws Exception {


        Conta contaExistente = contaRepository.findById(id).orElseThrow(() -> new Exception("Conta não existe."));

        contaExistente.setNomeTitular(contaRequestDTO.getNomeTitular());
        contaExistente.setNumeroConta(contaRequestDTO.getNumeroConta());
        contaExistente.setNumeroAgencia(contaRequestDTO.getNumeroAgencia());
        contaExistente.setChavePix(contaRequestDTO.getChavePix());

        contaExistente = contaRepository.save(contaExistente);

        ContaResponseDTO contaResponseDTO = ContaResponseDTO.builder()
            .id(contaExistente.getId())
            .nomeTitular(contaExistente.getNomeTitular())
            .build();

        return ResponseEntity.status(HttpStatus.OK).body(contaResponseDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable UUID id) {

        contaRepository.findById(id).orElseThrow(() -> new ContaNaoExisteException("Conta não existe."));

        contaRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping("/{id}")
    public ResponseEntity<ContaDTO> conta(@PathVariable UUID id) throws Exception {

        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoExisteException("Conta não existe."));

        ContaDTO contaDTO = ContaDTO.builder()
            .id(conta.getId())
            .nomeTitular(conta.getNomeTitular())
            .numeroAgencia(conta.getNumeroAgencia())
            .numeroConta(conta.getNumeroConta())
            .chavePix(conta.getChavePix())
            .saldo(conta.getSaldo())
            .build();

        return ResponseEntity.status(HttpStatus.OK).body(contaDTO);
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> contas(){

        List<ContaDTO> contas = contaRepository.findAll().stream().map(
            conta -> ContaDTO.builder()
                .id(conta.getId())
                .nomeTitular(conta.getNomeTitular())
                .numeroAgencia(conta.getNumeroAgencia())
                .numeroConta(conta.getNumeroConta())
                .chavePix(conta.getChavePix())
                .saldo(conta.getSaldo())
                .build())
            .toList();

        return ResponseEntity.status(HttpStatus.OK).body(contas);

    }
}
