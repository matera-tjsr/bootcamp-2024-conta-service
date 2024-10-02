package com.bootcamp.conta.service.service;

import com.bootcamp.conta.service.dto.pix.PixResponseDTO;
import com.bootcamp.conta.service.dto.pix.PixRequestDTO;
import com.bootcamp.conta.service.exception.ContaNaoExisteException;
import com.bootcamp.conta.service.exception.SaldoInsuficienteExisteException;
import com.bootcamp.conta.service.model.Conta;
import com.bootcamp.conta.service.model.Pix;
import com.bootcamp.conta.service.repository.ContaRepository;
import com.bootcamp.conta.service.repository.PixRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PixService {

    private final PixRepository pixRepository;
    private final ContaRepository contaRepository;

    @Transactional
    public PixResponseDTO enviarPix(PixRequestDTO requestPixDTO) {

        Optional<Pix> existingPix = pixRepository.findByIdempotencia(requestPixDTO.getIdempotencia());

        if (existingPix.isPresent()) {
            return new PixResponseDTO(
                existingPix.get().getCreatedAt(), "Pix já processado com sucesso (idempotente).", existingPix.get()
            );
        }

        Optional<Conta> contaPagador = contaRepository.findByChavePix(requestPixDTO.getChavePixPagador());

        if (contaPagador.isEmpty()) {
            throw new ContaNaoExisteException(String.format("Conta com a chave %s não existe", requestPixDTO.getChavePixPagador()));
        }

        Conta conta = contaPagador.get();

        if (requestPixDTO.getValor().compareTo(conta.getSaldo()) > 0) {
            throw new SaldoInsuficienteExisteException("Saldo insuficiente para realizar o Pix");
        }

        conta.sacar(requestPixDTO.getValor());
        contaRepository.save(conta);

        Optional<Conta> optionalConta = contaRepository.findByChavePix(requestPixDTO.getChavePixRecebedor());

        if (optionalConta.isEmpty()) {
            throw new ContaNaoExisteException(String.format("Conta com a chave %s não existe", requestPixDTO.getChavePixRecebedor()));
        }

        Conta contaRecebedor = optionalConta.get();
        contaRecebedor.depositar(requestPixDTO.getValor());
        contaRepository.save(contaRecebedor);

        Pix pix = Pix.builder()
            .createdAt(LocalDateTime.now())
            .conta(contaPagador.get())
            .chavePixPagador(requestPixDTO.getChavePixPagador())
            .chavePixRecebedor(requestPixDTO.getChavePixRecebedor())
            .valor(requestPixDTO.getValor())
            .idempotencia(requestPixDTO.getIdempotencia())
            .build();

        pixRepository.save(pix);

        PixResponseDTO pixResponseDTO = new PixResponseDTO();
        pixResponseDTO.setPix(pix);
        pixResponseDTO.setMessage("Pix realizado com sucesso");

        return pixResponseDTO;

    }

}
