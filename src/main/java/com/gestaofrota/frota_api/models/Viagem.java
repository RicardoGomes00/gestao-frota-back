package com.gestaofrota.frota_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "viagens")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorista_id", nullable = false)
    private Motorista motorista;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusViagem status;

    @Column(nullable = false, length = 300)
    private String destino;

    @Column(columnDefinition = "TEXT")
    private String justificativa;

    @Column(name = "data_saida_agendada", nullable = false)
    private LocalDateTime dataSaidaAgendada;

    @Column(name = "data_retorno_prevista")
    private LocalDateTime dataRetornoPrevista;

    @Column(name = "data_saida_efetiva")
    private LocalDateTime dataSaidaEfetiva;

    @Column(name = "data_retorno_efetiva")
    private LocalDateTime dataRetornoEfetiva;

    @Column(name = "quilometragem_inicial")
    private Integer quilometragemInicial;

    @Column(name = "quilometragem_final")
    private Integer quilometragemFinal;
}   