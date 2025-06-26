package com.gestaofrota.frota_api.services;

import com.gestaofrota.frota_api.dtos.ViagemDTO;
import com.gestaofrota.frota_api.models.*;
import com.gestaofrota.frota_api.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final VeiculoRepository veiculoRepository;
    private final MotoristaRepository motoristaRepository;
    private final StatusViagemRepository statusViagemRepository;
    private final StatusVeiculoRepository statusVeiculoRepository;

    public ViagemService(ViagemRepository viagemRepository, VeiculoRepository veiculoRepository, MotoristaRepository motoristaRepository, StatusViagemRepository statusViagemRepository, StatusVeiculoRepository statusVeiculoRepository) {
        this.viagemRepository = viagemRepository;
        this.veiculoRepository = veiculoRepository;
        this.motoristaRepository = motoristaRepository;
        this.statusViagemRepository = statusViagemRepository;
        this.statusVeiculoRepository = statusVeiculoRepository;
    }

    @Transactional
    public Viagem agendarViagem(ViagemDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
        
        Motorista motorista = motoristaRepository.findById(dto.getMotoristaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));

        if (!"Disponível".equals(veiculo.getStatus().getDescricao())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veículo não está disponível. Status atual: " + veiculo.getStatus().getDescricao());
        }
        
        List<Viagem> viagensAtivas = viagemRepository.findViagensAtivasParaVeiculo(veiculo.getId());
        if (!viagensAtivas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este veículo já possui uma viagem agendada ou em andamento e não pode ser agendado novamente.");
        }
        
        StatusViagem statusAgendado = statusViagemRepository.findByDescricao("AGENDADO")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Status 'AGENDADO' não configurado no sistema."));

        Viagem novaViagem = new Viagem();
        novaViagem.setVeiculo(veiculo);
        novaViagem.setMotorista(motorista);
        novaViagem.setDestino(dto.getDestino());
        novaViagem.setJustificativa(dto.getJustificativa());
        novaViagem.setDataSaidaAgendada(dto.getDataSaidaAgendada());
        novaViagem.setStatus(statusAgendado);

        return viagemRepository.save(novaViagem);
    }

    @Transactional
    public Viagem iniciarViagem(Long id, int quilometragemInicial) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada."));

        if (!"AGENDADO".equals(viagem.getStatus().getDescricao())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Apenas viagens agendadas podem ser iniciadas.");
        }
        
        StatusViagem statusEmUso = statusViagemRepository.findByDescricao("EM_USO")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Status 'EM_USO' não configurado."));

        StatusVeiculo statusVeiculoEmUso = statusVeiculoRepository.findByDescricao("Em Uso")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Status de veículo 'Em Uso' não configurado."));

        viagem.setStatus(statusEmUso);
        viagem.setQuilometragemInicial(quilometragemInicial);
        viagem.setDataSaidaEfetiva(LocalDateTime.now());
        
        Veiculo veiculo = viagem.getVeiculo();
        veiculo.setStatus(statusVeiculoEmUso);
        veiculoRepository.save(veiculo);

        return viagemRepository.save(viagem);
    }
    
    @Transactional
    public Viagem finalizarViagem(Long id, int quilometragemFinal) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada."));

        if (!"EM_USO".equals(viagem.getStatus().getDescricao())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Apenas viagens em uso podem ser finalizadas.");
        }
        
        StatusViagem statusFinalizado = statusViagemRepository.findByDescricao("FINALIZADO")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Status 'FINALIZADO' não configurado."));
        
        StatusVeiculo statusVeiculoDisponivel = statusVeiculoRepository.findByDescricao("Disponível")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Status de veículo 'Disponível' não configurado."));

        viagem.setStatus(statusFinalizado);
        viagem.setQuilometragemFinal(quilometragemFinal);
        viagem.setDataRetornoEfetiva(LocalDateTime.now());
        
        Veiculo veiculo = viagem.getVeiculo();
        veiculo.setStatus(statusVeiculoDisponivel);
        veiculo.setQuilometragemAtual(quilometragemFinal);
        veiculoRepository.save(veiculo);

        return viagemRepository.save(viagem);
    }

    public List<Viagem> buscarTodas() {
        return viagemRepository.findAll();
    }
}
