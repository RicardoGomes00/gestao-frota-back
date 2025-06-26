package com.gestaofrota.frota_api.controllers;

import com.gestaofrota.frota_api.dtos.AbastecimentoDTO;
import com.gestaofrota.frota_api.models.Abastecimento;
import com.gestaofrota.frota_api.services.AbastecimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/abastecimentos") 
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;

    public AbastecimentoController(AbastecimentoService abastecimentoService) {
        this.abastecimentoService = abastecimentoService;
    }


    @GetMapping
    public ResponseEntity<List<AbastecimentoDTO>> listarTodos() {
        List<Abastecimento> abastecimentos = abastecimentoService.buscarTodos();
        
        List<AbastecimentoDTO> dtos = abastecimentos.stream()
                .map(AbastecimentoDTO::new) 
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(dtos);
    }


    @PostMapping
    public ResponseEntity<AbastecimentoDTO> registrar(@RequestBody AbastecimentoDTO dto) {
        Abastecimento novoAbastecimento = abastecimentoService.registrar(dto);
        
        return new ResponseEntity<>(new AbastecimentoDTO(novoAbastecimento), HttpStatus.CREATED);
    }
}
