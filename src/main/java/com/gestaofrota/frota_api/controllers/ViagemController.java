package com.gestaofrota.frota_api.controllers;

import com.gestaofrota.frota_api.dtos.ViagemDTO;
import com.gestaofrota.frota_api.dtos.ViagemResponseDTO;
import com.gestaofrota.frota_api.models.Viagem;
import com.gestaofrota.frota_api.services.ViagemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/viagens") 
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @GetMapping
    public ResponseEntity<List<ViagemResponseDTO>> buscarTodas() {
        List<Viagem> viagens = viagemService.buscarTodas();
        List<ViagemResponseDTO> dtos = viagens.stream().map(ViagemResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/motorista/{motoristaId}/pendentes")
    public ResponseEntity<List<ViagemResponseDTO>> buscarPendentesPorMotorista(@PathVariable Long motoristaId) {
        List<Viagem> viagens = viagemService.buscarPendentesPorMotorista(motoristaId);
        List<ViagemResponseDTO> dtos = viagens.stream().map(ViagemResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/motorista/{motoristaId}/historico")
    public ResponseEntity<List<ViagemResponseDTO>> buscarHistoricoPorMotorista(@PathVariable Long motoristaId) {
        List<Viagem> viagens = viagemService.buscarHistoricoPorMotorista(motoristaId);
        List<ViagemResponseDTO> dtos = viagens.stream().map(ViagemResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/agendar")
    public ResponseEntity<ViagemResponseDTO> agendarViagem(@RequestBody ViagemDTO dto) {
        Viagem novaViagem = viagemService.agendarViagem(dto);
        return new ResponseEntity<>(new ViagemResponseDTO(novaViagem), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/iniciar")
    public ResponseEntity<ViagemResponseDTO> iniciarViagem(@PathVariable Long id, @RequestBody Map<String, Integer> payload) {
        int quilometragemInicial = payload.get("quilometragem_inicial");
        Viagem viagemIniciada = viagemService.iniciarViagem(id, quilometragemInicial);
        return ResponseEntity.ok(new ViagemResponseDTO(viagemIniciada));
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<ViagemResponseDTO> finalizarViagem(@PathVariable Long id, @RequestBody Map<String, Integer> payload) {
        int quilometragemFinal = payload.get("quilometragem_final");
        Viagem viagemFinalizada = viagemService.finalizarViagem(id, quilometragemFinal);
        return ResponseEntity.ok(new ViagemResponseDTO(viagemFinalizada));
    }
}
