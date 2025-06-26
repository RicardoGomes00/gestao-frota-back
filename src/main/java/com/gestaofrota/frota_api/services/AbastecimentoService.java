package com.gestaofrota.frota_api.services;

import com.gestaofrota.frota_api.dtos.AbastecimentoDTO;
import com.gestaofrota.frota_api.models.*;
import com.gestaofrota.frota_api.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AbastecimentoService {

    private final AbastecimentoRepository abastecimentoRepository;
    private final VeiculoRepository veiculoRepository;
    private final MotoristaRepository motoristaRepository;
    private final ViagemRepository viagemRepository; // Para o relacionamento opcional

    public AbastecimentoService(AbastecimentoRepository abastecimentoRepository, VeiculoRepository veiculoRepository, MotoristaRepository motoristaRepository, ViagemRepository viagemRepository) {
        this.abastecimentoRepository = abastecimentoRepository;
        this.veiculoRepository = veiculoRepository;
        this.motoristaRepository = motoristaRepository;
        this.viagemRepository = viagemRepository;
    }


    public List<Abastecimento> buscarTodos() {
        return abastecimentoRepository.findAll();
    }


    @Transactional
    public Abastecimento registrar(AbastecimentoDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo com ID " + dto.getVeiculoId() + " não encontrado."));

        Motorista motorista = motoristaRepository.findById(dto.getMotoristaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista com ID " + dto.getMotoristaId() + " não encontrado."));

        Abastecimento novoAbastecimento = new Abastecimento();
        
        novoAbastecimento.setVeiculo(veiculo);
        novoAbastecimento.setMotorista(motorista);
        novoAbastecimento.setDataAbastecimento(dto.getDataAbastecimento());
        novoAbastecimento.setTipoCombustivel(dto.getTipoCombustivel());
        novoAbastecimento.setValorTotal(dto.getValorTotal());
        novoAbastecimento.setQuilometragemNoAbastecimento(dto.getQuilometragemNoAbastecimento());

        if (dto.getViagemId() != null) {
            Viagem viagem = viagemRepository.findById(dto.getViagemId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem associada com ID " + dto.getViagemId() + " não encontrada."));
            novoAbastecimento.setViagem(viagem);
        }

        return abastecimentoRepository.save(novoAbastecimento);
    }
}
