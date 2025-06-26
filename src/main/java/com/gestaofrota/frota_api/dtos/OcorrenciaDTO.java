package com.gestaofrota.frota_api.dtos;

import com.gestaofrota.frota_api.models.Ocorrencia;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OcorrenciaDTO {

    private Long viagemId;
    private String titulo;
    private String descricao;

    private Long id;
    private LocalDateTime dataOcorrencia;
    private MotoristaDTO motorista; 
    private ViagemResponseDTO viagem; 

    public OcorrenciaDTO(Ocorrencia ocorrencia) {
        this.id = ocorrencia.getId();
        this.titulo = ocorrencia.getTitulo();
        this.descricao = ocorrencia.getDescricao();
        this.dataOcorrencia = ocorrencia.getDataOcorrencia();
        
        if (ocorrencia.getMotorista() != null) {
            this.motorista = new MotoristaDTO(ocorrencia.getMotorista());
        }
        if (ocorrencia.getViagem() != null) {
            this.viagem = new ViagemResponseDTO(ocorrencia.getViagem());
        }
    }
}
