package com.gestaofrota.frota_api.controllers;


import com.gestaofrota.frota_api.models.Veiculo;
import com.gestaofrota.frota_api.repositories.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/veiculos") 
public class VeiculoController {

    private VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoRepository.save(veiculo);
        return new ResponseEntity<>(novoVeiculo, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
        return veiculoRepository.findById(id)
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build()); 
    }
}