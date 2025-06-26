package com.gestaofrota.frota_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "status_veiculos") 
public class StatusVeiculo {

    @Id 
    private Short id; 

    @Column(nullable = false, unique = true)
    private String descricao;
}