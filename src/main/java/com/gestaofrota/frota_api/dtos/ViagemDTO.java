package com.gestaofrota.frota_api.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ViagemDTO {
    private Long veiculoId;
    private Long motoristaId;
    private String destino;
    private String justificativa;
    private LocalDateTime dataSaidaAgendada;
}