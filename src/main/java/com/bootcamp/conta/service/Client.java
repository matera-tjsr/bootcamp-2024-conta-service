package com.bootcamp.conta.service;

import com.bootcamp.conta.service.dto.ContaDTO;
import com.bootcamp.conta.service.dto.ContaRequestDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(contextId = "exemplo", name = "nome", url = "localhost:9001/api")
public interface Client {

    @GetMapping("/contas")
    List<ContaDTO> contas();

    @PostMapping("/contas")
    ContaDTO contas(ContaRequestDTO contaDTO);

}
