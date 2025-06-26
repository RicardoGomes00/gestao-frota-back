package com.gestaofrota.frota_api.services;

import com.gestaofrota.frota_api.dtos.VeiculoCreateUpdateDTO; 
import com.gestaofrota.frota_api.models.StatusVeiculo;
import com.gestaofrota.frota_api.models.Veiculo;
import com.gestaofrota.frota_api.repositories.StatusVeiculoRepository;
import com.gestaofrota.frota_api.repositories.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    public Veiculo criarVeiculo(VeiculoCreateUpdateDTO dto) {
        veiculoRepository.findByPlaca(dto.getPlaca()).ifPresent(v -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um veículo com esta placa.");
        });

        StatusVeiculo status = statusVeiculoRepository.findByDescricao(dto.getStatus())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status inválido: " + dto.getStatus()));

        Veiculo novoVeiculo = new Veiculo();
        novoVeiculo.setPlaca(dto.getPlaca());
        novoVeiculo.setModelo(dto.getModelo());
        novoVeiculo.setTipo(dto.getTipo());
        novoVeiculo.setAno(dto.getAno());
        novoVeiculo.setQuilometragemAtual(dto.getQuilometragemAtual());
        novoVeiculo.setStatus(status);

        return veiculoRepository.save(novoVeiculo);
    }

    @Transactional
    public Veiculo atualizarVeiculo(Long id, VeiculoCreateUpdateDTO dto) {
        Veiculo veiculoExistente = buscarPorId(id);

        Optional<Veiculo> veiculoComMesmaPlaca = veiculoRepository.findByPlaca(dto.getPlaca());
        if (veiculoComMesmaPlaca.isPresent() && !veiculoComMesmaPlaca.get().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A placa " + dto.getPlaca() + " já está em uso por outro veículo.");
        }

        StatusVeiculo novoStatus = statusVeiculoRepository.findByDescricao(dto.getStatus())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status inválido: " + dto.getStatus()));

        veiculoExistente.setPlaca(dto.getPlaca());
        veiculoExistente.setModelo(dto.getModelo());
        veiculoExistente.setTipo(dto.getTipo());
        veiculoExistente.setAno(dto.getAno());
        veiculoExistente.setQuilometragemAtual(dto.getQuilometragemAtual());
        veiculoExistente.setStatus(novoStatus);

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
