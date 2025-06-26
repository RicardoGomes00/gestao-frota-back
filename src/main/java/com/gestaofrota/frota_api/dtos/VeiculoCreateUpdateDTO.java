package com.gestaofrota.frota_api.dtos;

import lombok.Data;

@Data
public class VeiculoCreateUpdateDTO {

    private String placa;
    private String modelo;
    private String tipo;
    private Integer ano;
    private Integer quilometragemAtual;
    private String status;
}
