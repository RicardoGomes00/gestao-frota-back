package com.gestaofrota.frota_api.dtos;

import com.gestaofrota.frota_api.models.Motorista;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MotoristaDTO {

    private Long id;
    private boolean ativo;

    private String email;
    private String senha; 
    private String perfil; 

    private String nomeCompleto;
    private String cpf;
    private String cnhNumero;
    private LocalDate cnhValidade;
    private String telefone;

    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;

    public MotoristaDTO(Motorista motorista) {
        this.id = motorista.getId();
        this.ativo = motorista.isAtivo();
        this.email = motorista.getUsuario().getEmail();
        this.perfil = motorista.getUsuario().getTipoPerfil();
        this.nomeCompleto = motorista.getNomeCompleto();
        this.cpf = motorista.getCpf();
        this.cnhNumero = motorista.getCnhNumero();
        this.cnhValidade = motorista.getCnhValidade();
        this.telefone = motorista.getTelefone();
        this.cep = motorista.getCep();
        this.logradouro = motorista.getLogradouro();
        this.numero = motorista.getNumero();
        this.bairro = motorista.getBairro();
        this.cidade = motorista.getCidade();
        this.uf = motorista.getUf();
    }
}
