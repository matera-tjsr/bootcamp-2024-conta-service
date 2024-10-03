package com.bootcamp.conta.service.controller.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.bootcamp.conta.service.dto.ContaRequestDTO;
import com.bootcamp.conta.service.model.Conta;
import com.bootcamp.conta.service.repository.ContaRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContaControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ContaRepository contaRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    void afterEach() {
        clearDatabase(contaRepository);
    }

    protected void clearDatabase(JpaRepository... repositories) {
        Arrays.stream(repositories).forEach(CrudRepository::deleteAll);
    }

    @Test
    void deveCriarContaComSucesso() throws IOException {

        String request = new String(Files.readString(Paths.get("src/test/resources/request-conta.json")));

        given()
            .contentType(ContentType.JSON)
            .body(request)
            .when()
            .post("/api/contas")
            .then()
            .assertThat()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", notNullValue())
            .body("nomeTitular", equalTo("Samuel X"));
    }

    @Test
    void deveBuscarTodasContasComSucesso() {

        Conta conta = Conta.builder()
            .nomeTitular("Samuel X")
            .numeroAgencia(200)
            .numeroConta(80)
            .chavePix("martin@pix.com")
            .saldo(BigDecimal.ZERO)
            .build();

        contaRepository.save(conta);

        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/api/contas")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("data.size()", equalTo(1))
            .body("[0].nomeTitular", equalTo("Samuel X"))
            .body("[0].numeroAgencia", equalTo(200))
            .body("[0].numeroConta", equalTo(80))
            .body("[0].chavePix", equalTo("martin@pix.com"));
    }

    @Test
    void deveRetonarContaExistenteExceptionQuandoContaJaExistir() {

        Conta conta = Conta.builder()
            .nomeTitular("Samuel teste exception")
            .numeroAgencia(100)
            .numeroConta(77)
            .chavePix("samuel-exception@pix.com")
            .saldo(BigDecimal.ZERO)
            .build();

        contaRepository.save(conta);

        ContaRequestDTO requestDTO = ContaRequestDTO.builder()
            .nomeTitular("Samuel teste exception")
            .numeroAgencia(100)
            .numeroConta(77)
            .chavePix("samuel-exception@pix.com").build();

        given()
            .contentType(ContentType.JSON)
            .body(requestDTO)
            .when()
            .post("/api/contas")
            .then()
            .assertThat()
            .statusCode(HttpStatus.CONFLICT.value())
            .body("detail", equalTo("Conta já existe."))
            .body("title", equalTo("Conflict"));
    }

}
