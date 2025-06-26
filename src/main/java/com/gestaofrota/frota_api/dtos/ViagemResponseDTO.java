package com.gestaofrota.frota_api.dtos;

import com.gestaofrota.frota_api.models.Viagem;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViagemResponseDTO {
    private Long id;
    private String destino;
    private String justificativa;
    private LocalDateTime dataSaidaAgendada;
    private LocalDateTime dataRetornoEfetiva;
    private Integer quilometragemInicial;
    private Integer quilometragemFinal;
    private String status;
    private VeiculoDTO veiculo;
    private MotoristaDTO motorista;

    public ViagemResponseDTO(Viagem viagem) {
        this.id = viagem.getId();
        this.destino = viagem.getDestino();
        this.justificativa = viagem.getJustificativa();
        this.dataSaidaAgendada = viagem.getDataSaidaAgendada();
        this.dataRetornoEfetiva = viagem.getDataRetornoEfetiva();
        this.quilometragemInicial = viagem.getQuilometragemInicial();
        this.quilometragemFinal = viagem.getQuilometragemFinal();
        this.status = viagem.getStatus().getDescricao();
        
        if (viagem.getVeiculo() != null) {
            this.veiculo = new VeiculoDTO(viagem.getVeiculo());
        }
        if (viagem.getMotorista() != null) {
            this.motorista = new MotoristaDTO(viagem.getMotorista());
        }
    }
}