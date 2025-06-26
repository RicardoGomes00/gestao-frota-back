package com.gestaofrota.frota_api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senha; // O nome do campo é 'senha', mas a coluna é 'senha_hash'

    @Column(name = "tipo_perfil", nullable = false)
    private String tipoPerfil;
}