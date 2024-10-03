package com.bootcamp.conta.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bootcamp.conta.service.dto.conta.ContaRequestDTO;
import com.bootcamp.conta.service.model.Conta;
import com.bootcamp.conta.service.repository.ContaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaRepository contaRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void deveCriarAContaComSucesso() throws Exception {

        ContaRequestDTO request = ContaRequestDTO.builder()
            .nomeTitular("Samuel")
            .numeroAgencia(10)
            .numeroConta(20)
            .chavePix("cliente@pix.com")
            .build();

        UUID idDaContaSalva = UUID.randomUUID();

        Conta contaMock = Conta.builder()
            .id(idDaContaSalva)
            .nomeTitular(request.getNomeTitular())
            .numeroAgencia(request.getNumeroAgencia())
            .numeroConta(request.getNumeroConta())
            .chavePix(request.getChavePix())
            .saldo(BigDecimal.ZERO)
            .build();

        when(contaRepository.findByNomeTitularAndNumeroContaAndChavePix(
            request.getNomeTitular(),
            request.getNumeroConta(),
            request.getChavePix()
        )).thenReturn(Optional.empty());

        when(contaRepository.save(any())).thenReturn(contaMock);

        mockMvc.perform(post("/api/contas")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("id").exists())
            .andExpect(jsonPath("nomeTitular").value("Samuel"))
            .andExpect(status().isCreated());
    }

    @Test
    void deveBuscarContasComSucesso() throws Exception {

        mockMvc.perform(
            get("/api/contas")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }
}
