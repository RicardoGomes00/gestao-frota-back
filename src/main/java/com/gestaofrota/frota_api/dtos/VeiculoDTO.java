package com.gestaofrota.frota_api.dtos;

import com.gestaofrota.frota_api.models.Veiculo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VeiculoDTO {

    private Long id;
    private String placa;
    private String modelo;
    private String tipo;
    private Integer ano;
    private Integer quilometragemAtual;
    
    private String status;

    public VeiculoDTO(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.placa = veiculo.getPlaca();
        this.modelo = veiculo.getModelo();
        this.tipo = veiculo.getTipo();
        this.ano = veiculo.getAno();
        this.quilometragemAtual = veiculo.getQuilometragemAtual();
        if (veiculo.getStatus() != null) {
            this.status = veiculo.getStatus().getDescricao();
        }
    }
}
