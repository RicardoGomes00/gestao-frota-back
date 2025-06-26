package com.gestaofrota.frota_api.controllers;

import com.gestaofrota.frota_api.dtos.ManutencaoDTO;
import com.gestaofrota.frota_api.models.Manutencao;
import com.gestaofrota.frota_api.services.ManutencaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/manutencoes") 
public class ManutencaoController {

    private final ManutencaoService manutencaoService;

    public ManutencaoController(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

 
    @GetMapping
    public ResponseEntity<List<ManutencaoDTO>> listarTodos() {
        List<Manutencao> manutencoes = manutencaoService.buscarTodos();
        List<ManutencaoDTO> dtos = manutencoes.stream()
                .map(ManutencaoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }


    @PostMapping
    public ResponseEntity<ManutencaoDTO> registrar(@RequestBody ManutencaoDTO dto) {
        Manutencao novaManutencao = manutencaoService.registrar(dto);
        return new ResponseEntity<>(new ManutencaoDTO(novaManutencao), HttpStatus.CREATED);
    }
}
