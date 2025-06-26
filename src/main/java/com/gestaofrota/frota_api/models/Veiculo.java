package com.gestaofrota.frota_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String placa;

    @Column(nullable = false, length = 100)
    private String modelo;
    
    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false)
    private Integer ano;

    @Column(name = "quilometragem_atual", nullable = false)
    private Integer quilometragemAtual;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "status_id", nullable = false)
    private StatusVeiculo status;
}
