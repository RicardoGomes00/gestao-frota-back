package com.gestaofrota.frota_api.controllers;

import com.gestaofrota.frota_api.dtos.OcorrenciaDTO;
import com.gestaofrota.frota_api.models.Ocorrencia;
import com.gestaofrota.frota_api.services.OcorrenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ocorrencias") 
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }


    @GetMapping
    public ResponseEntity<List<OcorrenciaDTO>> listarTodos() {
        List<Ocorrencia> ocorrencias = ocorrenciaService.buscarTodos();
        List<OcorrenciaDTO> dtos = ocorrencias.stream()
                .map(OcorrenciaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }


    @PostMapping
    public ResponseEntity<OcorrenciaDTO> registrar(@RequestBody OcorrenciaDTO dto) {
        Ocorrencia novaOcorrencia = ocorrenciaService.registrar(dto);
        return new ResponseEntity<>(new OcorrenciaDTO(novaOcorrencia), HttpStatus.CREATED);
    }
}
