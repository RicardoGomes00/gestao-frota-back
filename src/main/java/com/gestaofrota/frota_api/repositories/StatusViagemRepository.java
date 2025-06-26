package com.gestaofrota.frota_api.repositories;

import com.gestaofrota.frota_api.models.StatusViagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusViagemRepository extends JpaRepository<StatusViagem, Short> {

    Optional<StatusViagem> findByDescricao(String descricao);

}