package com.gestaofrota.frota_api.repositories;

import com.gestaofrota.frota_api.models.StatusVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusVeiculoRepository extends JpaRepository<StatusVeiculo, Short> {

    Optional<StatusVeiculo> findByDescricao(String descricao);
}
