package com.gestaofrota.frota_api.dtos;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private Long usuarioId;
    private String nome;
    private String email;
    private String perfil;
    private String token; 

    public LoginResponseDTO(Long usuarioId, String nome, String email, String perfil, String token) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.token = token;
    }
}
