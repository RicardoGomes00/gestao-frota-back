package com.gestaofrota.frota_api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "status_viagens")
public class StatusViagem {

    @Id
    private Short id;

    @Column(nullable = false, unique = true, length = 50)
    private String descricao;
}