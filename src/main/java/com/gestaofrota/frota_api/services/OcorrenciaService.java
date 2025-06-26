package com.gestaofrota.frota_api.services;

import com.gestaofrota.frota_api.dtos.OcorrenciaDTO;
import com.gestaofrota.frota_api.models.*;
import com.gestaofrota.frota_api.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final ViagemRepository viagemRepository;
    private final MotoristaRepository motoristaRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository, ViagemRepository viagemRepository, MotoristaRepository motoristaRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.viagemRepository = viagemRepository;
        this.motoristaRepository = motoristaRepository;
    }


    public List<Ocorrencia> buscarTodos() {
        return ocorrenciaRepository.findAll();
    }


    @Transactional
    public Ocorrencia registrar(OcorrenciaDTO dto) {
        Viagem viagem = viagemRepository.findById(dto.getViagemId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem com ID " + dto.getViagemId() + " não encontrada."));

        Ocorrencia novaOcorrencia = new Ocorrencia();
        
        novaOcorrencia.setViagem(viagem);
        novaOcorrencia.setMotorista(viagem.getMotorista()); 
        novaOcorrencia.setDataOcorrencia(LocalDateTime.now()); 
        novaOcorrencia.setTitulo(dto.getTitulo());
        novaOcorrencia.setDescricao(dto.getDescricao());

        return ocorrenciaRepository.save(novaOcorrencia);
    }
}
