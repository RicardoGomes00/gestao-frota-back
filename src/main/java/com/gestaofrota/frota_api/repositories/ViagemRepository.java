package com.gestaofrota.frota_api.repositories;

import com.gestaofrota.frota_api.models.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    List<Viagem> findByMotoristaIdAndStatusDescricaoIn(Long motoristaId, List<String> status);

}