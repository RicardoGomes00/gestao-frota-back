package com.gestaofrota.frota_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "status_veiculos")
public class StatusVeiculo {

    @Id
    private Short id;

    @Column(nullable = false, unique = true, length = 50)
    private String descricao;
}
