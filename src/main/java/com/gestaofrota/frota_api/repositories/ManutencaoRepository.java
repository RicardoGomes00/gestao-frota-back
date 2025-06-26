package com.gestaofrota.frota_api.repositories;

import com.gestaofrota.frota_api.models.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
}
