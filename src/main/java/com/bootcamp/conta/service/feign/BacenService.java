package com.bootcamp.conta.service.feign;

import com.bootcamp.conta.service.exception.ErroCadastroChaveBacenException;
import com.bootcamp.conta.service.feign.dto.ChaveRequestDTO;
import com.bootcamp.conta.service.feign.dto.ChaveResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BacenService {

    private final BacenClient bacenClient;

    public ChaveResponseDTO criarChave(final String chave) {
        try {
            ChaveRequestDTO chaveRequestDTO = ChaveRequestDTO.builder()
                    .chave(chave)
                    .ativa(Boolean.TRUE)
                    .build();

            return bacenClient.criarChave(chaveRequestDTO);
        } catch (Exception ex) {
            log.error("Erro ao chamar a API de cadastrar chave do Bacen", ex);
            throw new ErroCadastroChaveBacenException("Erro ao cadastrar a chave no Bacen", ex);
        }

    }

}
