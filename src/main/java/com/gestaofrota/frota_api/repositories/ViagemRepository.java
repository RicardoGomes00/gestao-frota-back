package com.gestaofrota.frota_api.repositories;

import com.gestaofrota.frota_api.models.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    @Query("SELECT v FROM Viagem v WHERE v.veiculo.id = :veiculoId AND v.status.descricao IN ('AGENDADO', 'EM_USO')")
    List<Viagem> findViagensAtivasParaVeiculo(Long veiculoId);
    
}
