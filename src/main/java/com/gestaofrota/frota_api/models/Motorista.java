package com.gestaofrota.frota_api.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "motoristas")
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(name = "cnh_numero", unique = true, nullable = false)
    private String cnhNumero;
    
    @Column(name = "cnh_validade", nullable = false)
    private LocalDate cnhValidade;
    
    private String telefone;

    @Column(name = "endereco_completo")
    private String enderecoCompleto;
}