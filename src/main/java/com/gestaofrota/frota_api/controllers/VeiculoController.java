package com.gestaofrota.frota_api.controllers;

import com.gestaofrota.frota_api.dtos.VeiculoDTO; 
import com.gestaofrota.frota_api.models.Veiculo;
import com.gestaofrota.frota_api.services.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> listarTodos() {
        List<Veiculo> veiculos = veiculoService.buscarTodos();
        List<VeiculoDTO> veiculosDto = veiculos.stream().map(VeiculoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(veiculosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscarPorId(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.buscarPorId(id);
        return ResponseEntity.ok(new VeiculoDTO(veiculo));
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> criarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.criarVeiculo(veiculo);
        return new ResponseEntity<>(new VeiculoDTO(novoVeiculo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoDetalhes) {
        Veiculo veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculoDetalhes);
        return ResponseEntity.ok(new VeiculoDTO(veiculoAtualizado));
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<VeiculoDTO> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
         String novoStatus = payload.get("status");
         if (novoStatus == null || novoStatus.trim().isEmpty()) {
             return ResponseEntity.badRequest().build();
         }
         Veiculo veiculoAtualizado = veiculoService.atualizarStatus(id, novoStatus);
         return ResponseEntity.ok(new VeiculoDTO(veiculoAtualizado));
    }
}
