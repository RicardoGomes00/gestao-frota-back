package com.gestaofrota.frota_api.dtos;

import com.gestaofrota.frota_api.models.Abastecimento;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AbastecimentoDTO {
    
    private Long veiculoId;
    private Long motoristaId;
    private Long viagemId; 
    private LocalDateTime dataAbastecimento;
    private String tipoCombustivel;
    private BigDecimal valorTotal;
    private Integer quilometragemNoAbastecimento;

    private Long id;
    private VeiculoDTO veiculo; 
    private MotoristaDTO motorista; 

    public AbastecimentoDTO(Abastecimento abastecimento) {
        this.id = abastecimento.getId();
        this.dataAbastecimento = abastecimento.getDataAbastecimento();
        this.tipoCombustivel = abastecimento.getTipoCombustivel();
        this.valorTotal = abastecimento.getValorTotal();
        this.quilometragemNoAbastecimento = abastecimento.getQuilometragemNoAbastecimento();
        
        if (abastecimento.getVeiculo() != null) {
            this.veiculo = new VeiculoDTO(abastecimento.getVeiculo());
        }
        if (abastecimento.getMotorista() != null) {
            this.motorista = new MotoristaDTO(abastecimento.getMotorista());
        }
    }
}
