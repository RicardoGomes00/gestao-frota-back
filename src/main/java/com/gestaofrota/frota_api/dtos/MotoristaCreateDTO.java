package com.gestaofrota.frota_api.dtos;

import java.time.LocalDate;
import lombok.Data;

@Data
public class MotoristaCreateDTO {
    private String nomeCompleto;
    private String cpf;
    private String cnhNumero;
    private LocalDate cnhValidade;
    private String telefone;
    private String enderecoCompleto;

    private String email;
    private String senha;
}