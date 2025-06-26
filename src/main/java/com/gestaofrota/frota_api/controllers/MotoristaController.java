package com.gestaofrota.frota_api.controllers;

import com.gestaofrota.frota_api.dtos.MotoristaCreateDTO;
import com.gestaofrota.frota_api.models.Motorista;
import com.gestaofrota.frota_api.repositories.MotoristaRepository;
import com.gestaofrota.frota_api.services.MotoristaService; // Importe o service
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/motoristas")
public class MotoristaController {

    private MotoristaService motoristaService;

    private MotoristaRepository motoristaRepository; 

    @GetMapping
    public List<Motorista> listarTodos() {
        return motoristaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorista> buscarPorId(@PathVariable Long id) {
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));
        return ResponseEntity.ok(motorista);
    }

    @PostMapping
    public ResponseEntity<Motorista> criarMotorista(@RequestBody MotoristaCreateDTO motoristaDTO) {
        Motorista novoMotorista = motoristaService.createMotorista(motoristaDTO);
        return new ResponseEntity<>(novoMotorista, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motorista> atualizarMotorista(@PathVariable Long id, @RequestBody Motorista motoristaDetalhes) {
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));

        motorista.setNomeCompleto(motoristaDetalhes.getNomeCompleto());
        motorista.setCnhNumero(motoristaDetalhes.getCnhNumero());
        motorista.setCnhValidade(motoristaDetalhes.getCnhValidade());
        motorista.setTelefone(motoristaDetalhes.getTelefone());
        motorista.setEnderecoCompleto(motoristaDetalhes.getEnderecoCompleto());
        
        final Motorista motoristaAtualizado = motoristaRepository.save(motorista);
        return ResponseEntity.ok(motoristaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMotorista(@PathVariable Long id) {
        motoristaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));
        motoristaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}