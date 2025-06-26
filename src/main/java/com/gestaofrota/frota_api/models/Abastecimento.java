package com.gestaofrota.frota_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "abastecimentos")
public class Abastecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorista_id", nullable = false)
    private Motorista motorista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viagem_id")
    private Viagem viagem;

    @Column(name = "data_abastecimento", nullable = false)
    private LocalDateTime dataAbastecimento;

    @Column(name = "tipo_combustivel", nullable = false)
    private String tipoCombustivel;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "litros", precision = 10, scale = 2)
    private BigDecimal litros;

    @Column(name = "quilometragem_no_abastecimento", nullable = false)
    private Integer quilometragemNoAbastecimento;
}
