package com.bootcamp.conta.service.feign;

import com.bootcamp.conta.service.feign.dto.ChaveRequestDTO;
import com.bootcamp.conta.service.feign.dto.ChaveResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(contextId = "BacenClient", name = "Bacen", url = "http://localhost:9001/api/bacen")
public interface BacenClient {

    @PostMapping("/chaves")
    ChaveResponseDTO criarChave(ChaveRequestDTO chaveRequestDTO);

}
