package com.gestaofrota.frota_api.repositories;

import com.gestaofrota.frota_api.models.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {

}
