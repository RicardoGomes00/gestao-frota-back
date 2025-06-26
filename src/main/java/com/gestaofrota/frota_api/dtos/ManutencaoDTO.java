package com.gestaofrota.frota_api.dtos;

import com.gestaofrota.frota_api.models.Manutencao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ManutencaoDTO {

    private Long veiculoId;
    private LocalDate dataInicio;
    private String descricaoServico;
    private BigDecimal custo;
    private Integer quilometragem;
    private String tipo; 
    
    private Long id;
    private LocalDate dataFim;
    private VeiculoDTO veiculo; 

    public ManutencaoDTO(Manutencao manutencao) {
        this.id = manutencao.getId();
        this.dataInicio = manutencao.getDataInicio();
        this.dataFim = manutencao.getDataFim();
        this.descricaoServico = manutencao.getDescricaoServico();
        this.custo = manutencao.getCusto();
        this.quilometragem = manutencao.getQuilometragem();
        
        if (manutencao.getVeiculo() != null) {
            this.veiculo = new VeiculoDTO(manutencao.getVeiculo());
        }
    }
}
