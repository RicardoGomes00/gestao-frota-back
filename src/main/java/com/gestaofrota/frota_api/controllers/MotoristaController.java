package com.gestaofrota.frota_api.controllers;

import com.gestaofrota.frota_api.dtos.MotoristaDTO;
import com.gestaofrota.frota_api.models.Motorista;
import com.gestaofrota.frota_api.services.MotoristaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
    
@RestController
@RequestMapping("/api/admin/motoristas") 
public class MotoristaController {

    private final MotoristaService motoristaService;

    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    @GetMapping
    public ResponseEntity<List<MotoristaDTO>> listarTodos() {
        List<Motorista> motoristas = motoristaService.buscarTodos();
        
        List<MotoristaDTO> dtos = motoristas.stream()
                .map(MotoristaDTO::new) 
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MotoristaDTO> buscarPorId(@PathVariable Long id) {
        Motorista motorista = motoristaService.buscarPorId(id);
        return ResponseEntity.ok(new MotoristaDTO(motorista));
    }

    @PostMapping
    public ResponseEntity<MotoristaDTO> criarMotorista(@RequestBody MotoristaDTO motoristaDTO) {
        Motorista novoMotorista = motoristaService.createMotorista(motoristaDTO);
        return new ResponseEntity<>(new MotoristaDTO(novoMotorista), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoristaDTO> atualizarMotorista(@PathVariable Long id, @RequestBody MotoristaDTO motoristaDetalhes) {
        Motorista motoristaAtualizado = motoristaService.atualizarMotorista(id, motoristaDetalhes);
        
        return ResponseEntity.ok(new MotoristaDTO(motoristaAtualizado));
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarMotorista(@PathVariable Long id) {
        motoristaService.inativarMotorista(id);
        return ResponseEntity.noContent().build();
    }
}
