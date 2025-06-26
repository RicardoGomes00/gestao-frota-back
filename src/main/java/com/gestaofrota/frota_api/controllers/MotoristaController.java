package com.gestaofrota.frota_api.controllers;

import com.gestaofrota.frota_api.dtos.MotoristaDTO;
import com.gestaofrota.frota_api.models.Motorista;
import com.gestaofrota.frota_api.services.MotoristaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/motoristas") 
public class MotoristaController {

    private final MotoristaService motoristaService;

    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    @GetMapping
    public ResponseEntity<List<Motorista>> listarTodos() {
        List<Motorista> motoristas = motoristaService.buscarTodos();
        return ResponseEntity.ok(motoristas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorista> buscarPorId(@PathVariable Long id) {
        Motorista motorista = motoristaService.buscarPorId(id);
        return ResponseEntity.ok(motorista);
    }

    @PostMapping
    public ResponseEntity<Motorista> criarMotorista(@RequestBody MotoristaDTO motoristaDTO) {
        Motorista novoMotorista = motoristaService.createMotorista(motoristaDTO);
        return new ResponseEntity<>(novoMotorista, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motorista> atualizarMotorista(@PathVariable Long id, @RequestBody Motorista motoristaDetalhes) {
        Motorista motoristaAtualizado = motoristaService.atualizarMotorista(id, motoristaDetalhes);
        return ResponseEntity.ok(motoristaAtualizado);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarMotorista(@PathVariable Long id) {
        motoristaService.inativarMotorista(id);
        return ResponseEntity.noContent().build();
    }
}
