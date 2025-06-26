package com.gestaofrota.frota_api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String placa;

    @Column(nullable = false)
    private String modelo;
    
    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Integer ano;

    @Column(name = "quilometragem_atual", nullable = false)
    private Integer quilometragemAtual;
    

    @ManyToOne 
    @JoinColumn(name = "status_id", nullable = false) 
    private StatusVeiculo status;
}