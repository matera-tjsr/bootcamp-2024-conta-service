package com.bootcamp.conta.service.repository;

import com.bootcamp.conta.service.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

    Optional<Conta> findByChavePix(String chavePixPagador);

    Optional<Conta> findByNomeTitularAndNumeroContaAndChavePix(String nomeTitular, Integer numeroConta, String chavePix);
}
