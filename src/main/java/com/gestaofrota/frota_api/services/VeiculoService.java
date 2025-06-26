package com.gestaofrota.frota_api.services;

import com.gestaofrota.frota_api.models.StatusVeiculo;
import com.gestaofrota.frota_api.models.Veiculo;
import com.gestaofrota.frota_api.repositories.StatusVeiculoRepository;
import com.gestaofrota.frota_api.repositories.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final StatusVeiculoRepository statusVeiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, StatusVeiculoRepository statusVeiculoRepository) {
        this.veiculoRepository = veiculoRepository;
        this.statusVeiculoRepository = statusVeiculoRepository;
    }

    public List<Veiculo> buscarTodos() {
        return veiculoRepository.findAll();
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
    }

    @Transactional
    public Veiculo criarVeiculo(Veiculo veiculo) {
        if (veiculoRepository.findByPlaca(veiculo.getPlaca()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um veículo com esta placa.");
        }
        return veiculoRepository.save(veiculo);
    }

    @Transactional
    public Veiculo atualizarVeiculo(Long id, Veiculo veiculoDetalhes) {
        Veiculo veiculoExistente = buscarPorId(id);

        veiculoExistente.setPlaca(veiculoDetalhes.getPlaca());
        veiculoExistente.setModelo(veiculoDetalhes.getModelo());
        veiculoExistente.setTipo(veiculoDetalhes.getTipo());
        veiculoExistente.setAno(veiculoDetalhes.getAno());
        
        veiculoExistente.setQuilometragemAtual(veiculoDetalhes.getQuilometragemAtual());
        
        veiculoExistente.setStatus(veiculoDetalhes.getStatus());

        return veiculoRepository.save(veiculoExistente);
    }

    @Transactional
    public Veiculo atualizarStatus(Long id, String novoStatusDescricao) {
        Veiculo veiculoExistente = buscarPorId(id);

        StatusVeiculo novoStatus = statusVeiculoRepository.findByDescricao(novoStatusDescricao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status inválido: " + novoStatusDescricao));
        
        veiculoExistente.setStatus(novoStatus);
        
        return veiculoRepository.save(veiculoExistente);
    }
}
