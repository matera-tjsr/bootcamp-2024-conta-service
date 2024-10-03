package com.bootcamp.conta.service.repository;

import com.bootcamp.conta.service.model.Pix;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<Pix, UUID> {
    Optional<Pix> findByIdempotencia(String idempotencia);
}