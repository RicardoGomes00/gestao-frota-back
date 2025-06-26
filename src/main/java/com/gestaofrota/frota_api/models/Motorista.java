package com.gestaofrota.frota_api.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(unique = true, nullable = false, length = 14)
    private String cpf;

    @Column(name = "cnh_numero", unique = true, nullable = false, length = 20)
    private String cnhNumero;
    
    @Column(name = "cnh_validade", nullable = false)
    private LocalDate cnhValidade;
    
    @Column(nullable = false)
    private boolean ativo;

    @Column(length = 20)
    private String telefone;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false)
    private String logradouro;
    
    @Column(nullable = false, length = 20)
    private String numero;
    
    @Column(nullable = false, length = 100)
    private String bairro;
    
    @Column(nullable = false, length = 100)
    private String cidade;
    
    @Column(nullable = false, length = 2)
    private String uf;
}
