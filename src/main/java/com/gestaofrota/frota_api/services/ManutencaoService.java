package com.gestaofrota.frota_api.services;

import com.gestaofrota.frota_api.dtos.ManutencaoDTO;
import com.gestaofrota.frota_api.models.Manutencao;
import com.gestaofrota.frota_api.models.StatusVeiculo;
import com.gestaofrota.frota_api.models.Veiculo;
import com.gestaofrota.frota_api.repositories.ManutencaoRepository;
import com.gestaofrota.frota_api.repositories.StatusVeiculoRepository;
import com.gestaofrota.frota_api.repositories.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;
    private final VeiculoRepository veiculoRepository;
    private final StatusVeiculoRepository statusVeiculoRepository;

    public ManutencaoService(ManutencaoRepository manutencaoRepository, VeiculoRepository veiculoRepository, StatusVeiculoRepository statusVeiculoRepository) {
        this.manutencaoRepository = manutencaoRepository;
        this.veiculoRepository = veiculoRepository;
        this.statusVeiculoRepository = statusVeiculoRepository;
    }


    public List<Manutencao> buscarTodos() {
        return manutencaoRepository.findAll();
    }


    @Transactional
    public Manutencao registrar(ManutencaoDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado."));
        
        Manutencao novaManutencao = new Manutencao();
        novaManutencao.setVeiculo(veiculo);
        novaManutencao.setDataInicio(dto.getDataInicio());
        novaManutencao.setDescricaoServico(dto.getDescricaoServico());
        novaManutencao.setCusto(dto.getCusto());
        novaManutencao.setQuilometragem(dto.getQuilometragem());
        
        StatusVeiculo statusManutencao = statusVeiculoRepository.findByDescricao("Em Manutenção")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Status 'Em Manutenção' não configurado no sistema."));
        
        veiculo.setStatus(statusManutencao);
        veiculoRepository.save(veiculo); 

        return manutencaoRepository.save(novaManutencao);
    }
}
